<template>
  <div class="welcome" :style="{background:backgroundImg}">
    <div class="welcome-box">
      <el-avatar v-bind:src="glindeAvatar" :size="128"></el-avatar>
      <span id="title" @click="openLoginDialog">Welcome</span>
      <span id="words">to Glinde's personal website</span>
      <el-button type="success" round @click="goToHome">Enter</el-button>
    </div>
    <div class="welcome-notice">Under Construction......</div>
    <el-dialog title="登录" :visible.sync="loginDialogVisible" width="50%" @close="resetForm">
      <el-form :model="loginForm" :rules="loginFormRules" ref="loginFormRef" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password"></el-input>
        </el-form-item>
      </el-form>
      <el-button id="loginButton" type="primary" @click="login">登录</el-button>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Welcome",
  data(){
    return{
      backgroundImg: "url("+require('../../public/img/backgroud.jpg')+")",
      glindeAvatar: require('../assets/img/glinde.jpg'),
      registerForm: {
        username: '',
        password: '',
        code: '',
      },
      loginForm: {
        username: '',
        password: ''
      },
      loginFormRules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 15, message: '长度在 3 到 30 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 5, max: 20, message: '长度在 5 到 30 个字符', trigger: 'blur'}
        ]
      },
      loginDialogVisible : false
    }
  },
  methods: {
    goToHome(){
      this.$router.push("/home");
    },
    openLoginDialog(){
      this.loginDialogVisible = true
    },
    resetForm(){
      this.$refs.loginFormRef.resetFields()
    },
    login(){
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return; //验证失败
        const {data:res} = await this.$http.post("login",this.loginForm); //访问后台
        if(res.flag==="success"){
          this.$message.success("操作成功");  //信息提示
          window.localStorage.setItem("token",res.token);  //存储token
          window.localStorage.setItem("username",res.username);
          window.localStorage.setItem("uid",res.uid)
          await this.$router.push({path: "/admin"});
        }else{
          this.$message.error("操作失败")     //错误提示
        }
      });
    }
  }
}
</script>

<style scoped>
.welcome {
  width: 100%;
  height: 100vh;
}
.welcome-box {
  position: fixed;
  width: 600px;
  height: 400px;
  top: 40%;
  left: 50%;
  transform: translate(-50%,-50%);
  background-color: #0f132d;
  border-radius: 20px;
  box-shadow: #0f132d 1px 1px 5px 1px;
}
.el-avatar {
  position: absolute;
  top: 20%;
  left: 50%;
  transform: translate(-50%,-50%);
  border: white 2px solid;
}
span {
  display: block;
  position: absolute;
  width: 100%;
  font-family: Hellowords;
  color: white;
  text-align: center;
}
@font-face{
  font-family: Hellowords;
  src: url('../assets/font/Hellwords2.31.ttf')
}
#title {
  font-size: 80px;
  margin: 140px auto;
}
#words {
  font-size: 50px;
  margin: 240px auto;
}
.el-button {
  position: absolute;
  top: 350px;
  left: 50%;
  transform: translate(-50%,-50%);
  font-size: 25px;
}
.welcome-notice {
  position: absolute;
  width: 400px;
  height: 40px;
  top: 80%;
  left: 50%;
  transform: translate(-50%,-50%);
  padding: 10px;
  background-color: #0f132d;
  border-radius: 20px;
  box-shadow: #0f132d 1px 1px 5px 1px;
  text-align: center;
  font-size: 30px;
  color: white;
}
</style>