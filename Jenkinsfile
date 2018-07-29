node {

	def customImage
	
	stage('Checkout') {
		checkout scm
    }
    stage('Build') { 
      dir('.') {
        sh 'mvn clean install'
        def pom = readMavenPom file:'pom.xml'
        print pom.version
        env.version = pom.version
        currentBuild.description = "Release: ${env.version}"
      }
    }
}