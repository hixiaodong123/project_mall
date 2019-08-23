package com.cskaoyan.mall.controller.webcontroller.goods;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.UpdateGoods;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.utils.GoodsReturnUntil;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
   /* @RequestMapping("list")
    public Map<String,Object> returnGoodsList(int page,int limit,String sort,String order){
        Map<String, Object> map1 = goodsService.selectAllGoodsList(page, limit,sort,order);

        return ReturnMapUntil.returnMap(map1,"成功",0);
    }*/
    @RequestMapping("list")
    public Map<String,Object> returnGoodsByGoodSn(int page,int limit,String name,String goodsSn,String sort,String order){
        if(goodsSn!=null && name != null){
            Map<String, Object> map1 = goodsService.selectGoodsByGoodSnAndName(page, limit, name, goodsSn, sort, order);
            return ReturnMapUntil.returnMap(map1,"成功",0);
        } else if(goodsSn!=null){
            Map<String, Object> map1 = goodsService.selectGoodsByGoodSn(page, limit,goodsSn,sort,order);
            return ReturnMapUntil.returnMap(map1,"成功",0);
        }else if(name != null){
            Map<String, Object> map1 = goodsService.selectGoodsByGoodsName(page, limit,name,sort,order);
            return ReturnMapUntil.returnMap(map1,"成功",0);
        }else{
            Map<String, Object> map1 = goodsService.selectAllGoodsList(page, limit,sort,order);
            return ReturnMapUntil.returnMap(map1,"成功",0);
        }
    }

    @RequestMapping("catAndBrand")
    public Map<String,Object> returnCatAndBrand(){
        return goodsService.selectAllCatAndBrand();
    }

    @RequestMapping("detail")
    public Map<String,Object> returnGoodsDetail(int id){
        return goodsService.returnGoodsDetail(id);
    }

    @RequestMapping("delete")
    public Map<String,Object> deleteGoods(@RequestBody Goods goods){
        int i = goodsService.deleteGoods(goods);
        return GoodsReturnUntil.goodsReturnUtil(i==1);
    }

    //编辑修改更新
    @RequestMapping("update")
    public Map<String,Object> updateGoods(@RequestBody UpdateGoods map){
        return goodsService.updateGoods(map);
    }

    //商品上架
    @RequestMapping("create")
    public Map<String,Object> createGoods(@RequestBody UpdateGoods map){
        return goodsService.createGoods(map);
    }

}
