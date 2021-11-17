<template>
  <div>
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
    <div class="blogs-container" v-loading="this.loading">
      <el-card v-for="blog in blogs" v-bind:key="blog.bid">
        <div slot="header">
          <router-link :to="'/home/blog/'+blog.bid">{{blog.title}}</router-link>
          <span>{{blog.date}}</span>
        </div>
        <div>
          {{blog.description}}
        </div>
      </el-card>
    </div>
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
</template>

<script>
export default {
  name: "Blogs",
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
      blogs:[],
      total: 0,
      loading: true
    }
  },
  methods: {
    async getBlogList(){
      const {data:res} = await this.$http.get("/home/blogs",{params:this.queryInfo});
      this.total = res.total;
      this.blogs = res.blogs;
      if(this.blogs.length!==0){
        this.loading = false
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
a {
  font-size: 30px;
}
span {
  float: right;
  color: grey;
}
.el-card {
  margin-bottom: 10px;
}
.blogs-container {
  min-height: 250px;
  height: auto;
}
.search {
  float: left;
  width: 365px;
  margin-right: 10px;
  margin-bottom: 5px;
}
</style>