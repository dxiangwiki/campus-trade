# 🎓 校园二手交易系统

一个基于 Vue2 + SpringBoot 的校园二手物品交易平台。

## ✨ 功能

- 用户注册 / 登录
- 商品发布 / 浏览 / 搜索
- 在线下单 / 订单管理
- 个人中心

## 🛠 技术栈

**前端**：Vue2 + ElementUI + Axios + Vue Router
**后端**：SpringBoot 4.x + Spring Data JPA + Lombok
**数据库**：MySQL 9.4

## 📁 项目结构

```
campus-trade/
├── campus-trade-backend/     # 后端（SpringBoot）
└── campus-trade-frontend/    # 前端（Vue2）
```

## 🚀 快速开始

### 后端

```bash
cd campus-trade-backend
# 改 application.yaml 里的数据库账号密码
mvn spring-boot:run
# 后端启动在 http://localhost:8080
```

### 前端

```bash
cd campus-trade-frontend
npm install
npm run serve
# 前端启动在 http://localhost:8081
```

## 🧪 测试账号

- 用户名：`admin`
- 密码：`123456`

## 📸 截图

（待补充）

## 📝 License

MIT
