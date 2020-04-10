package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.FundSaleChannel;
import com.newtouch.structfund.mapper.FundSaleChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fundSaleChannel")
@Transactional
public class FundSaleChannelAction {

    @Autowired
    private FundSaleChannelMapper fundSaleChannelMapper;

    /**
     * 添加产品销售渠道
     *
     * @author shaobo
     * @date 2020/04/07
     */
    @RequestMapping(value = "/addFundSaleChannel")
    public Object addFundSaleChannel(@RequestBody FundSaleChannel fsc) {

        Map<String, Object> result = new HashMap<>();

        String fundCode = fsc.getFundCode();
        FundSaleChannel fundSaleChannel1 = fundSaleChannelMapper.selectByPrimaryKey(fundCode);
        if (fundSaleChannel1 == null) {
            FundSaleChannel fundSaleChannel = new FundSaleChannel();
            fundSaleChannel.setFundCode(fundCode);
            fundSaleChannel.setRegisterCode(fsc.getRegisterCode());
            fundSaleChannel.setFundName(fsc.getFundName());
            fundSaleChannel.setPersonalBank(fsc.getPersonalBank());
            fundSaleChannel.setTelephoneBank(fsc.getTelephoneBank());
            fundSaleChannel.setMobilephoneBank(fsc.getMobilephoneBank());
            fundSaleChannel.setCounter(fsc.getCounter());
            fundSaleChannel.setLowCabinet(fsc.getLowCabinet());
            fundSaleChannel.setJiayitong(fsc.getJiayitong());
            fundSaleChannel.setWebPortal(fsc.getWebPortal());
            fundSaleChannel.setMultiMedia(fsc.getMultiMedia());
            fundSaleChannel.setItm(fsc.getItm());
            fundSaleChannel.setZizhutong(fsc.getZizhutong());
            fundSaleChannel.setZizhutong(fsc.getZizhutong());
            fundSaleChannel.setIntelligenceCabinet(fsc.getIntelligenceCabinet());
            fundSaleChannel.setCreateDate(new Date());
            fundSaleChannel.setUpdateDate(new Date());
            fundSaleChannelMapper.insert(fundSaleChannel);
        } else {
            result.put("result", "添加失败: 已存在相同记录!");
        }
        return result.put("result", "success");

    }

    /**
     * 编辑产品销售渠道
     *
     * @author shaobo
     * @date 2020/04/07
     */
    @RequestMapping("/editFundSaleChannel")
    public Object editFundSaleChannel(@RequestBody FundSaleChannel fsc) {

        FundSaleChannel fundSaleChannel = fundSaleChannelMapper.selectByPrimaryKey(fsc.getFundCode());
        fundSaleChannel.setFundCode(fsc.getFundCode());
        fundSaleChannel.setRegisterCode(fsc.getRegisterCode());
        fundSaleChannel.setFundName(fsc.getFundName());
        fundSaleChannel.setPersonalBank(fsc.getPersonalBank());
        fundSaleChannel.setTelephoneBank(fsc.getTelephoneBank());
        fundSaleChannel.setMobilephoneBank(fsc.getMobilephoneBank());
        fundSaleChannel.setCounter(fsc.getCounter());
        fundSaleChannel.setLowCabinet(fsc.getLowCabinet());
        fundSaleChannel.setJiayitong(fsc.getJiayitong());
        fundSaleChannel.setWebPortal(fsc.getWebPortal());
        fundSaleChannel.setMultiMedia(fsc.getMultiMedia());
        fundSaleChannel.setItm(fsc.getItm());
        fundSaleChannel.setZizhutong(fsc.getZizhutong());
        fundSaleChannel.setZizhutong(fsc.getZizhutong());
        fundSaleChannel.setIntelligenceCabinet(fsc.getIntelligenceCabinet());
        fundSaleChannel.setUpdateDate(new Date());
        fundSaleChannelMapper.update(fundSaleChannel);
        Map<String, Object> result = new HashMap<>();
        return result.put("result", "success");
    }

    /**
     * 删除产品销售渠道
     *
     * @author shaobo
     * @date 2020/04/07
     */
    @RequestMapping("/deleteFundSaleChannel")
    public Object deleteFundSaleChannel(@RequestBody List<FundSaleChannel> rows) {

        fundSaleChannelMapper.batchDelete(rows);

        Map<String, Object> result = new HashMap<>();
        return result.put("result", "success");
    }

    /**
     * 产品销售渠道详情
     *
     * @author shaobo
     * @date 2020/04/07
     */
    @RequestMapping("/findFundSaleChannelDetails")
    public Object findFundSaleChannelDetails(String fundCode) {

        FundSaleChannel fundSaleChannel = fundSaleChannelMapper.selectByPrimaryKey(fundCode);

        Map<String, Object> result = new HashMap<>();
        result.put("result", fundSaleChannel);
        return result;

    }

    /**
     * 产品销售渠道分页
     *
     * @author shaobo
     * @date 2020/04/07
     */
    @RequestMapping("/findFundSaleChannelPage")
    public Object findFundSaleChannelPage(Integer currentPage, Integer pageSize, String fundCode, String registerCode) {

        Map<String, Object> params = new HashMap<>();
        params.put("offset", currentPage);
        params.put("pageSize", pageSize);
        params.put("fundCode", fundCode);
        params.put("registerCode", registerCode);
        List<FundSaleChannel> pageList = fundSaleChannelMapper.pageList(params);
        int totalCount = fundSaleChannelMapper.pageListCount(currentPage, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }
}
