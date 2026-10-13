<template>
  <el-row :gutter="20">
    <el-col :span="8">
      <el-card>
        <div style="text-align:center">
          <el-avatar :size="80" :src="user.avatar || defaultAvatar" />
          <h3 style="margin-top:12px">{{ user.nickname || user.username }}</h3>
          <p style="color:#999">ID: {{ user.id }}</p>
        </div>
      </el-card>
    </el-col>
    <el-col :span="16">
      <el-card>
        <h3 style="margin-bottom:16px">👤 个人信息</h3>
        <el-descriptions :column="1" border>
          <el-descriptions-item label="用户名">{{ user.username }}</el-descriptions-item>
          <el-descriptions-item label="昵称">{{ user.nickname || '-' }}</el-descriptions-item>
          <el-descriptions-item label="手机号">{{ user.phone || '-' }}</el-descriptions-item>
          <el-descriptions-item label="注册时间">{{ user.createTime }}</el-descriptions-item>
        </el-descriptions>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      user: JSON.parse(localStorage.getItem('user') || '{}'),
      defaultAvatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
    }
  }
}
</script>