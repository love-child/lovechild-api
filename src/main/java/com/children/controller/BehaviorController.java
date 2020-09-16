package com.children.controller;

import com.children.bean.ResponseBean;
import com.children.service.BehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author 孙博
 * date 2020/9/4 16:49
 */
@CrossOrigin
@RestController
public class BehaviorController {

    @Autowired
    private BehaviorService behaviorService;

    @RequestMapping(value = "/behavior/list", method = RequestMethod.GET)
    public ResponseBean list(@RequestParam(value = "page", defaultValue = "0") int page){
        return ResponseBean.success(behaviorService.findList(page, 10));
    }
}
