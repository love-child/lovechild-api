package com.children.controller;

import com.children.bean.ResponseBean;
import com.children.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author 孙博
 * date 2020/8/6 15:59
 */
@CrossOrigin
@RestController
@Api(value = "user", tags = {"user",}, description = "the love-baby user API")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "登陆接口", notes = "", response = Void.class, tags = {"user",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取成功", response = ResponseBean.class),
            @ApiResponse(code = 400, message = "Invalid supplied", response = ResponseBean.class)})
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseBean login(@ApiParam(value = "user_account", required = true) @RequestParam("user_account") String userAccount,
                              @ApiParam(value = "user_password", required = true) @RequestParam("user_password") String userPassword){
        return ResponseBean.success(userService.login(userAccount, userPassword));
    }
}
