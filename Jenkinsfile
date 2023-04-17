pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
    stages {
            stage('Gradle Build') {
                steps {
                    bat './gradlew cleanTest test'
                }
            }
        }
}