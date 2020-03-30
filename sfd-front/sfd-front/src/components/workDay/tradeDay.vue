<template>
  <div class="mod-config">
    <!--表格菜单-->
    <el-form :inline="true" @keyup.enter.native="getDataList()">
      <el-form-item label="登记机构" prop="registerCode">
        <!-- <el-select filterable clearable>
          <el-option
            v-for="item in registerCodeList"
            :key="item.registerCode"
            :label="item.registerCode"
            :value="item.registerCode"
          ></el-option>
        </el-select>-->
      </el-form-item>
      <el-form-item label="产品代码" prop="fundCode">
        <el-input clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="resetForm()">重置</el-button>
        <el-button type="primary" @click="addAndDelete()">修改</el-button>
      </el-form-item>
    </el-form>

    <el-calendar
      style="width: 40%;display:inline-block"
      v-for="item in months"
      :key="item"
      :value="year+'-'+item"
    >
      <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
      <template slot="dateCell" slot-scope="{date, data}">
        <p
          @click="change(data)"
          v-data-init='data'
          :class="{ 'redbgc':tradeDayList[data.day].isred,'greenbgc':tradeDayList[data.day].isgreen } "
        >
          {{ data.day.split('-').slice(1).join('-') }}
        </p>
      </template>
    </el-calendar>
  </div>
</template>

<script>


export default {
  data() {
    return {
      

      testred: true,
      testgreen: false,
      year: 2019,
      months: [
        "01",
        "02",
        "03",
        "04",
        "05",
        "06",
        "07",
        "08",
        "09",
        "10",
        "11",
        "12"
      ],
      tradeDayList: {}
    };
  },
  directives: {
    "data-init": {
      inserted: function(el, binding) {
        const data=binding.value
        //el=document.createElement("p");
        //console.log(day.type=="current-month");
        const type=data.type
        //console.log(type=="current-month");
        const flag=type=="current-month";
        if(!flag){
          //console.log(data.day)
          el.innerHTML='';
          //el=document.createElement("p");
          //el.unbind("click");
        }
        //console.log(binding.value);
        
      }
    }
  },

  //计算属性
  computed: {
   
  },

  watch: {
    //监听tradeDayList数组对象
    tradeDayList: {
      //开启深度监听
      deep: true,
      //监听到变化后的处理
      handler: function(newValue, oldValue) {
        this.$forceUpdate()
      }
    }
  },

  created() {
    const tmpyear = this.year + 1;
    this.initTradeList(this.year - 1 + "-11-01", this.year + 1 + "-01-01");
    
   // console.log(this.tradeDayList)
    
  },
  methods: {
    
    change(item) {
      
      const day = item.day;
      console.log(day);
      const flag = this.tradeDayList[day].flag;
      const isred = this.tradeDayList[day].isred;
      console.log(isred);
      
      if (flag == 0) {
        this.tradeDayList[day].flag = 1;

        this.tradeDayList[day].isred = 1;
        this.tradeDayList[day].isgreen = 0;
      } else if (flag == 1) {
        this.tradeDayList[day].flag = null;

        this.tradeDayList[day].isred = 0;
        this.tradeDayList[day].isdefault = 1;
      } else {
        this.tradeDayList[day].flag = 0;

        this.tradeDayList[day].isdefault = 0;
        this.tradeDayList[day].isgreen = 1;
        
      }
      //this.tradeDayList=new Map(this.tradeDayList);
      // 强制重新渲染页面---数据监听到了,但是页面依然没有重新渲染
      this.$forceUpdate()
      console.log("--------------------");
      // console.log(this.tradeDayList[day].isred)
      // console.log(this.tradeDayList[day].isgreen)
      // console.log(this.tradeDayList[day].isdefault)
    },
    getDate(datestr) {
      var temp = datestr.split("-");
      var date = new Date(temp[0], temp[1], temp[2]);
      return date;
    },
    initTradeList(start, end) {
      
      var startTime = this.getDate(start);
      var endTime = this.getDate(end);
      while (endTime.getTime() - startTime.getTime() >= 1) {
        var year = startTime.getFullYear();
        var month =
          (startTime.getMonth() + 1).toString().length == 1
            ? "0" + (startTime.getMonth() + 1).toString()
            : startTime.getMonth() + 1;
        var day =
          startTime.getDate().toString().length == 1
            ? "0" + startTime.getDate()
            : startTime.getDate();
       
        this.tradeDayList[year + "-" + month + "-" + day] = {
          
          flag: null,
          isred: 0,
          isgreen: 0,
          isdefault: 1
        };
        startTime.setDate(startTime.getDate() + 1);
      }
    },
    //点击查询按钮,查询客户群
    getDataList() {},

    // 新增 / 删除 / 修改
    addAndDelete() {},
    //重置
    resetForm() {},

    // 登记机构
    async initFundCusttype() {}
  }
};
</script>

<style scoped>
.redbgc {
  background: red;
}
.greenbgc {
  background: green;
}
.defaultbgc {
  background: grey;
}
</style>
