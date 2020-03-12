package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.FundCusttype;
import com.newtouch.structfund.entity.FundLot;
import com.newtouch.structfund.mapper.FundCusttypeMapper;
import com.newtouch.structfund.mapper.FundLotMapper;
import com.newtouch.structfund.mapper.FundRegMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("fundLot")
public class FundLotAction {

    @Autowired
    FundLotMapper fundLotMapper;

    @Autowired
    FundRegMapper fundRegMapper;

    @Autowired
    FundCusttypeMapper fundCusttypeMapper;

    @RequestMapping("listFundLot")
    public List<FundLot> listFundLot(Integer pageIndex, Integer pageSize, FundLot fundLot) {

     //   PageHelper.startPage(pageIndex, pageSize);
        return fundLotMapper.list(fundLot);
    }

    @RequestMapping("listFundCusttype")
    public List<FundCusttype> listFundCusttype() {

        return fundCusttypeMapper.listAll();
    }


    @RequestMapping("delete")
    public int delete(@RequestParam List<String> ids) {

            return fundLotMapper.delete(ids);

    }

    @RequestMapping("getOne")
    public FundLot getOne(FundLot fundLot) {

        return fundLotMapper.getOne(fundLot);

    }

    @RequestMapping("insert")
    public int insert(FundLot fundLot) {

        return fundLotMapper.insert(fundLot);

    }

    @RequestMapping("update")
    public int update(FundLot fundLot) {

        return fundLotMapper.update(fundLot);

    }

}
