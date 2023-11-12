pipeline {
    agent any

    stages {
        stage('Start Selenium Grid') {
            steps {
                // Run Docker Compose to start the Selenium Grid
                sh 'docker-compose -f docker-compose.yml up -d selenium-hub chrome'
            }
        }

        stage('Build and Test') {
            steps {
                // Run your Maven build and execute tests, pointing to the Selenium Grid
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
            // Stop the Selenium Grid using Docker Compose
            sh 'docker-compose -f docker-compose.yml down'
        }
    }
}
