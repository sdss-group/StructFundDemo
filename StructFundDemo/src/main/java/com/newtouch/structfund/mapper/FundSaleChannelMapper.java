package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.FundSaleChannel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FundSaleChannelMapper {

    int insert(FundSaleChannel fundSaleChannel);

    int delete(String id);

    int update(FundSaleChannel fundSaleChannel);

    FundSaleChannel selectByPrimaryKey(String id);

    List<FundSaleChannel> pageList(Map<String, Object> params);

    int pageListCount(int offset, int pageSize);

    int batchDelete(List<FundSaleChannel> fundSaleChannelList);

}
