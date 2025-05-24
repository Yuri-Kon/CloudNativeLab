FROM jenkins/jenkins:lts

USER root

# 安装 Docker CLI
RUN apt-get update && \
    apt-get install -y docker.io

# 安装 Maven
RUN apt-get install -y maven

# 安装 kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl && \
    rm kubectl

USER jenkins
