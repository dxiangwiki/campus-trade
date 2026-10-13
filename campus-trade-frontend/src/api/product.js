import request from '@/utils/request'

export const getProductPage = (params) =>
  request.get('/product/page', { params })

export const publishProduct = (data) =>
  request.post('/product/publish', data)

export const getProductById = (id) =>
  request.get(`/product/${id}`)