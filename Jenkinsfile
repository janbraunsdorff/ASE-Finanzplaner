pipeline {
    agent {
        //docker { image 'gradle:jdk17' }
        any
    }
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

