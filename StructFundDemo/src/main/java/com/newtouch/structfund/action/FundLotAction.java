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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("queryFundLot")
    public Map queryFundLot(FundLot fundLot, Integer currentPage, Integer pageSize) {

        Map<String, Object> param = new HashMap();
        param.put("fundLot", fundLot);
        param.put("startRow", (currentPage - 1) * pageSize);
        param.put("pageSize", pageSize);
        List<FundLot> resultList = fundLotMapper.queryFundLot(param);
        // Integer totalRows = fundLotMapper.count(param);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("dataList", resultList);
        resultMap.put("totalRows", resultList.size());
        return resultMap;
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
