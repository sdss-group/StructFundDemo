package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.RegFundVo;
import com.newtouch.structfund.mapper.RegFundQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("regAndFund")
public class RegFundQueryAction {
    @Autowired
    RegFundQueryMapper qm;
    /*
    查询出所有的registerCode和registerName
     */
    @RequestMapping("getRegList")
    public List<RegFundVo> getRegList(){
       return qm.getRegList();
    }


    /*
    根据registerCode查询出对应的所有fundCode
     */
}
