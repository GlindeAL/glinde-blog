<template>
  <div class="edit-container">
    <el-form :model="editForm" :rules="editFormRules" ref="editFormRef">
      <el-form-item label="标题：" prop="title">
        <el-input v-model="editForm.title"></el-input>
      </el-form-item>
      <el-form-item label="摘要：" prop="description">
        <el-input v-model="editForm.description"></el-input>
      </el-form-item>
      <el-form-item label="内容：" prop="content">
        <mavon-editor v-model="editForm.content" ref="md" @imgAdd="imgAdd" @imgDel="imgDel"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EditBlog",
  data(){
    return {
      editForm: {
        title: '',
        description: '',
        content: '',
        author: ''
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
  methods: {
    resetForm(){
      this.$refs.editFormRef.resetFields()
    },
    submitForm(){
      this.$refs.editFormRef.validate(async valid => {
        if(!valid) return;
        this.checkAuth();
        this.editForm.author = window.localStorage.getItem("uid");
        const {data:res} = await this.$http.post("/admin/edit",this.editForm);
        if(res.flag==="success"){
          this.$message.success("操作成功");
          this.resetForm()
        }else{
          this.$message.error("操作失败");
        }
      })
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
        this.$refs.md.$img2Url(pos,axios.defaults.baseURL+res.url);
      }
    },
    imgDel(){
      //delete this.img_file[pos];
    }
  }
}
</script>

<style scoped>
.edit-container {
  width: 75%;
  margin: 0 auto;
}
</style>