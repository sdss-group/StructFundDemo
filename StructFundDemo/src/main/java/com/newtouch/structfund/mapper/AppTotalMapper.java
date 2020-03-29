package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.AppTotal;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface AppTotalMapper {

    List<AppTotal> queryAccountTrans(Map<String, Object> param);

    List<AppTotal>  queryAccountTransOut(Map<String, Object> param);

    List<AppTotal> queryTransTrans(Map<String, Object> param);

    List<AppTotal> queryTransTransOut(Map<String, Object> param);

    int sumAccountTrans(Map<String, Object> param);

    int sumTransTrans(Map<String, Object> param);
}
