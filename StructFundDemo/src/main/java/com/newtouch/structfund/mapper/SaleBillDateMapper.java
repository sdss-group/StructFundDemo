package com.newtouch.structfund.mapper;


import com.newtouch.structfund.entity.SaleBillDate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SaleBillDateMapper {

    public List<SaleBillDate> getList(SaleBillDate exemple);

    int delete(List<SaleBillDate> list);

    public SaleBillDate getOne(SaleBillDate example);

    public int insert(SaleBillDate exemple);

    public int update(SaleBillDate exemple);

}