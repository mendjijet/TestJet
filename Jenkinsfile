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
                        nexusUrl: '172.17.0.2:8081',
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        repository: 'testjet-release',
                        version: '1.0.0'
                }
            }
        }
        }

}
