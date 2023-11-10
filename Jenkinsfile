pipeline {
    agent any

    tools {
        maven "Maven" // The name of the Maven installation to use.
        jdk "JDK" // The name of the JDK installation to use.
    }

    stages {
        stage('Checkout') {
            steps {
                // Checks out the code from the GitHub repository
                git 'https://github.com/prakestr/bundlr.git'
            }
        }

        stage('Build and Test') {
            steps {
                // Run your Maven build and execute tests
                script {
                    // If you have the Maven wrapper in your project, you can use ./mvnw instead of mvn
                    sh 'mvn clean test'
                }
            }
        }

        stage('Generate Reports') {
            steps {
                script {
                    // Assuming you have a report directory after tests are run
                    sh 'mvn allure:report'
                }
            }
        }

        stage('Publish Reports') {
            steps {
                // This will publish the Allure report generated in the target/site/allure-maven-plugin directory
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
}
