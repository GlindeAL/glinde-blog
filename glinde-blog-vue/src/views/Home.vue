<template>
  <el-container class="home-container">
    <el-header>
      <span class="header-title">Glinde的个人网站</span>
      <HeaderLink></HeaderLink>
    </el-header>
    <el-container class="main-container">
      <el-aside>
        <Aside></Aside>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Aside from "@/components/Aside";
import HeaderLink from "@/components/HeaderLink";
export default {
  name: "Home",
  components: {
    HeaderLink,
    Aside
  }
}
</script>

<style scoped>
.home-container {
  background: #e9edf1;
}
.main-container {
  padding: 0 120px;
}
.el-aside {
  margin: 20px 0 20px 20px;
}
.el-header {
  background-color: #3c4856;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.el-main {
  min-height: 1080px;
  height: auto;
  margin: 20px 20px 20px 50px;
  overflow: visible;
}
p {
  word-break: break-word;
}
.header-title {
  display: block;
  float: left;
  margin-top: 7px;
  font-size: 30px;
  color: white;
}
</style>