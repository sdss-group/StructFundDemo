package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.FundLot;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface FundLotMapper {

    List<FundLot> queryFundLot(Map param);

    Integer count(Map param);

    int delete(List<String> ids);

    FundLot getOne(FundLot fundLot);

    int insert(FundLot fundLot);

    int update(FundLot fundLot);
}
