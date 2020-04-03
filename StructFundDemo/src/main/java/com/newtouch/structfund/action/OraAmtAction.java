package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.OrgAmt;
import com.newtouch.structfund.mapper.OrgAmtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("orgAmt")
public class OraAmtAction {

    @Autowired
    OrgAmtMapper orgAmtMapper;

    @RequestMapping("queryOrgAmt")
    public Map queryOrgAmt(OrgAmt orgAmt, Integer currentPage, Integer pageSize) {

        Map<String, Object> param = new HashMap();
        param.put("orgAmt", orgAmt);
        param.put("startRow", (currentPage - 1) * pageSize);
        param.put("pageSize", pageSize);
        List<OrgAmt> list = orgAmtMapper.querySaleAmtInfo(param);
        for (OrgAmt item : list) {
            List<Map> saledAmtList = orgAmtMapper.querySaledAmt(item);
            Long saledAmt = 0L;
            if (saledAmtList.size() > 0) {
                for (Map saledAmtMap : saledAmtList) {
                    saledAmt = saledAmt - ((BigDecimal) saledAmtMap.get("AMOUNT")).longValue();
                }
            }
            item.setSaledAmt(saledAmt);
            item.setRestAmt(item.getTotalAmt().longValue() - item.getSaledAmt());
        }
        int count = orgAmtMapper.countOrgAmt(param);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("dataList", list);
        resultMap.put("totalRows", count);
        return resultMap;
    }

    @RequestMapping("update")
    public int update(OrgAmt orgAmt) {

        return orgAmtMapper.update(orgAmt);

    }

}
