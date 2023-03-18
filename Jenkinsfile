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

        }

}