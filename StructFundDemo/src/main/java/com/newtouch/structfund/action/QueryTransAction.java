package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.AppTotal;
import com.newtouch.structfund.entity.FundCusttype;
import com.newtouch.structfund.entity.FundReg;
import com.newtouch.structfund.mapper.AccountTransMappser;
import com.newtouch.structfund.mapper.AppTotalMapper;
import com.newtouch.structfund.mapper.FundRegMapper;
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

    @Autowired
    FundRegMapper fundRegMapper;

    @RequestMapping("accountTrans")
    public Map queryAccountTrans(HttpSession session, String registerCode, Integer currentPage, Integer pageSize) {

        //  String userCode = session.getAttribute("userCode").toString();
        //  List<String> totalOrg = queryOrgCode(userCode);
        Map<String, Object> param = new HashMap();
        param.put("startRow", (currentPage - 1) * pageSize);
        param.put("pageSize", pageSize);
        param.put("totalOrg", null);
        param.put("registerCode", registerCode);
        List<AppTotal> resultList = appTotalMapper.queryAccountTrans(param);
        List<AppTotal> resultListOut = appTotalMapper.queryAccountTransOut(param);
        int totalRows = appTotalMapper.sumAccountTrans(param);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("dataList", resultList);
        resultMap.put("dataListOut", resultListOut);
        resultMap.put("totalRows", totalRows);
        return resultMap;
    }

    @RequestMapping("transTrans")
    public Map queryTransTrans(HttpSession session, FundCusttype fundCusttype, Integer currentPage, Integer pageSize) {

      //  String userCode = session.getAttribute("userCode").toString();
     //   List<String> totalOrg = queryOrgCode(userCode);
        Map<String, Object> param = new HashMap();
        param.put("startRow", (currentPage - 1) * pageSize);
        param.put("pageSize", pageSize);
        param.put("registerCode", fundCusttype.getRegisterCode());
        param.put("fundCode", fundCusttype.getFundCode());
        param.put("totalOrg", null);
        List<AppTotal> resultList = appTotalMapper.queryTransTrans(param);
        List<AppTotal> resultListOut = appTotalMapper.queryTransTransOut(param);
        int totalRows = appTotalMapper.sumTransTrans(param);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("dataList", resultList);
        resultMap.put("dataListOut", resultListOut);
        resultMap.put("totalRows", totalRows);
        return resultMap;
    }

    //页面初始化，查询所有登记机构和产品代码下拉框展示
    @RequestMapping("queryAllAgencyAndProcode")
    public List<FundReg> queryAllAgencyAndProcode() {

        return fundRegMapper.queryAllAgencyAndProcode();
    }

    //根据所选登记机构查询产品代码下拉框展示
    @RequestMapping("queryFundCodeByAgency")
    public List<FundReg> queryFundCodeByAgency(String registerCode) {

        return fundRegMapper.queryFundCode(registerCode);
    }

    //根据产品代码查询登记机构反显展示
    @RequestMapping("queryAgencyByFundCode")
    public List<FundReg> queryAgencyByFundCode(String fundCode) {

        List<FundReg> fundRegList = new ArrayList<>();
        fundRegList.add(fundRegMapper.queryAgency(fundCode));
        return fundRegList;
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
