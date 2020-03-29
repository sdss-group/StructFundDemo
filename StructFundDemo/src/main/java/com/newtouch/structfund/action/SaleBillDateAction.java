package com.newtouch.structfund.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newtouch.structfund.entity.ResponseMessage;
import com.newtouch.structfund.entity.SaleBillDate;
import com.newtouch.structfund.mapper.SaleBillDateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("saleBillDate")
public class SaleBillDateAction {
    @Autowired
    SaleBillDateMapper sdm;


    @RequestMapping("/listSaleBillDate")
    public ResponseMessage<List<SaleBillDate>> listSaleBillDate(Integer currentPage, Integer pageSize, SaleBillDate sbd) {


        PageHelper.startPage(currentPage,pageSize);


        List<SaleBillDate> list=sdm.getList(sbd);
        PageInfo<SaleBillDate> pageInfo=new PageInfo<>(list);
        ResponseMessage<List<SaleBillDate>> message=new ResponseMessage<>();
        message.setTotalPieces(pageInfo.getTotal());
        message.setContent(list);

        return message;
    }

    @RequestMapping("delete")
    public int delete(@RequestBody List<SaleBillDate> items) {


        return sdm.delete(items);

    }

    @RequestMapping("getOne")
    public SaleBillDate delete(@RequestBody SaleBillDate item) {

        return sdm.getOne(item);
    }
    @RequestMapping("insert")
    public int insert(@RequestBody SaleBillDate item) {
        item.setDistributorCode("006");
        System.out.println("---------------------");
        System.out.println(item);
        System.out.println("---------------------");
        return sdm.insert(item);
    }
    @RequestMapping("update")
    public int update(@RequestBody SaleBillDate item) {

        return sdm.update(item);
    }

}
