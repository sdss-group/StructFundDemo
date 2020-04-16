package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.SaleBranchDate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleBranchDateMapper {

    List<SaleBranchDate> getBranch(String registerCode, String fundCode);

    List<SaleBranchDate> queryBranch(String registerCode, String fundCode);

    int delete(String registerCode, String fundCode);

    List<SaleBranchDate> findAllBranch();

    void save(SaleBranchDate sbd);
}
