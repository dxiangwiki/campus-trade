module.exports = {
  publicPath: process.env.NODE_ENV === 'production'
    ? '/campus-trade/'     // GitHub Pages 的路径前缀
    : '/',                 // 开发环境用根路径
  lintOnSave: false,
  devServer: {
    port: 8081,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
}