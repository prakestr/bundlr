pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/prakestr/bundlr.git'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                // Start the Selenium Grid using docker-compose
                sh 'docker-compose -f docker-compose.yml up -d selenium-hub chrome'
            }
        }

        stage('Build and Test') {
            steps {
                // Run your Maven build and execute tests, pointing to the Selenium Grid
                script {
                    // Set the URL for the Selenium Grid Hub as an environment variable or system property
                    // so that your tests know where to connect to
                    // This would be used in your test code to set up the WebDriver
                    sh 'mvn clean test -DSELENIUM_HUB_URL=http://localhost:4444/wd/hub'
                }
            }
        }

        stage('Generate Reports') {
            steps {
                script {
                    sh 'mvn allure:report'
                }
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

        stage('Stop Selenium Grid') {
            steps {
                // Stop the Selenium Grid using docker-compose
                sh 'docker-compose -f docker-compose.yml down'
            }
        }
    }

    post {
        always {
            // Ensure that the Selenium Grid is shut down even if the build fails
            sh 'docker-compose -f docker-compose.yml down'
        }
    }
}

