## SpringSecutiry登陆认证
> 不通过session管理用户会话，通过jwt+过滤器完成
### 实现如下功能
演示SpringSecutiry登陆操作,不涉及到数据库,使用了redis
1. 用户名密码登录成功返回jwt信息
2. 如果token将要过期用户在操作系统,自动为token续期

### 演示
##### 正常登录
```http request
# 请求
curl -i -X POST \
   -H "Content-Type:application/json" \
 'http://localhost:8000/auth/login?username=001&password=123456'

# 返回用户信息和登录token
{
	"user": {
		"username": "001",
		"password": "$2a$10$Q/nUwhpK4Vnl5g11cHxPEO5k4oWuIG53dKrSH.QHhxB9cu7j8HrYW",
		"authorities": [{
			"authority": "1"
		}],
		"enabled": true,
		"accountNonExpired": true,
		"accountNonLocked": true,
		"credentialsNonExpired": true
	},
	"token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDEiLCJhdXRoIjoiMSIsImp0aSI6IjlkOTVmY2ExNDQyNzQ0NDY5Yjc1MWM2ZDVkZjU5NTk1In0.Nvk8Xrpirlkvx5W7MTR9s-UgvfaZbE5Nu4v0g7T67SGtIWYhY1kvjJWfExX80FH5J_fh4IhRc2sobH6dfF-BeA"
}
```
##### 获取用户信息
```http request
# 使用登录成功返回的token作为请求头获取用户信息
curl -i -X GET \
   -H "Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDEiLCJhdXRoIjoiMSIsImp0aSI6IjlkOTVmY2ExNDQyNzQ0NDY5Yjc1MWM2ZDVkZjU5NTk1In0.Nvk8Xrpirlkvx5W7MTR9s-UgvfaZbE5Nu4v0g7T67SGtIWYhY1kvjJWfExX80FH5J_fh4IhRc2sobH6dfF-BeA" \
 'http://localhost:8000/auth/info'
# 返回用户信息
{
	"username": "001",
	"password": "$2a$10$4dnuwjfpjWFvpfu9yDbWueVJYXQFlleD1DO7jPMdSa1/CiVmN12Km",
	"authorities": [{
		"authority": "1"
	}],
	"enabled": true,
	"accountNonExpired": true,
	"accountNonLocked": true,
	"credentialsNonExpired": true
}
```
##### 错误登录
```http request
# 002账号在系统中不存在
curl -i -X POST \
   -H "Content-Type:application/json" \
 'http://localhost:8000/auth/login?username=002&password=123456'
# 返回错误信息
{
    "status": 400,
    "timestamp": "2020-08-06 09:53:44",
    "message": "用户名或密码不正确"
}
```