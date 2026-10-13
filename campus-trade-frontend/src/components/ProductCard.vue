<template>
  <el-card shadow="hover" :body-style="{ padding: '0' }" style="margin-bottom:16px">
    <img :src="product.image || defaultImg"
         style="width:100%;height:180px;object-fit:cover" />
    <div style="padding:12px">
      <div style="font-weight:bold;font-size:15px;margin-bottom:6px;
                  white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
        {{ product.title }}
      </div>
      <div style="color:#999;font-size:12px;height:36px;overflow:hidden;
                  text-overflow:ellipsis;display:-webkit-box;
                  -webkit-line-clamp:2;-webkit-box-orient:vertical">
        {{ product.description }}
      </div>
      <div style="display:flex;justify-content:space-between;align-items:center;margin-top:10px">
        <span style="color:#F56C6C;font-size:20px;font-weight:bold">
          ¥{{ product.price }}
        </span>
        <span style="font-size:12px;color:#888">{{ product.sellerName }}</span>
      </div>
      <el-button type="primary" size="small" style="margin-top:10px;width:100%"
                 @click="$emit('buy', product)">立即购买</el-button>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'ProductCard',
  props: { product: { type: Object, required: true } },
  data() {
    return {
      defaultImg: 'https://via.placeholder.com/300x180?text=No+Image'
    }
  }
}
</script>