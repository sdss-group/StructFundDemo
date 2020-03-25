package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.FundCusttype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FundCusttypeMapper {

    List<FundCusttype> listAll();

    List<String> listTypeByReCodeAndFuCode(@Param("registerCode") String registerCode, @Param("fundCode")String fundCode);

    int insert(@Param("registerCode") String registerCode, @Param("fundCode")String fundCode,@Param("custType")String[] custType );

    int delete(@Param("registerCode") String registerCode, @Param("fundCode")String fundCode,@Param("toDelete")String[] toDelete );
}
