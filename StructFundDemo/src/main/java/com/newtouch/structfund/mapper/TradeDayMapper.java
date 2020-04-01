package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.TradeDayVo;

import java.util.List;

public interface TradeDayMapper {
    //根据registerCode和fundCode查询出一年的工作日，确认日信息
    public List<TradeDayVo> queryTradeDay(TradeDayVo param);
    //根据registerCode，fundCode，businessDate查询出单条记录
    public TradeDayVo selectOne(TradeDayVo param);


    //插入数据
    public Integer insert(TradeDayVo param);

    //更新和假删除
    public Integer update(TradeDayVo param);

    //批量假删除工作日信息
    public Integer deleteWorkDay(TradeDayVo param);
    //批量假删除确认日信息
    public Integer deleteConfirmDay(TradeDayVo param);
}
