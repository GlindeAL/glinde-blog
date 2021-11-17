<template>
  <div class="aside-container">
    <el-avatar v-bind:src="glindeAvatar" :size="128"></el-avatar>
    <span>Glinde</span>
    <p>主要学习后端开发<br>也了解一些前端知识</p>
    <i :class="'iconfont icon-bilibili'" @click="goToBilibili"></i>
    <i :class="'iconfont icon-leetcode'" @click="goToLeetcode"></i>
    <i :class="'iconfont icon-csdnlogo'" @click="goToCSDN"></i>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data(){
    return {
      glindeAvatar: require('../assets/img/glinde.jpg')
    }
  },
  methods: {
    goToBilibili(){
      window.location.href = "https://space.bilibili.com/4211186";
    },
    goToLeetcode(){
      window.location.href = "https://leetcode-cn.com/u/glindeAZ/";
    },
    goToCSDN(){
      window.location.href = "https://blog.csdn.net/Glinde?spm=1001.2101.3001.5343";
    }
  }
}
</script>

<style scoped>
.aside-container {
  position: relative;
  height: 700px;
  background-color: #6a839f;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.el-avatar {
  position: absolute;
  top: 15%;
  left: 50%;
  transform: translate(-50%,-50%);
  border: white 2px solid;
}
span {
  display: block;
  position: absolute;
  top: 35%;
  left: 50%;
  transform: translate(-50%,-50%);
  font-family: Hellowords;
  font-size: 60px;
  color: white;
}
p {
  display: block;
  position: absolute;
  width: 100%;
  top: 45%;
  left: 50%;
  transform: translate(-50%,-50%);
  text-align: center;
  font-size: 25px;
  line-height: 40px;
  color: white;
}
.iconfont {
  position: absolute;
  left: 50%;
  transform: translate(-50%,-50%);
  font-size: 40px;
  color: white;
}
.iconfont:hover {
  cursor: pointer;
}
.icon-bilibili {
  top: 67%;
}
.icon-leetcode {
  top: 77%;
}
.icon-csdnlogo {
  top: 87%;
  font-size: 30px;
}
</style>