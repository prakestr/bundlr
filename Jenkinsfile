pipeline {
    agent any

    stages {
        stage('Start Selenium Server') {
            steps {
                // Pull the latest image and start the standalone Selenium server
                sh 'docker pull selenium/standalone-chrome:latest'
                sh 'docker run -d --name selenium-standalone -p 4444:4444 selenium/standalone-chrome:latest'
            }
        }

        stage('Build and Test') {
            steps {
                // Run your Maven build and execute tests, pointing to the standalone Selenium server
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
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/allure-maven-plugin',
                    reportFiles: 'index.html',
                    reportName: 'Allure Report'
                ])
            }
        }
    }

    post {
        always {
            // Stop the standalone Selenium server
            sh 'docker stop selenium-standalone'
            sh 'docker rm selenium-standalone'
        }
    }
}
