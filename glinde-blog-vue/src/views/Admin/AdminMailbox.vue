<template>
  <div class="admin-mailbox-container">
    <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getAdminMails">
      <el-button slot="append" @click="getAdminMails">搜索</el-button>
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
    <div class="admin-mails-container">
      <el-card v-for="mail in mails" v-bind:key="mail.mid">
        <div slot="header">
          <el-button type="primary" icon="el-icon-check" @click="updateMailState(mail.mid,1)" circle></el-button>
          <el-button type="success" icon="el-icon-minus" @click="updateMailState(mail.mid,3)" circle></el-button>
          <el-button type="info" icon="el-icon-view" @click="updateMailState(mail.mid,2)" circle></el-button>
          <el-button type="danger" icon="el-icon-delete" @click="deleteMail(mail.mid)" circle></el-button>
          <span class="mail-id">id : {{mail.mid}}</span>
          <span class="mail-state">状态：{{mail.state}}</span>
          <span class="mail-date" :style="'color:grey'">{{mail.date}}</span>
        </div>
        <article>{{mail.content}}</article>
      </el-card>
    </div>
    <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getAdminMails">
      <el-button slot="append" @click="getAdminMails">搜索</el-button>
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
  name: "AdminMailbox",
  created(){
    this.getAdminMails()
  },
  data(){
    return{
      mails: [],
      queryInfo: {
        query: "",   //查询信息
        pageNum: 1,  //当前页
        pageSize: 15  //每页最大数
      },
      total: 0
    }
  },
  methods:{
    async getAdminMails(){
      const {data:res} = await this.$http.get("/admin/mailbox/get",{params:this.queryInfo})
      for(let i=0;i<res.mails.length;i++){
        if(res.mails[i].state===1){
          res.mails[i].state="显示"
        }else if(res.mails[i].state===0){
          res.mails[i].state="等待审核"
        }else if(res.mails[i].state===2){
          res.mails[i].state="私人"
        }else{
          res.mails[i].state="不过审"
        }
      }
      console.log(res.mails)
      this.total = res.total;
      this.mails = res.mails
    },
    async updateMailState(mid,state){
      this.checkAuth()
      const {data:res} = await this.$http.post("/admin/mailbox/state/"+mid+"/"+state)
      if(res.flag==="success"){
        this.$message.success("操作成功");
        await this.getAdminMails();
      }else{
        this.$message.error("操作失败")
      }
    },
    async deleteMail(mid){
      this.checkAuth()
      const {data:res} = await this.$http.delete("/admin/mailbox/delete/"+mid)
      if(res.flag==="success"){
        this.$message.success("操作成功");
        await this.getAdminMails();
      }else{
        this.$message.error("操作失败")
      }
    },
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize;
      this.getAdminMails()
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage;
      this.getAdminMails();
    }
  }
}
</script>

<style scoped>
.admin-mailbox-container {
  width: 60%;
  margin: 0 auto;
}
.el-card {
  margin: 8px auto;
}
.mail-date {
  float: right;
}
.mail-state {
  margin-left: 32px;
}
.mail-id {
  margin-left: 32px;
}
.search {
  float: left;
  width: 365px;
  margin-right: 10px;
  margin-bottom: 5px;
}
</style>