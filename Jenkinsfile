pipeline{

    agent any
tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven3"
    }

    stages {

        stage('Git Checkout'){

            steps{

                script{

                    git branch: 'master', url: 'https://github.com/mendjijet/TestJet.git'
                }
            }
        }
stage('Maven build'){

            steps{

                script{
                    sh 'mvn clean install'
                    junit 'target/surefire-reports/*.xml'
                     archiveArtifacts artifacts: 'target/*.jar'
                }
            }
        }

        stage('Build Docker image'){

                    steps{

                        script{

                            sh 'docker build -t mendjijet/testjet .'
                        }
                    }
                }
        
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u mendjijet -p ${dockerhubpwd}'

}
                   sh 'docker push mendjijet/testjet'
                }
            }
        }
        }

}
