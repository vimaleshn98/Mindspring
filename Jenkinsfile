pipeline{
    agent any
    environment{
        New_Version = '1.0.3'
    }
//      triggers {
//     cron('* * * * *')
//   }
    stages{
        stage("Build"){
            steps{
                bat 'mvn -version'
                bat 'mvn compile'
            }
            post{
                success{
                    echo "========Maven compile stage executed successfully========"
                }
                failure{
                    echo "========Maven compile stage execution failed========"
                }
            }
        }
        stage("build & SonarQube analysis") {
            steps {
              withSonarQubeEnv('sonarqube2') {
                bat 'mvn verify sonar:sonar'
              }
            }
          }

          stage("Quality gate") {
            steps {
                  echo "quality gate "
            }
        }

          stage("Test"){
            steps{
                echo "Maven Test"
                bat 'mvn test'
            }
            post{
                success{
                    junit 'target/surefire-reports/**/*.xml'
                    echo "========Maven Test stage executed successfully  ${New_Version}========"

                }
                failure{
                    echo "========Maven Test stage execution failed========"
                }
            }
        }

        stage("Packaging"){
            steps{
                echo "Maven Packaging"
                bat 'mvn package'
            }
            post{
                success{
                    archiveArtifacts 'target/*.jar'
                    echo "========Maven Packaging stage executed successfully  ${New_Version}========"

                }
                
                failure{
                    echo "========Maven Packaging stage execution failed========"
                }
            }
        }
          stage("Deployee"){
           when {
                expression {
                        currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
                steps{
     
                     rtUpload (
                         serverId: 'artifactory-server',
                     spec: '''{
                             "files": [
                                      {
                                     "pattern": "target/*.jar",
                                     "target": "art-doc-dev-loc"
                                    }
                                ]
                            }'''
                        )
                    }
            post{
                success{
                    echo "========Deploying executed successfully  ${New_Version}========"

                }
                
                failure{
                    echo "========Deploying stage execution failed========"
                }
            }
        }
       stage("Download"){
           when {
                expression {
                        currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
                steps{
     
                     
            rtDownload (
                         serverId: 'artifactory-server',
                     spec: '''{
                             "files": [
                                      {
                                      "pattern": "art-doc-dev-loc/Passport-0.0.1-SNAPSHOT.jar",
                                      "target": "bazinga/"
                                    }
                                ]
                            }'''
                        )
                    }
            post{
                success{
                    echo "========Download executed successfully  ${New_Version}========"

                }
                
                failure{
                    echo "========Download stage execution failed========"
                }
            }
        }
    }
    post{
        always{
            echo "========Running on ========"
        }
        changed{
                    echo "========Their is change in Packaging from pervious========"
                }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}