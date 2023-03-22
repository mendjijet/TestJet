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
stage('UNIT testing'){

            steps{

                script{

                    sh 'mvn test'
                }
            }
        }
        stage('Integration testing'){

                    steps{

                        script{

                            sh 'mvn verify -DskipUnitTests'
                        }
                    }
                }

stage('Maven build'){

            steps{

                script{

                    sh 'mvn clean install'
                }
            }
        }

        stage('Static code analysis'){
                    steps{

                        script{

                            withSonarQubeEnv(credentialsId: 'sonar-key') {

                                sh 'mvn clean package sonar:sonar'
                            }
                           }

                        }
                    }

        stage('upload war file to nexus'){

            steps{

                script{
                    nexusArtifactUploader artifacts:
                        [
                            [
                                artifactId: 'TestJet',
                                classifier: '',
                                file: 'target/TestJet.jar',
                                type: 'jar'
                            ]
                        ],
                        credentialsId: 'nexus-auth',
                        groupId: 'com.jet',
                        nexusUrl: 'localhost:8081',
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        repository: 'testjet-release',
                        version: '1.0.0'
                }
            }
        }
        }

}
