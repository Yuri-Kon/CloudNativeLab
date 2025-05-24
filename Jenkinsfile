pipeline {
    agent any

    stages {
        stage('拉取代码') {
            steps {
                git 'https://github.com/Yuri-Kon/CloudNativeLab.git'
            }
        }

        stage('Maven 构建') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('构建 Docker 镜像') {
            steps {
                sh 'docker build -t my-simple-app:latest .'
            }
        }

        stage('部署到 Kubernetes') {
            steps {
                sh 'kubectl apply -f ticket-system-k8s/'
            }
        }
    }
}
