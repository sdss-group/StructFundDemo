package com.newtouch.structfund.action;


import com.newtouch.structfund.mapper.FundCusttypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

import java.util.Set;

@RestController
@RequestMapping("fundCustType")
public class FundCustType {
    @Autowired
    FundCusttypeMapper fm;

    //查询出指定resisterCode和fundCode的客户群
    @RequestMapping("getCustTypeList")
    public Set<String> listSaleBillDate(String registerCode, String fundCode) {


        return fm.listTypeByReCodeAndFuCode(registerCode,fundCode);
    }

    //增删客户群
    @RequestMapping("updateByRegAndFund")
    public int updateByRegAndFund(String registerCode, String fundCode,String[] checked) {


        //如果checked为空，则删除全部
        if(checked==null||checked.length ==0){
            fm.deleteAll(registerCode,fundCode);
            return 0;
        }
        HashSet<String> checkList=new HashSet<>();
        for(String s:checked){
            checkList.add(s);
        }

        Set<String> origin = fm.listTypeByReCodeAndFuCode(registerCode, fundCode);
        //交集：
        HashSet<String> intersection=new HashSet<>();
        intersection.addAll(origin);
        intersection.retainAll(checkList);


        //需要添加的
        HashSet<String> toAdd=new HashSet<>();
        toAdd.addAll(checkList);
        toAdd.removeAll(intersection);



        //需要删除的
        HashSet<String> toDelete=new HashSet<>();
        toDelete.addAll(origin);
        toDelete.removeAll(intersection);


        if(toAdd!=null && !toAdd.isEmpty()){
            fm.insert(registerCode,fundCode,toAdd);
        }
        if(toDelete!=null && !toDelete.isEmpty()){
            fm.delete(registerCode,fundCode,toDelete);
        }
        return 0;


    }



}
