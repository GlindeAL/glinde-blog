<template>
  <div class="admin-blogs-container">
    <div class="left-container">
      <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getBlogList">
        <el-button slot="append" @click="getBlogList">搜索</el-button>
      </el-input>
      <el-pagination
          @size-change=handleSizeChange
          @current-change=handleCurrentChange
          :current-page=queryInfo.pageNum
          :page-sizes="[5, 10, 20, 30]"
          :page-size=queryInfo.pageSize
          layout="prev, pager, next, jumper, sizes, total"
          :total=total>
      </el-pagination>
      <el-card v-for="blog in blogs" v-bind:key="blog.bid">
        <div slot="header">
          <el-button class="title-button" @click="getSingleBlog(blog.bid)">{{blog.title}}</el-button>
          <span>{{blog.date}}</span>
        </div>
        <div>
          <p>id：{{blog.bid}}</p>
          <p>状态：{{blog.state===1?"显示":"隐藏"}}</p>
          {{blog.description}}
        </div>
      </el-card>
      <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getBlogList">
        <el-button slot="append" @click="getBlogList">搜索</el-button>
      </el-input>
      <el-pagination
          @size-change=handleSizeChange
          @current-change=handleCurrentChange
          :current-page=queryInfo.pageNum
          :page-sizes="[5, 10, 20, 30]"
          :page-size=queryInfo.pageSize
          layout="prev, pager, next, jumper, sizes, total"
          :total=total>
      </el-pagination>
    </div>
    <div class="right-container">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef">
        <el-form-item label="标题：" prop="title">
          <el-input v-model="editForm.title"></el-input>
        </el-form-item>
        <el-form-item label="摘要：" prop="description">
          <el-input v-model="editForm.description"></el-input>
        </el-form-item>
        <el-form-item label="内容：" prop="content">
          <br/>
          <mavon-editor v-model="editForm.content" ref="md" @imgAdd="imgAdd" @imgDel="imgDel"></mavon-editor>
        </el-form-item>
        <el-form-item>
          <p>id：{{editForm.bid}}</p>
          <p>状态：{{editForm.state===1?"显示":"隐藏"}}</p>
          <el-button type="primary" icon="el-icon-check" @click="updateBlogState(editForm.bid,1)"></el-button>
          <el-button type="info" icon="el-icon-view" @click="updateBlogState(editForm.bid,0)"></el-button>
          <el-button type="danger" icon="el-icon-delete" @click="deleteBlog(editForm.bid)"></el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateBlog">修改</el-button>
          <el-button @click="submitForm">提交</el-button>
          <el-button type="info" @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminBlogs",
  created() {
    this.getBlogList();
  },
  data() {
    return {
      queryInfo: {
        query: "",   //查询信息
        pageNum: 1,  //当前页
        pageSize: 5  //每页最大数
      },
      blogs: [],
      total: 0,
      editForm: {
        bid: null,
        title: '',
        description: '',
        content: '',
        author: null,
        state: null
      },
      editFormRules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入介绍', trigger: 'blur' },
          { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请选择内容', trigger: 'blur' },
          { min: 1, max: 10000, message: '长度在 1 到 10000 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    async getBlogList(){
      const {data:res} = await this.$http.get("/admin/blog/get",{params:this.queryInfo});
      this.total = res.total;
      this.blogs = res.blogs;
    },
    async getSingleBlog(bid){
      const {data:res} = await this.$http.get("/home/blog/"+bid)
      this.editForm = res.blog

    },
    async imgAdd(pos,$file){
      this.checkAuth();
      let formData = new FormData();
      formData.append('image',$file);
      const {data:res} = await this.$http.post("/admin/uploadimg",formData,{
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      if(res.flag==="success"){
        this.$refs.md.$img2Url(pos,this.$http.defaults.baseURL+res.url);
      }
    },
    imgDel(){
      //delete this.img_file[pos];
    },
    resetForm(){
      this.$refs.editFormRef.resetFields()
    },
    submitForm(){
      this.$refs.editFormRef.validate(async valid => {
        if(!valid) return;
        this.checkAuth()
        this.editForm.author = window.localStorage.getItem("uid");
        this.editForm.bid = null
        const {data:res} = await this.$http.post("/admin/edit",this.editForm);
        if(res.flag==="success"){
          this.$message.success("操作成功");
          this.resetForm();
          await this.getBlogList()
        }else{
          this.$message.error("操作失败");
        }
      })
    },
    updateBlog(){
      this.$refs.editFormRef.validate(async valid => {
        if(!valid) return;
        this.checkAuth()
        this.editForm.author = window.localStorage.getItem("uid");
        const {data:res} = await this.$http.post("/admin/blog/update",this.editForm);
        if(res.flag==="success"){
          this.$message.success("操作成功");
          await this.getBlogList()
        }else{
          this.$message.error("操作失败");
        }
      })
    },
    async deleteBlog(bid){
      this.checkAuth()
      const {data:res} = await this.$http.delete("/admin/blog/delete/"+bid)
      if(res.flag==="success"){
        this.$message.success("操作成功");
        await this.getBlogList();
      }else{
        this.$message.error("操作失败")
      }
    },
    async updateBlogState(bid,state){
      this.checkAuth()
      const {data:res} = await this.$http.post("/admin/blog/state/"+bid+"/"+state)
      if(res.flag==="success"){
        this.$message.success("操作成功");
        await this.getBlogList();
        this.editForm.state = state
      }else{
        this.$message.error("操作失败")
      }
    },
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize;
      this.getBlogList();
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage;
      this.getBlogList()
    }
  }
}
</script>

<style scoped>
.left-container {
  float: left;
  width: 49%;
}
.right-container {
  float: right;
  width: 49%;
}
.el-card {
  margin: 10px 0;
}
span {
  float: right;
  color: grey;
}
.title-button {
  font: 23px black;
  border: none;
}
p {
  font-size: 16px;
  line-height: normal;
}
.search {
  width: 365px;
  margin-right: 10px;
  margin-bottom: 5px;
}
</style>