
pipeline{
   agent {
    docker {
      image 'maven:3.6.3-jdk-11'
      args '-v /root/.m2:/root/.m2'
    }
  }
  environment{
        flag = ''
    }
  stages {
          stage("build & SonarQube analysis") {
            steps {
                script{
                    flag=env.STAGE_NAME
                }
              withSonarQubeEnv('sonarqube2') {
                sh 'java -version'
                sh 'mvn clean package sonar:sonar'
                 echo "========********************************\nDeploying executed successfully\n***************========"
              }
             
            }
            post{
                changed{
                    echo "========&&&&&&&&&&&&&&&&&& Their is change in Packaging from pervious&&&&&&&&&&&&&&&&&&&&&&&&&========"
                }
                success{
                    echo "**************************************** ${currentBuild.number} and ${currentBuild.result}******************************"
                    archiveArtifacts 'target/*.jar'
                    echo "========**********Maven Packaging stage executed successfully************========"
                }
                
                failure{
                    echo "========Maven Packaging stage execution failed========"
                }
            }
          }
                stage("Quality gate") {
                    steps {
                        script{
                            flag=env.STAGE_NAME
                         }
                         timeout(time: 1, unit: 'HOURS') { 
                     waitForQualityGate abortPipeline: true
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
                   script{
                    flag=env.STAGE_NAME
                }
                     rtUpload (
                         serverId: 'artifactory-server',
                     spec: """{
                             "files": [
                                      {
                                     "pattern": "target/*.jar",
                                     "target": "art-doc-dev-loc/${env.BUILD_NUMBER}/"
                                    }
                                ]
                            }"""
                        )
                    }
            post{
                success{
                    echo "========Deploying executed successfully ${params.buildnumber}========"
                    

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
                     spec: """{
                             "files": [
                                      {
                                      "pattern": "art-doc-dev-loc/${params.buildnumber}/*.jar",
                                      "target": "bazinga/"
                                    }
                                ]
                            }"""
                        )
                        sshagent(['f674a595-aa5a-4e23-90fb-eb8ee9341dfe']){
                    sh 'scp -r bazinga/*.jar ubuntu@18.236.173.67:/home/ubuntu/artifacts'
                    }
                    }
            post{
                success{
                    echo "========Download executed successfully========"
                    script{
                        zip zipFile: 'bazinga.zip', archive: false, dir: 'bazinga/'
                    }
                    emailext attachLog: true, attachmentsPattern: "bazinga.zip", body: "<b> Artifact is downloaded with build number of ${params.buildnumber} </b> <br>Project: ${env.JOB_NAME} <br>Current Build Number: ${env.BUILD_NUMBER} <br> URL of build: ${env.BUILD_URL}", compressLog: true, subject: 'Pipeline Failed in :', to: 'vimaleshn98@gmail.com '

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
                    echo "========Their is change in Packaging from pervious${params.buildnumber}========"
                }
        success{
            echo "========pipeline executed successfully ${flag}  ========"
        }
        failure{
             emailext attachLog: true, body: "<b> Pipeline is failed on ${flag} </b> <br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL of build: ${env.BUILD_URL}", compressLog: true, subject: 'Pipeline Failed in :', to: 'vimaleshn98@gmail.com '
            echo "========pipeline execution failed  ${flag}========"
        }
    }
}