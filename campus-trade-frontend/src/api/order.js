import request from '@/utils/request'

export const createOrder = (data) =>
  request.post('/order/create', data)

export const getOrderPage = (params) =>
  request.get('/order/page', { params })