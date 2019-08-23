package com.cskaoyan.mall.controller.wxcontroller.wxauth;

import com.cskaoyan.mall.bean.base.BaseResponseModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx")
public class AuthController {

    @RequestMapping("auth/register")
    public BaseResponseModel authRegister() {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        return null;
    }

    @RequestMapping("auth/regCaptcha")
    public BaseResponseModel authRegCaptcha(@RequestBody String mobile) {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        return null;
    }
}
