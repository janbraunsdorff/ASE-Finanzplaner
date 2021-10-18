pipeline {
    agent any
    stages {
        stage ('Print Version') {
            steps {
                sh 'docker'
                sh 'gradle -v'
                sh 'java -v'
            }
        }
    }
}

