import axios from 'axios'
import { Message, Loading } from 'element-ui'

const service = axios.create({
  baseURL: '/api',
  timeout: 10000
})

let loadingInstance

// 请求拦截：开启 Loading + 带上用户 ID
service.interceptors.request.use(config => {
  loadingInstance = Loading.service({
    fullscreen: true,
    text: '加载中...',
    background: 'rgba(0, 0, 0, 0.4)'
  })
  const user = JSON.parse(localStorage.getItem('user') || 'null')
  if (user) {
    config.headers['X-User-Id'] = user.id
  }
  return config
}, err => {
  loadingInstance && loadingInstance.close()
  return Promise.reject(err)
})

// 响应拦截：统一处理错误 + 关闭 Loading + 解包 data
service.interceptors.response.use(res => {
  loadingInstance && loadingInstance.close()
  const data = res.data
  if (data.code !== 200) {
    Message.error(data.msg || '请求失败')
    return Promise.reject(new Error(data.msg))
  }
  return data.data
}, err => {
  loadingInstance && loadingInstance.close()
  Message.error(err.message || '网络错误')
  return Promise.reject(err)
})

export default service