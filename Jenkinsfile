pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Gradle Build') {
            steps {
                bat './gradlew cleanTest test'
            }
        }
    }
}