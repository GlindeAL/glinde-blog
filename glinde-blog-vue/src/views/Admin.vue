<template>
  <div class="admin-container">
    <AdminHeader></AdminHeader>
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import AdminHeader from "@/components/AdminHeader";
export default {
  name: "Admin",
  components: {AdminHeader}
}
</script>

<style scoped>
.admin-container {
  background-color: #e9edf1;
}
.el-main {
  min-height: 1080px;
  height: auto;
  margin: 20px 20px 20px 50px;
  overflow: visible;
}
</style>