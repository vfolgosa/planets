pipeline {
  def image
  agent none
  stages{
    stage('Build Jar'){
        agent {
          docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
          }
        }
        steps {
            sh 'mvn package'
            stash includes: 'target/*.jar', name: 'targetfiles'
        }
    }
    stage('Build Docker Image') {
      agent {
            node {
                label 'DockerDefault'
            }
         }

      steps {
            script{
                unstash 'targetfiles'
                sh 'ls -l -R'
				image = docker.build("planets-service:latest", ' .')
            }
      }
    }
    
   stage('Push Image') {
   		steps{
   			docker.withRegistry('http://54.233.110.154:5043', 'docker-repository-credentials') {
   				image.push();
   			}
   		}
   }
   stage('Run container'){
   		steps{
   			docker.withRegistry('http://54.233.110.154:5043', 'docker-repository-credentials') {
   				image.run('-p 8090:8090')
   			}
   		}
    }
  }
}