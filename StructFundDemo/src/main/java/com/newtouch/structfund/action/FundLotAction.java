package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.FundCusttype;
import com.newtouch.structfund.entity.FundLot;
import com.newtouch.structfund.mapper.FundCusttypeMapper;
import com.newtouch.structfund.mapper.FundLotMapper;
import com.newtouch.structfund.mapper.FundRegMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
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
        Integer totalRows = fundLotMapper.count(param);
        resultMap.put("totalRows", totalRows);
        return resultMap;
    }

    @RequestMapping("listFundCusttype")
    public List<FundCusttype> listFundCusttype() {

        return fundCusttypeMapper.listAll();
    }


    @RequestMapping("delete")
    public int delete(@RequestBody List<FundLot> rows) {

        return fundLotMapper.delete(rows);

    }

    @RequestMapping("getOne")
    public FundLot getOne(FundLot fundLot) {

        return fundLotMapper.getOne(fundLot);

    }

    @RequestMapping("insert")
    public Map<String, Object> insert(HttpSession session, FundLot fundLot) {

        List<FundLot> resultList = fundLotMapper.selectSingleData(fundLot);
        Map<String, Object> result = new HashMap<>();
        if (!resultList.isEmpty() && resultList.size() > 0) {
            result.put("result", "失败:已存在相同记录!");
            return result;
        }
        String userCode = (String) session.getAttribute("user");
        fundLot.setOperator("A");
        fundLot.setAuthorizer("A");
        result.put("result", fundLotMapper.insert(fundLot));
        return result;

    }

    @RequestMapping("update")
    public Map<String, Object> update(FundLot fundLot) {
        fundLot.setOperator("A");
        fundLot.setAuthorizer("A");
        Map<String, Object> result = new HashMap<>();
        result.put("result", fundLotMapper.update(fundLot));
        return result;

    }

}
