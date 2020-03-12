package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.FundLot;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface FundLotMapper {

    List<FundLot> list(FundLot fundLot);

    int delete(List<String> ids);

    FundLot getOne(FundLot fundLot);

    int insert(FundLot fundLot);

    int update(FundLot fundLot);
}
