<template>
  <el-container style="height:100vh">
    <el-header style="background:#409EFF;color:#fff;display:flex;align-items:center;padding:0 20px">
      <h3 style="margin-right:40px;white-space:nowrap">🎓 校园二手交易</h3>
      <el-menu mode="horizontal" :default-active="$route.path" router
               background-color="#409EFF" text-color="#fff"
               active-text-color="#ffd04b" style="flex:1;border:none">
        <el-menu-item index="/products">商品列表</el-menu-item>
        <el-menu-item index="/publish">发布商品</el-menu-item>
        <el-menu-item index="/orders">我的订单</el-menu-item>
        <el-menu-item index="/user">个人中心</el-menu-item>
      </el-menu>
      <span style="margin-right:12px">{{ user.nickname || user.username }}</span>
      <el-button size="small" @click="logout">退出</el-button>
    </el-header>

    <el-main style="background:#f5f7fa">
      <router-view />
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      user: JSON.parse(localStorage.getItem('user') || '{}')
    }
  },
  methods: {
    logout() {
      this.$confirm('确认退出登录？', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        localStorage.removeItem('user')
        this.$router.push('/login')
        this.$message.success('已退出')
      }).catch(() => {})
    }
  }
}
</script>