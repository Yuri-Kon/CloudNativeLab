pipeline {
    agent any

    environment {
        IMAGE_TAG = "1.0"
    }

    stages {
        stage('环境检测') {
            steps {
                sh 'docker version'
                sh 'kubectl version --client'
                sh 'echo "🛠 检查构建环境..."'
                sh 'which mvn || echo "❌ Maven 未安装"'
                sh 'which docker || echo "❌ Docker 未安装"'
                sh 'which kubectl || echo "❌ kubectl 未安装"'
            }
        }

        stage('构建 Jar') {
            steps {
                echo '📦 开始构建模块...'
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('构建 Docker 镜像') {
            steps {
                echo '🧱 开始构建服务镜像...'
                script {
                    def services = ['eureka-service', 'user-service', 'movie-service', 'order-service']
                    for (svc in services) {
                        sh "docker build -t ${svc}:${IMAGE_TAG} ${svc}"
                    }
                }
            }
        }

        stage('部署到 Kubernetes') {
            steps {
                echo '🚀 开始部署到 K8s 集群...'
                sh """
                    kubectl apply -f k8s/eureka-deployment.yaml
                    kubectl apply -f k8s/eureka-service.yaml
                    kubectl apply -f k8s/user-deployment.yaml
                    kubectl apply -f k8s/user-service.yaml
                    kubectl apply -f k8s/movie-deployment.yaml
                    kubectl apply -f k8s/movie-service.yaml
                    kubectl apply -f k8s/order-deployment.yaml
                    kubectl apply -f k8s/order-service.yaml
                """
            }
        }
    }

    post {
        success {
            echo '✅ 部署成功'
        }
        failure {
            echo '❌ 部署失败，请查看控制台日志'
        }
    }
}
