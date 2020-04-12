package com.newtouch.structfund.action;

import com.newtouch.structfund.entity.TradeDayVo;
import com.newtouch.structfund.mapper.TradeDayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

import java.util.List;

@RestController
@RequestMapping("tradeDay")
public class TradeDayAction {
    @Autowired
    TradeDayMapper tm;
    /*
    查询指定registerCode，fundCode在某一年的全部交易日日信息
     */
    @RequestMapping("queryTradeDay")
    public List<TradeDayVo> queryWorkDay(TradeDayVo param){

        param.setBeginDate(param.getYear()+"-01-01");
        param.setEndDate(param.getYear()+"-12-31");

        System.out.println(param);
        return tm.queryTradeDay(param);
    }
    /*
    工作日维护页面提交数据
     */
    @RequestMapping("updateWorkDay")
    public Integer updateWorkDay(@RequestBody List<TradeDayVo> param){
        for(TradeDayVo tradeDay:param){
            TradeDayVo tradeDayVo = tm.selectOne(tradeDay);
            if(tradeDayVo==null){
                if(!tradeDay.getFlag().equals("2")){
                    tm.insert(tradeDay);
                }
            }else {
                if(!tradeDayVo.getFlag().equals(tradeDay.getFlag())) {
                    tm.update(tradeDay);
                }
            }
        }
        return 0;
    }

    /*
确认日维护页面提交数据
 */
    @RequestMapping("updateConfirmDay")
    public Integer updateConfirmDay(@RequestBody List<TradeDayVo> param){
        for(TradeDayVo tradeDay:param){
            TradeDayVo tradeDayVo = tm.selectOne(tradeDay);
            if(tradeDayVo==null){
                if(!tradeDay.getConfirmDayFlag().equals("2")){
                    tm.insert(tradeDay);
                }
            }else {
                if(!tradeDayVo.getConfirmDayFlag().equals(tradeDay.getConfirmDayFlag())) {
                    tm.update(tradeDay);
                }
            }
        }
        return 0;
    }

    /*
    工作日信息批量删除
     */
    @RequestMapping("deleteWorkDay")
    public Integer deleteWorkDay(TradeDayVo param){

        System.out.println(param);
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        String dayString=day>9?(day+""):("0"+day);
        int month = cal.get(Calendar.MONTH) + 1;
        String monthString=month>9?(month+""):("0"+month);
        int year = cal.get(Calendar.YEAR);


        //如果是本年
        if(param.getYear().equals(year+"")){
            param.setBeginDate(year+"-"+monthString+"-"+dayString);

            param.setEndDate(param.getYear()+"-12-31");

            tm.deleteWorkDay(param);

        //如果>本年
        }else if(Integer.parseInt(param.getYear())>year){
            param.setBeginDate(param.getYear()+"-01-01");
            param.setEndDate(param.getYear()+"-12-31");
            tm.deleteWorkDay(param);
        }

        return 0;
    }

    /*
        确认日信息批量删除
         */
    @RequestMapping("deleteConfirmDay")
    public Integer deleteConfirmDay(TradeDayVo param){
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        String dayString=day>9?(day+""):("0"+day);
        int month = cal.get(Calendar.MONTH) + 1;
        String monthString=month>9?(month+""):("0"+month);
        int year = cal.get(Calendar.YEAR);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        //如果是本年
        if(param.getYear().equals(year+"")){
            param.setBeginDate(year+"-"+monthString+"-"+dayString);
            param.setEndDate(param.getYear()+"-12-31");
            System.out.println("-----------delete---------------------");
            tm.deleteConfirmDay(param);
            System.out.println(param);
            System.out.println("--------------------------------");
            //如果>本年
        }else if(Integer.parseInt(param.getYear())>year){
            param.setBeginDate(param.getYear()+"-01-01");
            param.setEndDate(param.getYear()+"-12-31");
            tm.deleteConfirmDay(param);
        }

        return 0;
    }


}
