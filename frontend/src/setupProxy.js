const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'https://j6a304.p.ssafy.io/',
      changeOrigin: true,
    })
  );
};