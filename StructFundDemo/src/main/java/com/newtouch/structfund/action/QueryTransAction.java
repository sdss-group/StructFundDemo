package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.AppTotal;
import com.newtouch.structfund.entity.FundCusttype;
import com.newtouch.structfund.mapper.AccountTransMappser;
import com.newtouch.structfund.mapper.AppTotalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("queryTrans")
public class QueryTransAction {

    @Autowired
    AccountTransMappser accountTransMappser;

    @Autowired
    AppTotalMapper appTotalMapper;

    @RequestMapping("accountTrans")
    public Map queryAccountTrans(HttpSession session, String registerCode, Integer currentPage, Integer pageSize) {

        String userCode = session.getAttribute("userCode").toString();
        List<String> totalOrg = queryOrgCode(userCode);
        Map<String, Object> param = new HashMap();
        param.put("startRow", (currentPage - 1) * pageSize);
        param.put("pageSize", pageSize);
        param.put("totalOrg", totalOrg);
        param.put("registCode", registerCode);
        List<AppTotal> resultList = appTotalMapper.queryAccountTrans(param);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("dataList", resultList);
        resultMap.put("totalRows", resultList.size());
        return resultMap;
    }

    @RequestMapping("agencyTrans")
    public Map queryAgencyTrans(HttpSession session, FundCusttype fundCusttype, Integer currentPage, Integer pageSize) {

        String userCode = session.getAttribute("userCode").toString();
        List<String> totalOrg = queryOrgCode(userCode);
        Map<String, Object> param = new HashMap();
        param.put("startRow", (currentPage - 1) * pageSize);
        param.put("pageSize", pageSize);
        param.put("registCode", fundCusttype.getRegisterCode());
        param.put("fundCode", fundCusttype.getFundCode());
        param.put("totalOrg", totalOrg);
        List<AppTotal> resultList = appTotalMapper.queryAgencyTrans(param);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("dataList", resultList);
        resultMap.put("totalRows", resultList.size());
        return resultMap;
    }

    public void exportExcel() {

    }


    /**
     * 查询柜员所属机构
     *
     * @param userCode
     * @return
     */
    public List<String> queryOrgCode(String userCode) {
        List<String> allOrgCode = new ArrayList<String>();
        return allOrgCode;
    }
}
