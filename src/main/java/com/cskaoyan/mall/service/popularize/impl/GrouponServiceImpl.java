package com.cskaoyan.mall.service.popularize.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.wx.GrouponWX;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.popularize.GrouponService;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrouponServiceImpl implements GrouponService {

    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Override
    public long countByExample(GrouponExample example) {
        return grouponMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(GrouponExample example) {
        return grouponMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return grouponMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Groupon record) {
        return grouponMapper.insert(record);
    }

    @Override
    public int insertSelective(Groupon record) {
        return grouponMapper.insertSelective(record);
    }

    @Override
    public List<Groupon> selectByExample(GrouponExample example) {
        return grouponMapper.selectByExample(example);
    }

    @Override
    public List<Groupon> selectByConditions(Integer goodsId, String sort, String order) {
        return grouponMapper.selectByConditions(goodsId, sort, order);
    }

    @Override
    public Groupon selectByPrimaryKey(Integer id) {
        return grouponMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Groupon record, GrouponExample example) {
        return grouponMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Groupon record, GrouponExample example) {
        return grouponMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Groupon record) {
        return grouponMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Groupon record) {
        return grouponMapper.updateByPrimaryKey(record);
    }

    @Override
    public Map<String, Object> returnGrouponList(int showType) {
        GrouponExample example = new GrouponExample();
        HashMap<String, Object> map = new HashMap<>();
        List<Groupon> groupons=null;
        if(showType==0) {
            example.createCriteria().andCreatorUserIdEqualTo(1).andGrouponIdEqualTo(0);
             groupons = grouponMapper.selectByExample(example);
        }else{
            example.createCriteria().andCreatorUserIdEqualTo(0).andGrouponIdEqualTo(1);
             groupons = grouponMapper.selectByExample(example);
        }
        if(groupons!=null){
            map.put("count",groupons.size());
            ArrayList<Object> list = new ArrayList<>();
            for (Groupon groupon : groupons) {
                HashMap<String, Object> map1 = new HashMap<>();
                Order order = orderMapper.selectByPrimaryKey(groupon.getOrderId());
                map1.put("actualPrice",order.getActualPrice());
                if(groupon.getCreatorUserId()!=0){
                    User user = userMapper.selectByPrimaryKey(groupon.getCreatorUserId());
                    map1.put("creator",user.getUsername());
                }else{
                    example.createCriteria().andGrouponIdEqualTo(0).andOrderIdEqualTo(groupon.getOrderId());
                    List<Groupon> groupons1 = grouponMapper.selectByExample(example);
                    for (Groupon groupon1 : groupons1) {
                        User user = userMapper.selectByPrimaryKey(groupon1.getOrderId());
                        map1.put("creator",user.getUsername());
                    }
                }
                OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
                orderGoodsExample.createCriteria().andOrderIdEqualTo(groupon.getOrderId());
                List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
                ArrayList<Object> list1 = new ArrayList<>();
                for (OrderGoods orderGood : orderGoods) {
                    HashMap<String, Object> map2 = new HashMap<>();
                    map2.put("goodsName",orderGood.getGoodsName());
                    map2.put("id",orderGood.getId());
                    map2.put("number",orderGood.getNumber());
                    map2.put("picUrl",orderGood.getPicUrl());
                    list1.add(map2);
                }
                map1.put("goodsList",list1);
                map1.put("groupon",groupon);

                Short orderStatus = order.getOrderStatus();
                Map<String, Object> map2 = new HashMap<>();
                map2.put("cancel", false);
                map2.put("comment", false);
                map2.put("delete", false);
                map2.put("confirm", false);
                map2.put("pay", false);
                map2.put("rebuy", false);
                map2.put("refund", false);
                switch (orderStatus) {
                    case 101:
                        map2.put("cancel", true);
                        map2.put("pay", true);
                        break;
                    case 201:
                        map2.put("refund", true);
                        break;
                    case 301:
                        map2.put("confirm", true);
                        break;
                    case 401:
                        map2.put("comment", true);
                        map2.put("delete", true);
                        map2.put("rebuy", true);
                        break;
                }
                map1.put("handleOption",map2);
                map1.put("id",groupon.getId());
                map1.put("isCreator",groupon.getCreatorUserId()!=0);
                example.createCriteria().andOrderIdEqualTo(groupon.getOrderId());
                List<Groupon> grouponList = grouponMapper.selectByExample(example);
                map1.put("joinerCount",grouponList.size());
                map1.put("orderId",groupon.getOrderId());
                map1.put("orderSn",order.getOrderSn());
                switch (order.getOrderStatus()) {
                    case 101:
                        map1.put("orderStatusText", "未付款");
                        break;
                    case 102:
                        map1.put("orderStatusText", "已取消");
                        break;
                    case 103:
                        map1.put("orderStatusText", "已取消");
                        break;
                    case 201:
                        map1.put("orderStatusText", "已付款");
                        break;
                    case 202:
                        map1.put("orderStatusText", "申请退款");
                        break;
                    case 203:
                        map1.put("orderStatusText", "已退款");
                        break;
                    case 301:
                        map1.put("orderStatusText", "已发货");
                        break;
                    case 401:
                        map1.put("orderStatusText", "已收货");
                        break;
                    case 402:
                        map1.put("orderStatusText", "已收货");
                        break;
                }
                GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(groupon.getRulesId());
                map1.put("rules",grouponRules);
                list.add(map1);
            }
            map.put("data",list);
        }
        return ReturnMapUntil.returnMap(map,"成功",0);
    }

    @Override
    public Map<String, Object> returnGrouponDetail(int grouponId) {
        Groupon groupon = grouponMapper.selectByPrimaryKey(grouponId);
        HashMap<String, Object> map = new HashMap<>();
        GrouponExample example = new GrouponExample();
        example.createCriteria().andOrderIdEqualTo(grouponId).andCreatorUserIdNotEqualTo(0);
        List<Groupon> groupons = grouponMapper.selectByExample(example);
        User user = userMapper.selectByPrimaryKey(groupons.get(0).getCreatorUserId());
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("avatar",user.getAvatar());
        map1.put("nickname",user.getUsername());
        map.put("creator",map1);
        map.put("groupon",groupon);
        example.createCriteria().andOrderIdEqualTo(groupon.getOrderId()).andCreatorUserIdEqualTo(0);
        List<Groupon> grouponList = grouponMapper.selectByExample(example);
        ArrayList<Object> list1 = new ArrayList<>();
        for (Groupon groupon1 : grouponList) {
            HashMap<String, Object> map2 = new HashMap<>();
            User user1 = userMapper.selectByPrimaryKey(groupon1.getGrouponId());
            if(user1!=null){
                map2.put("avatar",user1.getAvatar());
                map2.put("nickname",user1.getUsername());
            }
            list1.add(map2);
        }
        list1.add(map1.get("creator"));
        map.put("joiners",list1);
        map.put("linkGrouponId",grouponId);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByOrderId(groupon.getOrderId());
        ArrayList<Object> list2 = new ArrayList<>();
        for (OrderGoods orderGood : orderGoods) {
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("goodsId",orderGood.getGoodsId());
            map3.put("goodsName",orderGood.getGoodsName());
            map3.put("goodsSpecificationValues",orderGood.getSpecifications());
            map3.put("id",orderGood.getId());
            map3.put("number",orderGood.getNumber());
            map3.put("orderId",orderGood.getId());
            map3.put("picUrl",orderGood.getPicUrl());
            map3.put("retailPrice",orderGood.getPrice());
            list2.add(map3);
        }
        map.put("orderGoods",list2);

        HashMap<String, Object> map3 = new HashMap<>();
        Order order = orderMapper.selectByPrimaryKey(groupon.getOrderId());
        map3.put("actualPrice",order.getActualPrice());
        map3.put("addTime",order.getAddTime());
        map3.put("address",order.getAddress());
        map3.put("consignee",order.getConsignee());
        map3.put("freighPrice",order.getFreightPrice());
        map3.put("goodsPrice",order.getGoodsPrice());

        Short orderStatus = order.getOrderStatus();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("cancel", false);
        map2.put("comment", false);
        map2.put("delete", false);
        map2.put("confirm", false);
        map2.put("pay", false);
        map2.put("rebuy", false);
        map2.put("refund", false);
        switch (orderStatus) {
            case 101:
                map2.put("cancel", true);
                map2.put("pay", true);
                break;
            case 201:
                map2.put("refund", true);
                break;
            case 301:
                map2.put("confirm", true);
                break;
            case 401:
                map2.put("comment", true);
                map2.put("delete", true);
                map2.put("rebuy", true);
                break;
        }
        map3.put("handleOption",map2);
        map3.put("id",order.getId());
        map3.put("mobile",order.getMobile());
        map3.put("orderSn",order.getOrderSn());
        switch (order.getOrderStatus()) {
            case 101:
                map3.put("orderStatusText", "未付款");
                break;
            case 102:
                map3.put("orderStatusText", "已取消");
                break;
            case 103:
                map3.put("orderStatusText", "已取消");
                break;
            case 201:
                map3.put("orderStatusText", "已付款");
                break;
            case 202:
                map3.put("orderStatusText", "申请退款");
                break;
            case 203:
                map3.put("orderStatusText", "已退款");
                break;
            case 301:
                map3.put("orderStatusText", "已发货");
                break;
            case 401:
                map3.put("orderStatusText", "已收货");
                break;
            case 402:
                map3.put("orderStatusText", "已收货");
                break;
        }
        map.put("orderInfo",map3);
        GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(groupon.getRulesId());
        map.put("rules",grouponRules);
        return ReturnMapUntil.returnMap(map,"成功",0);
    }

}
