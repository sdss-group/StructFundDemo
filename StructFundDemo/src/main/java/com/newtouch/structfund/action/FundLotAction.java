package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.FundLot;
import com.newtouch.structfund.entity.FundSet;
import com.newtouch.structfund.mapper.FundLotMapper;
import com.newtouch.structfund.mapper.FundSetMapper;
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
    FundSetMapper fundSetMapper;

    @RequestMapping("queryFundLot")
    public Map queryFundLot(FundLot fundLot, Integer currentPage, Integer pageSize) {

        Map<String, Object> param = new HashMap();
        param.put("fundLot", fundLot);
        param.put("startRow", (currentPage - 1) * pageSize);
        param.put("pageSize", pageSize);
        List<FundLot> resultList = fundLotMapper.queryFundLot(param);
        if (!resultList.isEmpty() && resultList.size() > 0) {
            for (FundLot temp : resultList) {
                if (temp.getOperator() != null && !"".equals(temp.getOperator().trim())) {
                   /* User modifyUser = userOrgPermission.getUserByUserCode(temp.getOperator());
                    temp.setOperator(modifyUser.getUserName());*/
                }
                if (temp.getAuthorizer() != null && !"".equals(temp.getAuthorizer().trim())) {
                   /* User author = userOrgPermission.getUserByUserCode(temp.getAuthorizer());
                    temp.setAuthorizer(author.getUserName());*/
                }
            }
        }
        Map<String, Object> resultMap = new HashMap();
        int totalRows = fundLotMapper.count(param);
        resultMap.put("dataList", resultList);
        resultMap.put("totalRows", totalRows);
        return resultMap;
    }

    @RequestMapping("delete")
    public int delete(@RequestBody List<FundLot> rows) {

        return fundLotMapper.delete(rows);

    }

    @RequestMapping("insert")
    public Map<String, Object> insert(HttpSession session, FundLot fundLot) {

        List<FundLot> resultList = fundLotMapper.selectSingleData(fundLot);
        Map<String, Object> result = new HashMap<>();
        if (!resultList.isEmpty() && resultList.size() > 0) {
            result.put("result", "添加失败: 已存在相同记录!");
            return result;
        }
        FundSet fundSet = new FundSet();
        FundSet entity = fundSetMapper.selectByPrimaryKey(fundSet);
        if (entity != null) {
            fundLot.setFundName(fundSet.getFundName());
        }
        String userCode = (String) session.getAttribute("user");
        fundLot.setLotStatus("0");//新建产品批次信息默认为初始化状态
        fundLot.setOperator("A");
        fundLot.setAuthorizer("A");
        result.put("result", fundLotMapper.insertSelective(fundLot));
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
