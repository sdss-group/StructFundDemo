<template >
  <div style="height: auto;">
    <hr />
    <el-container style="height: 100%; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu :default-openeds="openeds" :default-active="this.$route.path" router mode="vertical">
            <el-submenu index="1">
                <template slot="title"><i class="el-icon-message"></i>产品信息</template>
                <el-menu-item v-for="(item,i) in baseinfoNavList" :key="i" :index="item.index">
                  {{ item.name }}
                </el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title"><i class="el-icon-menu"></i>销售管理</template>
                <el-menu-item v-for="(item,i) in sailmanaNavList" :key="i" :index="item.index">
                  {{ item.name }}
                </el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title"><i class="el-icon-setting"></i>业务查询</template>
                <el-menu-item v-for="(item,i) in busisearNavList" :key="i" :index="item.index">
                  {{ item.name }}
                </el-menu-item>
            </el-submenu>
            </el-menu>
        </el-aside>
      <el-main>
        <el-router-view>

        </el-router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {
      openeds: ['1', '2', '3'],
      uniqueOpened: false,
      baseinfoNavList: [
        {name: '管理人产品基本信息', index: '/baseinfo'},
        {name: '产品销售参数信息维护', index: '/fundsale'},
        {name: '产品批次设置', index: '/fundLot'},
        {name: '产品工作日维护', index: '/workDay'},
        {name: '产品确认日维护', index: '/confirmDay'},
        {name: '产品客户群维护', index: '/fundCustType'},
        {name: '产品到账日信息维护', index: '/saleBillDate'},
        {name: '费用参数维护', index: '/saleBillDate'}
      ],
      sailmanaNavList: [
        {name: '总行销售额度控制维护', index: '/orgAmt'},
        {name: '产品销售渠道维护', index: '/fundSaleChannel'},
        {name: '产品销售分行维护', index: '/saleBranch'}
      ],
      busisearNavList: [
        {name: '账户类交易查询', index: '/accountTrans'},
        {name: '交易类交易查询', index: '/transTrans'}
      ]
    }
  },
  methods: {

  }
}
</script>
<style type="text/css">
  .el-submenu__title{
    color: #3a8ee6;
  }
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
</style>
