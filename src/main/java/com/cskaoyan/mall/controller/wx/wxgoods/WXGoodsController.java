package com.cskaoyan.mall.controller.wx.wxgoods;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.goods.*;
import com.cskaoyan.mall.service.mall.BrandService;
import com.cskaoyan.mall.service.mall.CategoryService;
import com.cskaoyan.mall.service.mall.IssueService;
import com.cskaoyan.mall.service.popularize.GrouponRulesService;
import com.cskaoyan.mall.service.user.CollectService;
import com.cskaoyan.mall.service.user.FootPrintService;
import com.cskaoyan.mall.service.user.SearchHistoryService;
import com.cskaoyan.mall.service.user.UserService;
import com.cskaoyan.mall.utils.wx.UserTokenManager;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/wx/goods")
public class WXGoodsController
{
    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsAttributeService goodsAttributeService;

    @Autowired
    BrandService brandService;

    @Autowired
    CommentService commentService;

    @Autowired
    GrouponRulesService grouponRulesService;

    @Autowired
    IssueService issueService;

    @Autowired
    GoodsProductService goodsProductService;

    @Autowired
    GoodsSpecificationService goodsSpecificationService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SearchHistoryService searchHistoryService;

    @Autowired
    CollectService collectService;

    @Autowired
    FootPrintService footPrintService;

    //设置显示数目，避免网页响应时间过长
    int goodsListSize = 8;
    //搜索时需要保留category信息，故将其设为全局变量
    Set<Category> filter = new HashSet<>();
    //记录搜索的keywords，发生变化时，更新filter
    String oldKeyword = "";

    @RequestMapping("/count")
    public BaseResponseModel countGoods()
    {
        long size = goodsService.selectAllGoods();
        BaseResponseModel<Map<String, Object>> responseModel = new BaseResponseModel<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsCount", size);
        responseModel.setErrmsg("成功");
        responseModel.setErrno(0);
        responseModel.setData(map);
        return responseModel;
    }

    @RequestMapping("/detail")
    public BaseResponseModel detailGoods(int id, HttpServletRequest request)
    {
        Goods goods = goodsService.selectByPrimaryKey(id);

        //1.attribute 根据goods_attribute表查询
        List<GoodsAttribute> attributeList = goodsAttributeService.selectAttributeByGoodsId(id);
        //brand 根据goods表查询
        Brand brand = brandService.selectByPrimaryKey(goods.getBrandId());

        //2.comment 根据comment表查询
        Map<String, Object> commentMap = commentService.selectGoodsCommentByValueId(id + "");
        //更换map中的key值,total-->count
        commentMap.put("count", commentMap.remove("total"));
        //因为需要返回的json数据融合了User信息和Comment信息，
        //所以调用mergeUserAndComment方法生成符合要求的List<Map>对象
        commentMap.put("data", mergeUserAndComment((List<Comment>) commentMap.get("items")));
        //删除commentMap原有的items键值对
        commentMap.remove("items");

        //3.groupon 根据groupon表查询
        List<GrouponRules> grouponList = grouponRulesService.selectByConditions(id, null, null);
        //4.info 根据goods表查询

        //5.issue 根据issue表查询
        List<Issue> issueList = issueService.queryIssueList(null, null, null);

        //6.productList 根据goods_products表查询
        List<GoodsProduct> goodsProductList = goodsProductService.selectGoodsProductsByGoodsId(id);

        //7.shareImage 根据
        String shareImage = goods.getShareUrl();

        //8.specificationList 根据goods_specificationList表查询
        List<Map> goodsSpecificationList = goodsSpecificationService.selectGoodsSpecificationByGoodsId(id);

        //9.userHasCollect 根据user表查询
        Integer userHasCollect;
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        List<Collect> collects = new ArrayList<>();
        if (userId != null)
        {
            collects = collectService.listColletByCondition(userId.toString(), id + "", "add_time", "desc");
        }
        if (collects.size() != 0)
        {
            userHasCollect = 1;
        }
        else
        {
            userHasCollect = 0;
        }


        HashMap<String, Object> map = new HashMap<>();
        map.put("attribute", attributeList);
        map.put("brand", brand);
        map.put("comment", commentMap);
        map.put("groupon", grouponList);
        map.put("info", goods);
        map.put("issue", issueList);
        map.put("productList", goodsProductList);
        map.put("shareImage", shareImage);
        map.put("specificationList", goodsSpecificationList);
        map.put("userHasCollect", userHasCollect);

        //增加用户足迹记录
        if (userId != null)
        {
            footPrintService.insertFootPrint(userId, id);
        }

        BaseResponseModel<Map> responseModel = new BaseResponseModel<>();
        responseModel.setData(map);
        responseModel.setErrmsg("成功");
        responseModel.setErrno(0);
        return responseModel;
    }

    private List<Map> mergeUserAndComment(List<Comment> items)
    {
        ArrayList<Map> maps = new ArrayList<>();
        int size = items.size();
        size = size > 2 ? 2 : size;
        for (int i = 0; i < size; i++)
        {
            Integer userId = items.get(i).getUserId();
            User user = userService.selectByPrimaryKey(userId);
            Map<String, Object> map = new HashMap<>();
            map.put("addTime", items.get(i).getAddTime());
            map.put("avatar", user.getAvatar());
            map.put("content", items.get(i).getContent());
            map.put("id", items.get(i).getId());
            map.put("nickname", user.getNickname());
            map.put("picList", items.get(i).getPicUrls());
            maps.add(map);
        }
        return maps;
    }

    @RequestMapping("/related")
    public BaseResponseModel relatedGoods(int id)
    {
        Goods goods = goodsService.selectByPrimaryKey(id);
        //根据当前商品的category_id查询对应分类下的所有商品
        List<Goods> goodsList = goodsService.selectGoodsByCategoryId(goods.getCategoryId());
        BaseResponseModel<Map> responseModel = new BaseResponseModel<>();
        responseModel.setErrmsg("成功");
        responseModel.setErrno(0);
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsList", goodsList.subList(0, goodsListSize));
        responseModel.setData(map);
        return responseModel;
    }

    @RequestMapping("/list")
    public BaseResponseModel goodsList(String keyword, int page, int size, String sort, String order, Integer categoryId, Integer brandId, HttpServletRequest request)
    {
        PageHelper.startPage(page, size);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        Map<String, Object> data = new HashMap<>();
        List<Goods> goodsList = new ArrayList<>();

        keyword = com.cskaoyan.mall.controller.webcontroller.user.BaseEncapsulation.judgeString(keyword);
        if (sort == null)
        {
            sort = "add_time";
        }
        if (order == null)
        {
            order = "desc";
        }

        if (brandId != null)
        {
            goodsList = goodsService.selectGoodsByBrandId(brandId);
            categoryId = 0;
        }
        else
        {
            goodsList = goodsService.queryGoodsByKeywordOrId(keyword, sort, order, categoryId);
        }
        if (categoryId == 0)
        {
            if (oldKeyword != keyword)
            {
                filter.clear();
            }
            Set<Integer> idSet = new HashSet<>();
            for (Goods goods : goodsList)
            {
                idSet.add(goods.getCategoryId());
            }
            List<Category> filterCategoryList = new ArrayList<>();
            for (int i : idSet)
            {
                Category category = categoryService.selectByPrimaryKey(i);
                filterCategoryList.add(category);
                filter.add(category);
            }
            data.put("filterCategoryList", filterCategoryList);
        }
        data.put("filterCategoryList", filter);
        data.put("goodsList", goodsList);
        baseResponseModel.setData(data);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        //获得userId，添加用户搜索记录,含去重操作
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null)
        {
            //默认未登录时，userId为999。通过这个id做searchHistory的操作
            searchHistoryService.insertSearchHistory(keyword, 999);
        }
        else
        {
            searchHistoryService.insertSearchHistory(keyword, userId);
        }
        return baseResponseModel;
    }

    // 根据一个category的Id去查询该category的父类和兄弟类
    @RequestMapping("/category")
    public BaseResponseModel goodsCategory(int id)
    {
        Category currentCategory = categoryService.selectByPrimaryKey(id);
        if ("L1".equals(currentCategory.getLevel()))
        {
            List<Category> categoryListByPid = categoryService.selectByPid(currentCategory.getId());
            currentCategory = categoryListByPid.get(0);
            id = currentCategory.getId();
        }
        List<Category> brotherCategory = categoryService.queryBrotherCategory(currentCategory.getPid());
        Category parentCategory = categoryService.queryParentCategory(currentCategory.getPid());
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", currentCategory);
        data.put("brotherCategory", brotherCategory);
        data.put("parentCategory", parentCategory);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        baseResponseModel.setData(data);
        return baseResponseModel;
    }

}
