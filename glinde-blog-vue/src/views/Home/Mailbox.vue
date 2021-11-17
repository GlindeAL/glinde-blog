<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item to="/home">首页</el-breadcrumb-item>
      <el-breadcrumb>匿名信箱</el-breadcrumb>
    </el-breadcrumb>
    <el-form :model="mailboxForm" :rules="mailboxFormRules" ref="mailboxFormRef">
      <el-form-item label="匿名信（经过审核才能展示）" prop="content">
        <el-input type="textarea" :rows="6" v-model="mailboxForm.content"></el-input>
      </el-form-item>
      <el-form-item label="是否公开：" prop="state">
        <el-checkbox v-model="mailboxForm.state" true-label="0" false-label="2" checked>是</el-checkbox>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitMail">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
    <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getAllMails">
      <el-button slot="append" @click="getAllMails">搜索</el-button>
    </el-input>
    <el-pagination
        @size-change=handleSizeChange
        @current-change=handleCurrentChange
        :current-page=queryInfo.pageNum
        :page-sizes="[10, 15, 25, 40]"
        :page-size=queryInfo.pageSize
        layout="prev, pager, next, jumper, sizes, total"
        :total=total>
    </el-pagination>
    <div class="mails-container" v-loading="loading">
      <el-card v-for="mail in mails" :key="mail.mid">
        <div slot="header">
          <span :style="'color:grey'">{{mail.date}}</span>
        </div>
        <article>{{mail.content}}</article>
      </el-card>
    </div>
    <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getAllMails">
      <el-button slot="append" @click="getAllMails">搜索</el-button>
    </el-input>
    <el-pagination
        @size-change=handleSizeChange
        @current-change=handleCurrentChange
        :current-page=queryInfo.pageNum
        :page-sizes="[10, 15, 25, 40]"
        :page-size=queryInfo.pageSize
        layout="prev, pager, next, jumper, sizes, total"
        :total=total>
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "Mailbox",
  created() {
    this.getAllMails();
  },
  data(){
    return {
      mailboxForm: {
        content: '',
        state: 0
      },
      mails: [],
      mailboxFormRules: {
        content: [
          { required: true, message: '请输入匿名信内容', trigger: 'blur' },
          { min: 1, max: 300, message: '长度在 1 到 300 个字符', trigger: 'blur' }
        ],
      },
      queryInfo: {
        query: "",   //查询信息
        pageNum: 1,  //当前页
        pageSize: 15  //每页最大数
      },
      total: 0,
      loading: true
    }
  },
  methods: {
    resetForm(){
      this.$refs.mailboxFormRef.resetFields()
    },
    async getAllMails(){
      const {data:res} = await this.$http.get("/home/mailbox/get",{params:this.queryInfo})
      this.mails = res.mails
      this.total = res.total
      if(this.mails.length!==0){
        this.loading = false
      }
    },
    submitMail(){
      this.$refs.mailboxFormRef.validate(async valid => {
        if(!valid) return;
        const {data:res} = await this.$http.post("/home/mailbox/submit",this.mailboxForm)
        if(res.flag==="success"){
          this.$message.success("提交成功")
          this.resetForm()
          await this.getAllMails();
        }else{
          this.$message.error("提交失败")
        }
      })
    },
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize;
      this.getAllMails();
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage;
      this.getAllMails()
    }
  }
}
</script>

<style scoped>
.el-form{
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
}
.el-card {
  margin-top: 20px;
}
.mails-container{
  width: inherit;
  min-height: 250px;
  height: auto;
}
.search {
  float: left;
  width: 365px;
  margin-right: 10px;
}
</style>