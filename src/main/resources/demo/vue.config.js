module.exports = {

  lintOnSave: false,
  // publicPath: 'http://localhost:808',
  outputDir: '../static',
  publicPath:'/',
  crossorigin: '',

  devServer: {
    // host: 'http://localhost:8080',
    // port: '8080',
    proxy: {
      '/': {
        target: 'http://localhost:8080',
        changeOragin: true,
        pathRewrite: {
          '^/': ''
        }
      }
    }
  }
}
