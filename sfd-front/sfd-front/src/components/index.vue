<template >
  <div style="height: 1000px;">
    <hr />
    <el-container style="height: 100%; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu :default-openeds="['1']">
            <el-submenu index="1">
                <template slot="title"><i class="el-icon-message"></i>导航一</template>
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
                <el-menu-item index="1-3">选项3</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title"><i class="el-icon-menu"></i>导航二</template>
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
                <el-menu-item index="1-3">选项3</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title"><i class="el-icon-setting"></i>导航三</template>
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
                <el-menu-item index="1-3">选项3</el-menu-item>
            </el-submenu>
            </el-menu>
        </el-aside>
    </el-container>
<router-view/>
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {

    }
  },
  methods: {
      
  }
}
</script>
