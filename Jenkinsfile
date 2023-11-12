pipeline {
    agent any

    stages {
        stage('Start Selenium Standalone Chrome') {
            steps {
                sh 'docker run -d -p 4444:4444 --shm-size="2g" --name selenium-standalone selenium/standalone-chrome:latest'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test -DSELENIUM_HUB_URL=http://localhost:4444/wd/hub'
            }
        }

        stage('Generate Reports') {
            steps {
                sh 'mvn allure:report'
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/site/allure-maven-plugin',
                    reportFiles: 'index.html',
                    reportName: 'Allure Report',
                    reportTitles: 'Allure Report'
                ])
            }
        }
    }

    post {
        always {
            sh 'docker stop selenium-standalone || true'
            sh 'docker rm selenium-standalone || true'
        }
    }
}
