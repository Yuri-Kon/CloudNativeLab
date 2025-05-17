pipeline {
    agent any

    environment {
        IMAGE_TAG = "1.0"
        REGISTRY = "localhost"  // 若推送远程仓库可改为 registry URL
    }

//     stages {
//         stage('拉取代码') {
//             steps {
//                 git 'https://github.com/Yuri-Kon/CloudNativeLab.git'
//             }
//         }

        stage('构建 Jar') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('构建镜像') {
            steps {
                script {
                    def services = ['eureka-service', 'user-service', 'movie-service', 'order-service']
                    for (svc in services) {
                        sh """
                            docker build -t ${svc}:${IMAGE_TAG} ${svc}
                        """
                    }
                }
            }
        }

        stage('部署到 Kubernetes') {
            steps {
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
            echo '部署成功 ✅'
        }
        failure {
            echo '部署失败 ❌'
        }
    }
}
