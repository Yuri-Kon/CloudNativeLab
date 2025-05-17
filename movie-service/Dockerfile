# 使用官方 OpenJDK 镜像作为基础镜像
FROM eclipse-temurin:22-jdk

# 设置工作目录
WORKDIR /app

# 复制 jar 文件到容器中（注意名称需与实际匹配）
COPY target/*.jar app.jar


EXPOSE 8082

# 启动服务
ENTRYPOINT ["java", "-jar", "app.jar"]
