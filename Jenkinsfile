pipeline {
    stages {
        agent {
            docker { image 'gradle:jdk17' }
        }
        stage ('Print Version') {
            steps {
                sh 'gradle -v'
                sh 'java -v'
            }
        }

    }
}