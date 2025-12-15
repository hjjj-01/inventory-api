# WMS API 项目

这是一个基于Spring Boot的仓库管理系统API，提供了用户注册和数据管理功能。

## 功能特性

- 用户注册API
- MySQL数据库连接与操作
- 跨域请求支持
- 自动表创建（首次启动时）

## 技术栈

- Java 8+
- Spring Boot 2.7.5
- MySQL 8.0
- Maven

## 快速开始

### 前提条件

- JDK 8或更高版本
- MySQL 8.0数据库
- Maven 3.6或更高版本

### 配置数据库

1. 确保MySQL服务正在运行
2. 在`src/main/resources/application.properties`文件中配置数据库连接信息：
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/goodssys?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=Nt@ls.com
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

### 构建和运行项目

```bash
# 构建项目
mvn clean package

# 运行项目
mvn spring-boot:run
```

## API接口说明

### 用户注册接口

**URL**: `/api/users/register`
**方法**: `POST`
**请求体**: 
```json
{
  "username": "user123",
  "password": "password123",
  "email": "user123@example.com",
  "phone": "13800138000",
  "fullName": "张三",
  "age": 25,
  "gender": "男",
  "status": 1
}
```

**成功响应**: 
```json
{
  "success": true,
  "message": "用户注册成功"
}
```

**失败响应**: 
```json
{
  "success": false,
  "message": "用户名已存在或注册失败"
}
```

### 初始化用户表接口（用于测试）

**URL**: `/api/users/init-table`
**方法**: `POST`

**成功响应**: 
```json
{
  "success": true,
  "message": "用户表初始化成功"
}
```

## 项目结构

```
src/main/java/org/example/
├── App.java                    # 应用主类
├── controller/                 # 控制器层
│   └── UserController.java     # 用户控制器
├── service/                    # 服务层
│   ├── UserService.java        # 用户服务接口
│   └── impl/
│       └── UserServiceImpl.java # 用户服务实现
├── model/                      # 数据模型层
│   └── User.java               # 用户实体类
├── repository/                 # 数据访问层
│   └── UserRepository.java     # 用户数据访问类
├── config/                     # 配置层
│   └── AppConfig.java          # 应用配置类
└── listener/                   # 监听器层
    └── ApplicationStartupListener.java # 应用启动监听器
```

## 注意事项

- 首次启动应用时，系统会自动尝试创建user表
- 生产环境中，请确保修改数据库密码等敏感信息
- 建议在生产环境中调整跨域配置，限制允许的来源
- 实际部署时，应添加适当的日志记录和安全措施