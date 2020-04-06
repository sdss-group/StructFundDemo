<template>
  <div>
    <div class="mod-config">
      <!--表格菜单-->
      <el-form :inline="true">
        <el-form-item label="登记机构">
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
          <el-select filterable clearable v-model="fundCode">
            <el-option
              v-for="(item,index) in fundCodeList"
              :key="index"
              :label="item.fundCode"
              :value="item.fundCode"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年份">
          <el-input v-model="year"  @blur="validateYear()"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true">
        <el-form-item label="当前产品工作日">
          <el-select filterable clearable v-model="chooseFlag">
            <el-option value="1" label="全选"></el-option>
            <el-option value="2" label="反选"></el-option>
            <el-option value="3" label="全不选"></el-option>
          </el-select>注：红色为非工作日
        </el-form-item>

        <el-form-item>
          <el-button @click="initTradeDay()" icon="el-icon-search" circle>查询</el-button>
          <el-button @click="resetQuery()" type="primary">重置</el-button>
          <el-button @click="submit()" type="success">提交</el-button>
          <el-button @click="deleteWorkDay()" type="danger">删除</el-button>
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
              @click="changeOne(day)"
            >{{day.day.getDate()}}</span>
            <span
              v-else-if="day.day.getTime()<new Date().getTime()"
              class="past"
            >{{ day.day.getDate() }}</span>
            <span
              v-else
              :class="{curDay:1,isred:day.isred,isgreen:day.isgreen}"
              @click="changeOne(day)"
            >{{ day.day.getDate() }}</span>
          </span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { queryRegList, queryfundList } from "../../common/req";

export default {
  data() {
    return {
      registerCode: "",
      fundCode: "",
      chooseFlag: "",
      registerCodeList: [],
      fundCodeList: [],
      year: new Date().getFullYear(),
      arrDays: []
    };
  },
  created() {
    this.initRegCodeList();
  },
  mounted() {
    this.initData();
  },
  watch: {
    registerCode: async function(newValue, oldValue) {
      if (newValue == "") {
        this.fundCode = "";
        this.fundCodeList = [];
        this.chooseFlag = "";
        return;
      }
      const response = (await queryfundList(newValue)).data;
      this.fundCodeList = response;
      if (!response == []) {
        this.fundCode = response[0].fundCode;
      }
    },
    chooseFlag: function(newValue, oldValue) {
      if (this.chooseFlag == "") {
        return;
      }
      if (this.fundCode == "") {
        this.$alert("请先选择登记机构和产品代码", "提示", {
          confirmButtonText: "确定"
        });
        this.chooseFlag = "";
        return;
      }
      //全选，全部设置为工作日 0-------此处修改了历史数据，但是历史数据并不会提交
      if (this.chooseFlag == 1) {
        for (let monthInfo of this.arrDays) {
          for (let dayInfo of monthInfo.days) {
            if (dayInfo == "") {
              continue;
            }
            dayInfo.flag = 0;
            dayInfo.isgreen = 1;
            dayInfo.isred = 0;
          }
        }
        return;
      }
      //反选，
      if (this.chooseFlag == 2) {
        for (let monthInfo of this.arrDays) {
          for (let dayInfo of monthInfo.days) {
            if (dayInfo == "" || dayInfo.flag == 2) {
              continue;
            }
            if (dayInfo.flag == 0) {
              dayInfo.flag = 1;
              dayInfo.isgreen = 0;
              dayInfo.isred = 1;
            } else {
              dayInfo.flag = 0;
              dayInfo.isgreen = 1;
              dayInfo.isred = 0;
            }
          }
        }
        return;
      }
      //全不选，全部设置为非工作日 1
      if (this.chooseFlag == 3) {
        for (let monthInfo of this.arrDays) {
          for (let dayInfo of monthInfo.days) {
            if (dayInfo == "") {
              continue;
            }
            dayInfo.flag = 1;
            dayInfo.isgreen = 0;
            dayInfo.isred = 1;
          }
        }
        return;
      }
    }
  },

  methods: {
    //验证年份是否正确
    validateYear() {
      let tips=''

      let tmp = Number(this.year);

      if (typeof tmp === "number" && !isNaN(tmp)) {
        if (this.year < 1000 || this.year>3000) {
          tips='年份数据有误'
        } else {
          return;
        }
      } else {
        tips='年份必须为数字'
      }

      this.year=new Date().getFullYear();
      
        this.$alert(tips, "提示", {
          confirmButtonText: "确定"
        });
        
      
      
    },
    //给可以修改的日期添加点击事件，点击修改
    changeOne(item) {
      if (item.flag == 0) {
        item.flag = 1;
        item.isred = 1;
        item.isgreen = 0;
      } else if (item.flag == 1) {
        item.flag = 2;
        item.isred = 0;
        item.isgreen = 0;
      } else if (item.flag == 2) {
        item.flag = 0;
        item.isred = 0;
        item.isgreen = 1;
      }
    },
    //本日及本年后续日期工作日信息全部删除(假删除)
    deleteWorkDay() {
      //弹窗，如果year<本年，提示不能修改历史数据
      if (this.year > new Date().getFullYear) {
        this.$alert("不能删除历史数据", "提示", {
          confirmButtonText: "确定"
        });
        return;
      }
      //检查registerCode/fundCode选项框中是否有值
      if (this.registerCode == "" || this.fundCode == "") {
        this.$alert("请选择登记机构和产品代码", "提示", {
          confirmButtonText: "确定"
        });
        return;
      }

      //todo,弹出提示框：是否要删除从。。。到。。。全部的工作日信息？
      this.$confirm(
        "此操作将删除" + this.year + "年所有的非历史数据, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          //发送请求
          this.$ajax({
            method: "post",

            url:
              "http://" +
              this.$Config.ip +
              ":" +
              this.$Config.port +
              "/tradeDay/deleteWorkDay",

            data: this.$qs.stringify({
              registerCode: this.registerCode,
              fundCode: this.fundCode,
              year: this.year
            })
          })
            .then(response => {
              //刷新查询
              this.initData();
              this.initTradeDay();
              this.chooseFlag = "";
              this.$message({
                type: "success",
                message: "删除成功!"
              });
            })
            .catch(error => {
              // this.chooseFlag="";
              this.$message({
                type: "error",
                message: "删除失败!"
              });
              console.log(error);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });
    },
    //提交数据
    submit() {
      //检查registerCode/fundCode选项框中是否有值
      if (this.registerCode == "" || this.fundCode == "") {
        this.$alert("请选择登记机构和产品代码", "提示", {
          confirmButtonText: "确定"
        });
        return;
      }

      //是否要提交？
      this.$confirm("此操作将提交修改, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          //初始化请求体参数
          let paramList = [];
          paramList = this.initSubmitData();
          //如果当前页面全为往期数据，那么提示往期数据无法修改
          if (paramList.length == 0) {
            this.$alert("当前页面为往期数据，无法修改", "提示", {
              confirmButtonText: "确定"
            });
            return;
          }
          //发送请求
          this.$ajax({
            method: "post",

            url:
              "http://" +
              this.$Config.ip +
              ":" +
              this.$Config.port +
              "/tradeDay/updateWorkDay",
            headers: { "Content-Type": "application/json;charset=UTF-8" },
            data: JSON.stringify(paramList)
          })
            .then(response => {
              //刷新查询
              this.initData();
              this.initTradeDay();
              this.chooseFlag = "";
              this.$message({
                type: "success",
                message: "提交成功!"
              });
            })
            .catch(error => {
              // this.initData();
              // this.initTradeDay();
              // this.chooseFlag="";
              this.$message({
                type: "error",
                message: "提交失败!"
              });
              console.log(error);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });

      //
    },
    //封装提交的请求体内容
    initSubmitData() {
      let paramList = [];
      const currentYear = new Date().getFullYear();
      const currentMonth = new Date().getMonth();
      const currentDate = new Date().getDate();
      //编写请求体内容
      for (let monthInfo of this.arrDays) {
        for (let dayInfo of monthInfo.days) {
          if (dayInfo == "") {
            continue;
          }
          //如果日期是本日以后或者本日
          if (
            dayInfo.day >= new Date() ||
            (dayInfo.day.getFullYear() == currentYear &&
              dayInfo.day.getMonth() == currentMonth &&
              dayInfo.day.getDate() == currentDate)
          ) {
            paramList.push({
              registerCode: this.registerCode,
              fundCode: this.fundCode,
              businessDate: dayInfo.businessDate,
              flag: dayInfo.flag
            });
          }
        }
      }
      return paramList;
    },
    //重置
    resetQuery() {
      this.initData();
      this.registerCode = "";
      this.fundCode = "";
      this.chooseFlag = "";
      this.year = new Date().getFullYear();
    },
    //查询到相关的工作日信息，并赋值
    initTradeDay() {
      //检查registerCode/fundCode选项框中是否有值
      if (this.registerCode == "" || this.fundCode == "") {
        this.$alert("请选择登记机构和产品代码", "提示", {
          confirmButtonText: "确定"
        });
        return;
      }
      //查询出一年的所有数据，但是页面上并不会显示出本日前的数据，方便日后调整
      this.$ajax({
        method: "post",
        url:
          "http://" +
          this.$Config.ip +
          ":" +
          this.$Config.port +
          "/tradeDay/queryTradeDay",
        data: this.$qs.stringify({
          registerCode: this.registerCode,
          fundCode: this.fundCode,
          year: this.year
        })
      })
        .then(response => {
          this.initData();
          this.chooseFlag = "";
          this.updateArrDays(response.data);
        })
        .catch(error => {
          console.log(error);
        });
    },
    /*
      arrDays=[
        {
          month:1
          days:[
            {
              businessDate:'2020-03-11',
              flag
              isred
              isgreen
            },
            {

            }
          ]
        }
      ]


    */
    //给出查询结果对象数组，修改原对象数组arrDays
    updateArrDays(data) {
      for (let responseDateInfo of data) {
        for (let monthInfo of this.arrDays) {
          for (let dayInfo of monthInfo.days) {
            if (dayInfo == "") {
              continue;
            }
            if (dayInfo.businessDate == responseDateInfo.businessDate) {
              dayInfo.flag = responseDateInfo.flag;
              if (dayInfo.flag == 0) {
                dayInfo.isgreen = 1;
              } else if (dayInfo.flag == 1) {
                dayInfo.isred = 1;
              } else {
              }
            }
          }
        }
      }
    },

    //初始化全部页面数据，页面数据并没有具体的工作日信息
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
            businessDate: this.formatDate(
              d.getFullYear(),
              d.getMonth() + 1,
              d.getDate()
            ),

            flag: 2,
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
            businessDate: this.formatDate(
              d.getFullYear(),
              d.getMonth() + 1,
              d.getDate()
            ),

            flag: 2,
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
.today {
  border: 1px solid rgb(8, 12, 240);
}
.isred {
  background: red;
}

.isgreen {
  background: rgb(4, 209, 245);
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

.days li span:hover {
  /* background: #0097FF;
            color: #FFFFFF; */
  font-size: 30px;
  /* color: rgb(247, 23, 7); */
}

.years {
  height: 30px;
  line-height: 30px;
  text-align: left;
  padding: 0 10px;
  color: #0097ff;
}
</style>
