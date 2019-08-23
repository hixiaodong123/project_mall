package com.cskaoyan.mall.controller.wx_controller;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.login.UserInfo;

import com.cskaoyan.mall.bean.login.BaseRespVo;
import com.cskaoyan.mall.bean.login.UserToken;
import com.cskaoyan.mall.service.mall.OrderService;
import com.cskaoyan.mall.service.user.UserService;
import com.cskaoyan.mall.utils.wx_util.UserTokenManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by little Stone
 * Date 2019/7/8 Time 20:55
 */
@RestController
@RequestMapping("/wx")
public class WxAuthController {

	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;

	/*@RequestMapping("/auth/login")
	@ResponseBody
	public Object login(@RequestBody User user, HttpServletRequest request) {
		String username = user.getUsername();
		String password = user.getPassword();

		//*******************************
		//根据username和password查询user信息
		User user1 = userService.selectByUsernameAndPassword(username, password);
		//*******************************

		// userInfo
		UserInfo userInfo = new UserInfo();
		userInfo.setNickName(username);
		//userInfo.setAvatarUrl(user.getAvatar());


		//********************************
		//根据获得userid
		int userId = user1.getId();
		//********************************
		// token
		UserToken userToken = UserTokenManager.generateToken(userId);

		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("token", userToken.getToken());
		result.put("tokenExpire", userToken.getExpireTime().toString());
		result.put("userInfo", userInfo);
		return BaseRespVo.ok(result);
	}*/

	@GetMapping("user/index")
	public Object list(HttpServletRequest request) {
		//前端写了一个token放在请求头中
		//*************************
		//获得请求头
		String tokenKey = request.getHeader("X-Litemall-Token");
		Integer userId = UserTokenManager.getUserId(tokenKey);
		//通过请求头获得userId，进而可以获得一切关于user的信息
		//**************************
		if (userId == null) {
			return BaseRespVo.fail();
		}

		long unpaid = orderService.queryOrderStatusNum(101);
		long unship = orderService.queryOrderStatusNum(201);
		long unrecv = orderService.queryOrderStatusNum(301);
		long uncomment = orderService.queryOrderStatusNum(401);
		HashMap<String, Object> map1 = new HashMap<>();
		HashMap<String, Object> data = new HashMap<>();
		map1.put("unpaid",unpaid);
		map1.put("unship",unship);
		map1.put("unrecv",unrecv);
		map1.put("uncomment",uncomment);
		//***********************************
		//根据userId查询订单信息
		data.put("order", map1);
		//***********************************

		return BaseRespVo.ok(data);
	}
}
