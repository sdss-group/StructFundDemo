package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.FundLot;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface FundLotMapper {

    List<FundLot> queryFundLot(Map param);

    List<FundLot> selectSingleData(FundLot fundLot);

    Integer count(Map param);

    int delete(List<FundLot> rows);

    int insertSelective(FundLot fundLot);

    int update(FundLot fundLot);
}
