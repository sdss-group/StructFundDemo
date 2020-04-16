package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.*;
import com.newtouch.structfund.mapper.FundSaleMapper;
import com.newtouch.structfund.mapper.SaleBranchDateMapper;
import com.newtouch.structfund.mapper.SalePersionsDateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("saleBranchDate")
public class SaleBranchDateAction {
    @Autowired
    SaleBranchDateMapper saleBranchDateMapper;
    @Autowired
    FundSaleMapper fundSaleMapper;


    //销售商条件查询
    @RequestMapping("getBranchDate")
    public Map getList(SaleBranchDate saleBranchDate,Integer currentPage, Integer pageSize) {
        Map param=new HashMap();
        param.put("registerCode", saleBranchDate.getRegisterCode());
        param.put("fundCode", saleBranchDate.getFundCode());
        param.put("startRow", (currentPage-1)*pageSize);
        param.put("pageSize", pageSize);
        List<FundSale> fundSales = fundSaleMapper.queryFundSaleInfo(param);
        Integer totalRows=fundSaleMapper.count(param);
        List<SaleBranchDate> SaleBranchDates=new ArrayList<>();
        if(fundSales !=null && fundSales.size()>0){
            for (FundSale fundSale : fundSales) {
                SaleBranchDate  saleBranch=new SaleBranchDate();
                List<SaleBranchDate> sbd=saleBranchDateMapper.getBranch(fundSale.getRegisterCode(),fundSale.getFundCode());
                if(sbd !=null && sbd.size()>0){
                    List<SaleBranchDate> branchs=saleBranchDateMapper.queryBranch(fundSale.getRegisterCode(),fundSale.getFundCode());
                    saleBranch.setRegisterCode(fundSale.getRegisterCode());
                    saleBranch.setFundCode(fundSale.getFundCode());
                    saleBranch.setFundName(fundSale.getFundName());
                    List<OrgAmt> org=new ArrayList<>();
                    for (SaleBranchDate branch : branchs) {
                        OrgAmt  orgAmt=new OrgAmt();
                        orgAmt.setOrgCode(branch.getOrgCode());
                        orgAmt.setOrgName(branch.getOrgName());
                        org.add(orgAmt);
                    }
                    saleBranch.setOrgAmts(org);

                }else {
                    saleBranch.setRegisterCode(fundSale.getRegisterCode());
                    saleBranch.setFundCode(fundSale.getFundCode());
                    saleBranch.setFundName(fundSale.getFundName());
                    List<OrgAmt> org=new ArrayList<>();
                        OrgAmt  orgAmt=new OrgAmt();
                        orgAmt.setOrgCode("0111111");
                        orgAmt.setOrgName("全部分行可销售");
                        org.add(orgAmt);
                    saleBranch.setOrgAmts(org);
                }
                SaleBranchDates.add(saleBranch);
            }
        }
        Map resultMap=new HashMap();
        resultMap.put("dataList", SaleBranchDates);
        resultMap.put("totalRows", totalRows);
        return resultMap;
    }

    @RequestMapping("update")
    public void update(@RequestBody SaleBranchDate saleBranchDate) {
        String registerCode = saleBranchDate.getRegisterCode();
        String fundCode = saleBranchDate.getFundCode();
        int delete = saleBranchDateMapper.delete(registerCode, fundCode);
        List<String> orgCodes = saleBranchDate.getOrgCodes();
        List<SaleBranchDate> allBranchList = saleBranchDateMapper.findAllBranch();
        List<String> branchs=new ArrayList<>();
        for (SaleBranchDate branchDate : allBranchList) {
            branchs.add(branchDate.getOrgCode());
        }
         branchs.removeAll(orgCodes);
        for (String branch : branchs) {
            SaleBranchDate sbd=new SaleBranchDate();
            sbd.setRegisterCode(registerCode);
            sbd.setFundCode(fundCode);
            sbd.setOrgCode(branch);
            saleBranchDateMapper.save(sbd);
        }
    }

    @RequestMapping("findAllBranch")
    public Map findAllBranch(HttpServletRequest req) {
        List<SaleBranchDate> allBranch = saleBranchDateMapper.findAllBranch();
        List<OrgAmt> OrgAmts=new ArrayList<>();
        for (SaleBranchDate branch : allBranch) {
                OrgAmt orgAmt=new OrgAmt();
                orgAmt.setOrgCode(branch.getOrgCode());
                orgAmt.setOrgName(branch.getOrgName());
                OrgAmts.add(orgAmt);
        }
        Map resultMap=new HashMap();
        resultMap.put("dataList", OrgAmts);
        return resultMap;
    }
}
