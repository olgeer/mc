package com.ucsmy.mc.centerM.controller;

import com.ucsmy.mc.centerM.bean.McModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jack on 2017/6/2.
 */
public class kc_GrpController {


    @RequestMapping(value="/message",method = RequestMethod.POST)
    @ResponseBody
    public String rev_message(McModel mcModel) {
    return  "";
    }

}
