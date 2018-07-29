#!groovy

pipeline {

agent none
  stages {
    stage('Maven Build') {
      steps {
        sh 'mvn clean install'
        def pom = readMavenPom file:'pom.xml'
        print pom.version
        env.version = pom.version
        currentBuild.description = "Release: ${env.version}"
      }
    } 
  }
}
   