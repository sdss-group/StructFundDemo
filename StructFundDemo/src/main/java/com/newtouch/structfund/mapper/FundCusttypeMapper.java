package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.FundCusttype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface FundCusttypeMapper {

    List<FundCusttype> listAll();

    Set<String> listTypeByReCodeAndFuCode(@Param("registerCode") String registerCode, @Param("fundCode")String fundCode);

    int insert(@Param("registerCode") String registerCode, @Param("fundCode")String fundCode,@Param("toAdd")Set<String> toAdd );

    int delete(@Param("registerCode") String registerCode, @Param("fundCode")String fundCode,@Param("toDelete")Set<String> toDelete );

    int deleteAll(@Param("registerCode") String registerCode, @Param("fundCode")String fundCode );
}
