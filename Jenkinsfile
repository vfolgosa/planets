#!groovy

pipeline {

agent none
  stages {
    stage('Maven Install') {
      agent {
        docker {
          image 'maven'
        }
      }
      steps {
        sh 'mvn clean install'
      }
    } 
  }
}
   