const Koa = require('koa');
const Router = require('koa-router');

const compose = require('koa-compose');
const body = require('koa-body');
const logger = require('koa-logger');
const connect = require('koa2-connect');
const httpProxy = require('http-proxy-middleware');

const app = new Koa();
const router = new Router();
const PORT = 8080;

router.get('*', connect(
  httpProxy({
    target: 'https://hacker-news.firebaseio.com/',
    changeOrigin: true
  })
));

app.use(compose([
  logger(),
  body(),
  router.routes(),
  router.allowedMethods()
]));

app.listen(PORT, () => {
  console.log('Proxy started...');
});
