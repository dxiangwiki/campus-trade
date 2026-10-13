<template>
  <div>
    <el-card>
      <el-row :gutter="10" type="flex" align="middle">
        <el-col :span="8">
          <el-input v-model="keyword" placeholder="搜索商品名称" clearable
                    prefix-icon="el-icon-search"
                    @keyup.enter.native="handleSearch" />
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="handleSearch">搜索</el-button>
          <el-button @click="reset">重置</el-button>
        </el-col>
      </el-row>
    </el-card>

    <div style="margin-top:16px">
      <el-empty v-if="list.length === 0" description="暂无商品" />
      <el-row :gutter="16" v-else>
        <el-col :xs="12" :sm="8" :md="6" :lg="6"
                v-for="p in list" :key="p.id">
          <ProductCard :product="p" @buy="handleBuy" />
        </el-col>
      </el-row>
    </div>

    <el-pagination style="margin-top:20px;text-align:right"
                   background layout="prev, pager, next, total"
                   :total="total" :page-size="pageSize"
                   :current-page.sync="pageNo"
                   @current-change="load" />
  </div>
</template>

<script>
import ProductCard from '@/components/ProductCard.vue'
import { getProductPage } from '@/api/product'
import { createOrder } from '@/api/order'

export default {
  components: { ProductCard },
  data() {
    return {
      list: [],
      total: 0,
      pageNo: 1,
      pageSize: 8,
      keyword: ''
    }
  },
  created() { this.load() },
  methods: {
    async load() {
      const res = await getProductPage({
        pageNo: this.pageNo,
        pageSize: this.pageSize,
        keyword: this.keyword
      })
      this.list = res.content
      this.total = res.totalElements
    },
    handleSearch() {
      this.pageNo = 1
      this.load()
    },
    reset() {
      this.keyword = ''
      this.pageNo = 1
      this.load()
    },
    handleBuy(product) {
      this.$confirm(`确认购买「${product.title}」？价格 ¥${product.price}`, '下单确认', {
        confirmButtonText: '确认购买',
        cancelButtonText: '再想想',
        type: 'warning'
      }).then(async () => {
        const user = JSON.parse(localStorage.getItem('user'))
        await createOrder({ productId: product.id, buyerId: user.id })
        this.$message.success('下单成功！去「我的订单」查看')
        this.load()
      }).catch(() => {})
    }
  }
}
</script>