package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.OrgAmt;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrgAmtMapper {

    List<OrgAmt> querySaleAmtInfo(Map<String, Object> param);

    List<Map> querySaledAmt(OrgAmt item);

    int countOrgAmt(Map<String, Object> param);

    OrgAmt selectByPrimaryKey(OrgAmt item);

    int insert(OrgAmt item);

    int update(OrgAmt item);
}
