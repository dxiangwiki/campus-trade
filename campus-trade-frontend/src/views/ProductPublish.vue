<template>
  <el-card style="max-width:640px;margin:0 auto">
    <h3 style="margin-bottom:20px">📤 发布商品</h3>
    <el-form :model="form" :rules="rules" ref="form" label-width="90px">
      <el-form-item label="标题" prop="title">
        <el-input v-model="form.title" placeholder="例如：二手自行车" />
      </el-form-item>
      <el-form-item label="分类" prop="category">
        <el-select v-model="form.category" placeholder="请选择分类" style="width:100%">
          <el-option v-for="c in categories" :key="c" :label="c" :value="c" />
        </el-select>
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input-number v-model="form.price" :min="0" :precision="2"
                         :step="1" style="width:200px" />
        <span style="margin-left:10px;color:#999">元</span>
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input type="textarea" v-model="form.description" :rows="4"
                  placeholder="描述一下商品的成色、使用情况..." />
      </el-form-item>
      <el-form-item label="图片URL">
        <el-input v-model="form.image" placeholder="选填，粘贴图片链接" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">发布</el-button>
        <el-button @click="$refs.form.resetFields()">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import { publishProduct } from '@/api/product'

export default {
  data() {
    return {
      categories: ['图书', '数码', '生活用品', '交通工具', '运动', '其他'],
      form: { title: '', category: '', price: 0, description: '', image: '' },
      rules: {
        title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
        category: [{ required: true, message: '请选择分类', trigger: 'change' }],
        price: [{ required: true, message: '请输入价格', trigger: 'blur' }],
        description: [{ required: true, message: '请输入描述', trigger: 'blur' }]
      }
    }
  },
  methods: {
    submit() {
      this.$refs.form.validate(async valid => {
        if (!valid) return
        const user = JSON.parse(localStorage.getItem('user'))
        await publishProduct({ ...this.form, sellerId: user.id })
        this.$message.success('发布成功！')
        this.$router.push('/products')
      })
    }
  }
}
</script>