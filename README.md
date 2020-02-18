# springboot-suntv
### Requirement
- springboot
- mybatis
- jwt
- redis
- restful
- qiniu

### Installation
- 新建数据库suntv
- src/main/resources 目录下新建文件application-prod.yml
- 将application-dev.yml中的内容复制到application-prod.yml
- 修改application.yml中spring:profiles:active为prod
- 修改相应的配置
- 根目录执行命令mvn clean package

# 前端项目
https://github.com/rxlisbest/react-native-suntv