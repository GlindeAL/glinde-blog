<template>
  <nav>
    <ul>
      <li><router-link id="blogs" to="/home/blogs" exact>博客</router-link></li>
      <li><router-link id="mailbox" to="/home/mailbox" exact>匿名信箱</router-link></li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "HeaderLink"
}
</script>

<style scoped>
ul {
  list-style-type: none;
  text-align: center;
  margin: 0 auto;
}
li {
  display: inline-block;
  margin: 8px 15px;
}
a {
  display: inline-block;
  font-size: 30px;
  color: white;
  text-decoration: none;
  border-radius: 10px;
  transition: background-color 0.15s;
}
#blogs {
  width: 120px;
}
#mailbox {
  width: 160px;
}
.router-link-active {
  background-color: #6a839f;
}
</style>