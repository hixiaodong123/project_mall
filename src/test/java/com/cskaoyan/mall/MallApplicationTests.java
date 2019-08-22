package com.cskaoyan.mall;

import com.cskaoyan.mall.bean.Api;
import com.cskaoyan.mall.bean.Authorization;
import com.cskaoyan.mall.service.admin.ApiService;
import com.cskaoyan.mall.service.admin.AuthorizationService;
import com.cskaoyan.mall.utils.json.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.cskaoyan.mall.mapper")
public class MallApplicationTests {
    @Autowired
    AuthorizationService as;

    @Autowired
    ApiService ap;

    /* 授权表
    CREATE TABLE `cskaoyan_mall_authorization` (
            `autid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '授权父ID，例如四级菜单的pid指向三级菜单，三级菜单的pid指向二级菜单，二级菜单的pid指向一级菜单，一级菜单的pid则是0',
            `id` varchar(120) NOT NULL DEFAULT '' COMMENT '查询接口，如admin:collect:list',
            `label` varchar(120) NOT NULL DEFAULT '' COMMENT '授权名称',
            `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '授权类型，如如1则是一级菜单， 如果是2则是二级菜单，如果是3则是三级菜单，如果是4则是四级菜单',
    PRIMARY KEY (`autid`) USING BTREE,
    KEY `parent_id` (`pid`) USING BTREE,
    KEY `region_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3232 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='授权表'
    */

    /* 接口表
        CREATE TABLE `cskaoyan_mall_api` (
  `apiid` int(11) NOT NULL AUTO_INCREMENT,
  `api` varchar(120) NOT NULL DEFAULT '' COMMENT '接口名称，如GET /admin/collect/list',
	`id` varchar(120) NOT NULL DEFAULT '' COMMENT '查询接口，如admin:collect:list',
  PRIMARY KEY (`apiid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3232 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='接口表'

     */


    //先运行以上两条sql语句，在数据库中新建两个表api和authorization
    //填充api和authorization表中的数据
    @Test
    public void injectData() {

        String string = "{\"systemPermissions\":[{\"id\":\"用户管理\",\"label\":\"用户管理\",\"children\":[{\"id\":\"用户收藏\",\"label\":\"用户收藏\",\"children\":[{\"id\":\"admin:collect:list\",\"label\":\"查询\",\"api\":\"GET /admin/collect/list\"}]},{\"id\":\"意见反馈\",\"label\":\"意见反馈\",\"children\":[{\"id\":\"admin:feedback:list\",\"label\":\"查询\",\"api\":\"GET /admin/feedback/list\"}]},{\"id\":\"会员管理\",\"label\":\"会员管理\",\"children\":[{\"id\":\"admin:user:list\",\"label\":\"查询\",\"api\":\"GET /admin/user/list\"}]},{\"id\":\"用户足迹\",\"label\":\"用户足迹\",\"children\":[{\"id\":\"admin:footprint:list\",\"label\":\"查询\",\"api\":\"GET /admin/footprint/list\"}]},{\"id\":\"搜索历史\",\"label\":\"搜索历史\",\"children\":[{\"id\":\"admin:history:list\",\"label\":\"查询\",\"api\":\"GET /admin/history/list\"}]},{\"id\":\"收货地址\",\"label\":\"收货地址\",\"children\":[{\"id\":\"admin:address:list\",\"label\":\"查询\",\"api\":\"GET /admin/address/list\"}]}]},{\"id\":\"推广管理\",\"label\":\"推广管理\",\"children\":[{\"id\":\"团购管理\",\"label\":\"团购管理\",\"children\":[{\"id\":\"admin:groupon:read\",\"label\":\"详情\",\"api\":\"GET /admin/groupon/listRecord\"},{\"id\":\"admin:groupon:update\",\"label\":\"编辑\",\"api\":\"POST /admin/groupon/update\"},{\"id\":\"admin:groupon:delete\",\"label\":\"删除\",\"api\":\"POST /admin/groupon/delete\"},{\"id\":\"admin:groupon:create\",\"label\":\"添加\",\"api\":\"POST /admin/groupon/create\"},{\"id\":\"admin:groupon:list\",\"label\":\"查询\",\"api\":\"GET /admin/groupon/list\"}]},{\"id\":\"广告管理\",\"label\":\"广告管理\",\"children\":[{\"id\":\"admin:ad:update\",\"label\":\"编辑\",\"api\":\"POST /admin/ad/update\"},{\"id\":\"admin:ad:read\",\"label\":\"详情\",\"api\":\"GET /admin/ad/read\"},{\"id\":\"admin:ad:delete\",\"label\":\"删除\",\"api\":\"POST /admin/ad/delete\"},{\"id\":\"admin:ad:create\",\"label\":\"添加\",\"api\":\"POST /admin/ad/create\"},{\"id\":\"admin:ad:list\",\"label\":\"查询\",\"api\":\"GET /admin/ad/list\"}]},{\"id\":\"专题管理\",\"label\":\"专题管理\",\"children\":[{\"id\":\"admin:topic:update\",\"label\":\"编辑\",\"api\":\"POST /admin/topic/update\"},{\"id\":\"admin:topic:read\",\"label\":\"详情\",\"api\":\"GET /admin/topic/read\"},{\"id\":\"admin:topic:delete\",\"label\":\"删除\",\"api\":\"POST /admin/topic/delete\"},{\"id\":\"admin:topic:create\",\"label\":\"添加\",\"api\":\"POST /admin/topic/create\"},{\"id\":\"admin:topic:list\",\"label\":\"查询\",\"api\":\"GET /admin/topic/list\"}]},{\"id\":\"优惠券管理\",\"label\":\"优惠券管理\",\"children\":[{\"id\":\"admin:coupon:listuser\",\"label\":\"查询用户\",\"api\":\"GET /admin/coupon/listuser\"},{\"id\":\"admin:coupon:update\",\"label\":\"编辑\",\"api\":\"POST /admin/coupon/update\"},{\"id\":\"admin:coupon:read\",\"label\":\"详情\",\"api\":\"GET /admin/coupon/read\"},{\"id\":\"admin:coupon:delete\",\"label\":\"删除\",\"api\":\"POST /admin/coupon/delete\"},{\"id\":\"admin:coupon:create\",\"label\":\"添加\",\"api\":\"POST /admin/coupon/create\"},{\"id\":\"admin:coupon:list\",\"label\":\"查询\",\"api\":\"GET /admin/coupon/list\"}]}]},{\"id\":\"配置管理\",\"label\":\"配置管理\",\"children\":[{\"id\":\"商场配置\",\"label\":\"商场配置\",\"children\":[{\"id\":\"admin:config:mall:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/mall\"},{\"id\":\"admin:config:mall:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/mall\"}]},{\"id\":\"运费配置\",\"label\":\"运费配置\",\"children\":[{\"id\":\"admin:config:express:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/express\"},{\"id\":\"admin:config:express:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/express\"}]},{\"id\":\"订单配置\",\"label\":\"订单配置\",\"children\":[{\"id\":\"admin:config:order:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/order\"},{\"id\":\"admin:config:order:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/order\"}]},{\"id\":\"小程序配置\",\"label\":\"小程序配置\",\"children\":[{\"id\":\"admin:config:wxcontroller:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/wxcontroller\"},{\"id\":\"admin:config:wxcontroller:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/wxcontroller\"}]}]},{\"id\":\"其他\",\"label\":\"其他\",\"children\":[{\"id\":\"权限测试\",\"label\":\"权限测试\",\"children\":[{\"id\":\"index:permission:write\",\"label\":\"权限写\",\"api\":\"POST /admin/index/write\"},{\"id\":\"index:permission:read\",\"label\":\"权限读\",\"api\":\"GET /admin/index/read\"}]}]},{\"id\":\"统计管理\",\"label\":\"统计管理\",\"children\":[{\"id\":\"用户统计\",\"label\":\"用户统计\",\"children\":[{\"id\":\"admin:stat:user\",\"label\":\"查询\",\"api\":\"GET /admin/stat/user\"}]},{\"id\":\"订单统计\",\"label\":\"订单统计\",\"children\":[{\"id\":\"admin:stat:order\",\"label\":\"查询\",\"api\":\"GET /admin/stat/order\"}]},{\"id\":\"商品统计\",\"label\":\"商品统计\",\"children\":[{\"id\":\"admin:stat:wxgoods\",\"label\":\"查询\",\"api\":\"GET /admin/stat/wxgoods\"}]}]},{\"id\":\"系统管理\",\"label\":\"系统管理\",\"children\":[{\"id\":\"管理员管理\",\"label\":\"管理员管理\",\"children\":[{\"id\":\"admin:admin:update\",\"label\":\"编辑\",\"api\":\"POST /admin/admin/update\"},{\"id\":\"admin:admin:read\",\"label\":\"详情\",\"api\":\"GET /admin/admin/read\"},{\"id\":\"admin:admin:delete\",\"label\":\"删除\",\"api\":\"POST /admin/admin/delete\"},{\"id\":\"admin:admin:create\",\"label\":\"添加\",\"api\":\"POST /admin/admin/create\"},{\"id\":\"admin:admin:list\",\"label\":\"查询\",\"api\":\"GET /admin/admin/list\"}]},{\"id\":\"操作日志\",\"label\":\"操作日志\",\"children\":[{\"id\":\"admin:log:list\",\"label\":\"查询\",\"api\":\"GET /admin/log/list\"}]},{\"id\":\"角色管理\",\"label\":\"角色管理\",\"children\":[{\"id\":\"admin:role:permission:update\",\"label\":\"权限变更\",\"api\":\"POST /admin/role/permissions\"},{\"id\":\"admin:role:update\",\"label\":\"角色编辑\",\"api\":\"POST /admin/role/update\"},{\"id\":\"admin:role:read\",\"label\":\"角色详情\",\"api\":\"GET /admin/role/read\"},{\"id\":\"admin:role:delete\",\"label\":\"角色删除\",\"api\":\"POST /admin/role/delete\"},{\"id\":\"admin:role:permission:get\",\"label\":\"权限详情\",\"api\":\"GET /admin/role/permissions\"},{\"id\":\"admin:role:create\",\"label\":\"角色添加\",\"api\":\"POST /admin/role/create\"},{\"id\":\"admin:role:list\",\"label\":\"角色查询\",\"api\":\"GET /admin/role/list\"}]},{\"id\":\"对象存储\",\"label\":\"对象存储\",\"children\":[{\"id\":\"admin:storage:update\",\"label\":\"编辑\",\"api\":\"POST /admin/storage/update\"},{\"id\":\"admin:storage:read\",\"label\":\"详情\",\"api\":\"POST /admin/storage/read\"},{\"id\":\"admin:storage:delete\",\"label\":\"删除\",\"api\":\"POST /admin/storage/delete\"},{\"id\":\"admin:storage:create\",\"label\":\"上传\",\"api\":\"POST /admin/storage/create\"},{\"id\":\"admin:storage:list\",\"label\":\"查询\",\"api\":\"GET /admin/storage/list\"}]}]},{\"id\":\"商场管理\",\"label\":\"商场管理\",\"children\":[{\"id\":\"品牌管理\",\"label\":\"品牌管理\",\"children\":[{\"id\":\"admin:brand:update\",\"label\":\"编辑\",\"api\":\"POST /admin/brand/update\"},{\"id\":\"admin:brand:read\",\"label\":\"详情\",\"api\":\"GET /admin/brand/read\"},{\"id\":\"admin:brand:delete\",\"label\":\"删除\",\"api\":\"POST /admin/brand/delete\"},{\"id\":\"admin:brand:create\",\"label\":\"添加\",\"api\":\"POST /admin/brand/create\"},{\"id\":\"admin:brand:list\",\"label\":\"查询\",\"api\":\"GET /admin/brand/list\"}]},{\"id\":\"订单管理\",\"label\":\"订单管理\",\"children\":[{\"id\":\"admin:order:refund\",\"label\":\"订单退款\",\"api\":\"POST /admin/order/refund\"},{\"id\":\"admin:order:ship\",\"label\":\"订单发货\",\"api\":\"POST /admin/order/ship\"},{\"id\":\"admin:order:read\",\"label\":\"详情\",\"api\":\"GET /admin/order/detail\"},{\"id\":\"admin:order:reply\",\"label\":\"订单商品回复\",\"api\":\"POST /admin/order/reply\"},{\"id\":\"admin:order:list\",\"label\":\"查询\",\"api\":\"GET /admin/order/list\"}]},{\"id\":\"关键词\",\"label\":\"关键词\",\"children\":[{\"id\":\"admin:keyword:update\",\"label\":\"编辑\",\"api\":\"POST /admin/keyword/update\"},{\"id\":\"admin:keyword:read\",\"label\":\"详情\",\"api\":\"GET /admin/keyword/read\"},{\"id\":\"admin:keyword:delete\",\"label\":\"删除\",\"api\":\"POST /admin/keyword/delete\"},{\"id\":\"admin:keyword:create\",\"label\":\"添加\",\"api\":\"POST /admin/keyword/create\"},{\"id\":\"admin:keyword:list\",\"label\":\"查询\",\"api\":\"GET /admin/keyword/list\"}]},{\"id\":\"类目管理\",\"label\":\"类目管理\",\"children\":[{\"id\":\"admin:category:update\",\"label\":\"编辑\",\"api\":\"POST /admin/category/update\"},{\"id\":\"admin:category:read\",\"label\":\"详情\",\"api\":\"GET /admin/category/read\"},{\"id\":\"admin:category:delete\",\"label\":\"删除\",\"api\":\"POST /admin/category/delete\"},{\"id\":\"admin:category:create\",\"label\":\"添加\",\"api\":\"POST /admin/category/create\"},{\"id\":\"admin:category:list\",\"label\":\"查询\",\"api\":\"GET /admin/category/list\"}]},{\"id\":\"通用问题\",\"label\":\"通用问题\",\"children\":[{\"id\":\"admin:issue:update\",\"label\":\"编辑\",\"api\":\"POST /admin/issue/update\"},{\"id\":\"admin:issue:delete\",\"label\":\"删除\",\"api\":\"POST /admin/issue/delete\"},{\"id\":\"admin:issue:create\",\"label\":\"添加\",\"api\":\"POST /admin/issue/create\"},{\"id\":\"admin:issue:list\",\"label\":\"查询\",\"api\":\"GET /admin/issue/list\"}]}]},{\"id\":\"商品管理\",\"label\":\"商品管理\",\"children\":[{\"id\":\"商品管理\",\"label\":\"商品管理\",\"children\":[{\"id\":\"admin:wxgoods:read\",\"label\":\"详情\",\"api\":\"GET /admin/wxgoods/detail\"},{\"id\":\"admin:wxgoods:update\",\"label\":\"编辑\",\"api\":\"POST /admin/wxgoods/update\"},{\"id\":\"admin:wxgoods:delete\",\"label\":\"删除\",\"api\":\"POST /admin/wxgoods/delete\"},{\"id\":\"admin:wxgoods:create\",\"label\":\"上架\",\"api\":\"POST /admin/wxgoods/create\"},{\"id\":\"admin:wxgoods:list\",\"label\":\"查询\",\"api\":\"GET /admin/wxgoods/list\"}]},{\"id\":\"评论管理\",\"label\":\"评论管理\",\"children\":[{\"id\":\"admin:comment:delete\",\"label\":\"删除\",\"api\":\"POST /admin/comment/delete\"},{\"id\":\"admin:comment:list\",\"label\":\"查询\",\"api\":\"GET /admin/comment/list\"}]}]}]}";
        Map<String, Object> map = JsonUtil.strJson2Map(string);
        List<Map<String, Object>> systemPermissions = (List<Map<String, Object>>) map.get("systemPermissions");
        for (Map<String, Object> sp : systemPermissions) {
            Authorization aut = new Authorization();
            aut.setId(((String) sp.get("id")).replaceAll("\"",""));
            aut.setLabel(((String) sp.get("label")).replaceAll("\"",""));
            aut.setPid(0);
            aut.setType((byte) 1);
            as.insertAuthorization(aut);
        }
        for (Map<String, Object> sp : systemPermissions) {
            List<Map<String, Object>> children = (List<Map<String, Object>>) sp.get("children");
            for (Map<String, Object> child : children) {
                Authorization a = as.listAuthorizationsByIdAndType(((String) sp.get("id")).replaceAll("\"",""), (byte) 1);
                Authorization aut = new Authorization();
                aut.setId(((String) child.get("id")).replaceAll("\"",""));
                aut.setLabel(((String) child.get("label")).replaceAll("\"",""));
                aut.setPid(a.getAutid());
                aut.setType((byte) 2);
                as.insertAuthorization(aut);
            }
        }
        for (Map<String, Object> sp : systemPermissions) {
            List<Map<String, Object>> children1 = (List<Map<String, Object>>) sp.get("children");
            for (Map<String, Object> child1 : children1) {
                List<Map<String, Object>> children2 = (List<Map<String, Object>>) child1.get("children");
                for (Map<String, Object> child2 : children2) {
                    Authorization a = as.listAuthorizationsByIdAndType(((String) child1.get("id")).replaceAll("\"",""), (byte) 2);
                    Authorization aut = new Authorization();
                    aut.setId(((String) child2.get("id")).replaceAll("\"",""));
                    aut.setLabel(((String) child2.get("label")).replaceAll("\"",""));
                    aut.setPid(a.getAutid());
                    aut.setType((byte) 3);
                    as.insertAuthorization(aut);
                    Api api = new Api();
                    api.setApi(((String) child2.get("api")).replaceAll("\"",""));
                    api.setId(((String) child2.get("id")).replaceAll("\"",""));
                    ap.insertApi(api);
                }

            }
        }
    }
}
