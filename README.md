# 1.项目搭建

| 组件            | 版本   | 说明       |
| :-------------- | ------ | :--------- |
| SpringBoot      | 2.1.0  | 后端框架   |
| Spring Security | 2.1.0  | 认证框架   |
| JWT             | 0.10.6 | Token相关  |
| Redis           | 3.2.1  | 缓存相关   |
| MYSQL           | 8.0.1  | 数据库相关 |



SpringBoot整合kafka 一定要在kafka/config/server.properties文件中开启
`listeners=PLAINTEXT://:9092
advertised.listeners=PLAINTEXT://192.168.169.20:9092
`