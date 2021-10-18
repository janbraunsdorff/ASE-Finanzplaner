pipeline {
    agent {
        docker { image 'gradle:jdk17' }
    }
    stages {
        stage ('Print Version') {
            steps {
                sh 'gradle -v'
                sh 'java -v'
            }
        }
    }
}

