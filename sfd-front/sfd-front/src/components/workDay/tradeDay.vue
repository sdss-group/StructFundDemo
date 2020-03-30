<template>
  <div>
    <div class="mod-config">
      <!--表格菜单-->
      <el-form :inline="true" >
        <el-form-item label="登记机构"  >
          <el-select filterable clearable v-model="registerCode">
          <el-option
            v-for="item in registerCodeList"
            :key="item.registerCode"
            :label="item.registerName"
            :value="item.registerCode"
          >{{item.registerName}}</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="产品代码">
          <el-select filterable clearable  v-model="fundCode">
          <el-option
            v-for="(item,index) in fundCodeList"
            :key="index"
            :label="item.fundCode"
            :value="item.fundCode"
          ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年份">
           <el-input v-model="year" clearable></el-input>
       </el-form-item>
      </el-form>
      <el-form :inline="true" >
        <el-form-item label="当前产品工作日">
          <el-select filterable clearable  v-model="chooseFlag">
            <el-option value=1 label="全选"></el-option>
            <el-option value=2 label="反选"></el-option>
            <el-option value=3 label="全不选"></el-option>
          </el-select>
          注：红色为非工作日
        </el-form-item>
        
        <el-form-item>
          <el-button @click="getDataList()"  icon="el-icon-search" circle>查询</el-button>
          <el-button type="primary" @click="resetForm()">重置</el-button>
          <el-button type="success" @click="addAndDelete()">提交</el-button>
          <el-button type="danger" @click="addAndDelete()">删除</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div v-for="(dayList,index) in arrDays" :key="index" class="calendar">
      <div class="years">{{dayList.year}}年{{dayList.moth}} 月</div>
      <ul class="weekdays">
        <li>一</li>
        <li>二</li>
        <li>三</li>
        <li>四</li>
        <li>五</li>
        <li style="color:red">六</li>
        <li style="color:red">日</li>
      </ul>
      <ul class="days">
        <li v-for="(day,index) in dayList.days" :key="index">
          <span v-if="day!=''">
            <span
              v-if="day.day.getFullYear() == new Date().getFullYear() 
                                     && day.day.getMonth() == new Date().getMonth() 
                                     && day.day.getDate() == new Date().getDate()"
              :class="{curDay:1,today:1,isred:day.isred,isgreen:day.isgreen}"
            >{{day.day.getDate()}}</span>
            <span
              v-else-if="day.day.getTime()<new Date().getTime()"
              class="past"
            >{{ day.day.getDate() }}</span>
            <span
              v-else
              :class="{curDay:1,isred:day.isred,isgreen:day.isgreen}"
            >{{ day.day.getDate() }}</span>
          </span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>

import {queryRegList,queryfundList} from "../../common/req"


export default {
  data() {
    return {
      registerCode:'',
      fundCode:'',
      chooseFlag:null,
      registerCodeList:[],
      fundCodeList:[],
      year: 2020,
      arrDays: []
    };
  },
  created() {
    this.initRegCodeList();
  },
  mounted() {
    this.initData();
  },
 watch:{
     registerCode :async function(newValue,oldValue){
       if(newValue==''){
         this.fundCode=''
         this.fundCodeList=[]
         return;
       }
       const response=(await queryfundList(newValue)).data;
       this.fundCodeList=response
       if(!response==[]){
         this.fundCode=response[0].fundCode
       }
     }
 },



  methods: {
    changeyear() {
      this.initData();
    },
    initData() {
      this.arrDays = [];

      for (var i = 1; i <= 12; i++) {
        if (i < 10) {
          this.initMonthArr(this.year + "-0" + i + "-01");
        } else {
          this.initMonthArr(this.year + "-" + i + "-01");
        }
      }
    },

    //把日期传进去，从而获取该日期所属月份的各种数据，可直接用于模板遍历
    //数据存储在this.arrDays
    initMonthArr(cur) {
      //cur是形如'2020-03-02'这样的字符串
      // var self = this
      var date = new Date(cur);

      var currentDay = date.getDate();
      var currentYear = date.getFullYear();
      var currentMonth = date.getMonth() + 1;
      var currentWeek = date.getDay(); // 1...6,0

      if (currentWeek == 0) {
        //星期天
        currentWeek = 7;
      }
      //var str = this.formatDate(currentYear, currentMonth, currentDay);
      var arr = [];

      var str = this.formatDate(currentYear, currentMonth, currentDay);
      //this.days.length = 0;
      // 今天是周日，放在第一行第7个位置，前面6个
      for (var i = currentWeek - 1; i >= 0; i--) {
        var d = new Date(str);
        //d.setDate(d.getDate() - i);
        if (i == 0) {
          arr.push({
            day: d,
            flag: "",
            isred: 0,
            isgreen: 0
          });
        } else {
          arr.push("");
        }
      }

      for (var i = 1; i <= 42 - currentWeek; i++) {
        var d = new Date(str);
        d.setDate(d.getDate() + i);
        if (currentMonth == d.getMonth() + 1) {
          arr.push({
            day: d,
            flag: "",
            isred: 0,
            isgreen: 0
          });
        }
      }
      //obj中{2020currentYear,3currentMonth,用于遍历的数组arr}
      var obj = {};
      obj.moth = currentMonth;
      obj.days = arr;
      obj.year = currentYear;
      this.arrDays.push(obj);
    },
    //将数字形式的年月日，生成‘year-month-day’形式的字符串
    formatDate(year, month, day) {
      var y = year;
      var m = month;
      if (m < 10) m = "0" + m;
      var d = day;
      if (d < 10) d = "0" + d;
      return y + "-" + m + "-" + d;
    },
    // 登记机构
    async initRegCodeList() {
      this.registerCodeList = [];
      this.registerCodeList = (await queryRegList()).data;
    }
    
  }
};
</script>

<style scoped>
.calendar {
  display: inline-block;
  width: 30%;
  margin-left: 10%;
}
.past {
  background: rgba(116, 113, 113, 0.116);
}
.today{
  border: 1px solid rgb(8, 12, 240);
}
.isred {
  background: red;
}

.isgreen {
  background: green;
}



.weekdays {
  margin: 0;
  padding: 10px 0;
  background: #eef1f6;
  display: flex;
  flex-wrap: wrap;
  color: #ffffff;
  justify-content: space-around;
  width: 100%;
  color: #374453;
  padding-right: 17px;
  background: #eef1f6;
}

.weekdays li {
  display: inline-block;
  width: 13.6%;
  text-align: center;
}

.days {
  padding: 0;
  background: #ffffff;
  margin: 0;
  display: flex;
  /* display:in-line; */
  flex-wrap: wrap;
  height: 100%;
  /* width: 420px; */
  width: 100%;
  
}

.days li {
  list-style-type: none;
  display: inline-block;
  width: 14.2%;
  text-align: center;
  font-size: 1rem;
  
}

.days li span {
  display: inline-block;
  color: #000;
  cursor: pointer;
  vertical-align: middle;
  height: 50px;
  line-height: 50px;
  width: 50px;
  /* border:2px solid white; */
  border-radius: 25px;
  
}


/* .days li span .active {
            width: 50px;
            border-radius: 25px;
            background: #0097FF;
            color: #fff;
            display: inline-block;

        } */

.days li span:hover {
  /* background: #0097FF;
            color: #FFFFFF; */

  color: rgb(247, 23, 7);
}

.years {
  height: 30px;
  line-height: 30px;
  text-align: left;
  padding: 0 10px;
  color: #0097ff;
}
</style>
