pipeline {
    agent {
        docker { image 'gradle:jdk17' }
    }
    stages {
        stage ('Print Version') {
            steps {
                sh 'gradle -v'
                sh 'java -version'
            }
        }

        stage ('Run Unit Tests') {
            steps {
                sh 'gradle test --stacktrace'
            }
        }

        stage ('Build Application') {
            steps {
                sh 'gradle build --refresh-dependencies --stacktrace'
            }
        }
    }
}

