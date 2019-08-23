package com.cskaoyan.mall.controller.wx.person.foot;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.service.person.foot.UserFootPrintService;
import com.cskaoyan.mall.utils.wx.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 足迹模块控制器
 * @author: Lime
 * @create: 2019-08-23 12:06
 **/

@RestController
@RequestMapping("/wx/footprint")
public class WxFootPrintController
{

    final UserFootPrintService userFootPrintService;
    final GoodsMapper goodsMapper;

    @Autowired
    public WxFootPrintController(UserFootPrintService userFootPrintService, GoodsMapper goodsMapper)
    {
        this.userFootPrintService = userFootPrintService;
        this.goodsMapper = goodsMapper;
    }

    @RequestMapping("list")
    public BaseResponseModel footprintList(int page, int size, HttpServletRequest request)
    {

        BaseResponseModel resp = new BaseResponseModel();
        try
        {

            //获取userId
            String token = request.getHeader("X-Litemall-Token");
            Integer userId = null;
            if (token != null && !"".equals(token))
            {
                userId = UserTokenManager.getUserId(token);
            }
            List<Footprint> list = userFootPrintService.queryFootPrint(page, size, userId, null);
            List<Footprint> list1 = list.subList((page - 1) * size, Math.min(page * size, list.size()));
            int count = userFootPrintService.countFootprintById(userId);
            HashMap data = new HashMap();
            data.put("totalPages", (int) Math.ceil(count / size));
            ArrayList list2 = new ArrayList();
            for (Footprint footprint : list1)
            {
                GoodsExample goodsExample = new GoodsExample();
                goodsExample.createCriteria().andGoodsSnEqualTo(footprint.getGoodsId().toString());
                List<Goods> goods = goodsMapper.selectByExample(goodsExample);
                HashMap bean = new HashMap();
                bean.put("addTime", footprint.getAddTime());
                bean.put("brief", goods.get(0).getBrief());
                bean.put("goodsId", goods.get(0).getGoodsSn());
                bean.put("id", goods.get(0).getId());
                bean.put("name", goods.get(0).getName());
                bean.put("picUrl", goods.get(0).getPicUrl());
                bean.put("retailPrice", goods.get(0).getRetailPrice());
                list2.add(bean);
            }
            data.put("footprintList", list2);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }
        catch (Exception e)
        {
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
}
