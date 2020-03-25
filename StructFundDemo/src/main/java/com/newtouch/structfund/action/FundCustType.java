package com.newtouch.structfund.action;


import com.newtouch.structfund.mapper.FundCusttypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("fundCustType")
public class FundCustType {
    @Autowired
    FundCusttypeMapper fm;

    //查询出指定resisterCode和fundCode的客户群
    @RequestMapping("getCustTypeList")
    public List<String> listSaleBillDate(String registerCode,String fundCode) {


        return fm.listTypeByReCodeAndFuCode(registerCode,fundCode);
    }

    //新增客户群

    @RequestMapping("insertCustTypeList")
    public int insert(String registerCode,String fundCode,String[] toAdd) {



        return fm.insert(registerCode,fundCode,toAdd);
    }

    //删除客户群
    @RequestMapping("deleteCustTypeList")
    public int delete(String registerCode,String fundCode,String[] toDelete) {



        return fm.delete(registerCode,fundCode,toDelete);
    }

}
