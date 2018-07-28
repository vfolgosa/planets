node {

	def customImage
	
	stage('Checkout') {
		checkout scm
    }
    
    stage('Build package'){
    	
        	sh 'mvn clean install'
        	def pom = readMavenPom file:'pom.xml'
        	print pom.version
        	env.version = pom.version
        	currentBuild.description = "Release: ${env.version}"
       
    }
    
	docker.withRegistry('http://54.233.110.154:5043', 'docker-repository-credentials') {
		stage('Build image') {
			customImage = docker.build("testes/planets-service:${env.version}")
		}
        stage('Push image') {
			customImage.push()
		}
		stage('Stopping previus container') {
						
		}
		stage('Run image') {
			customImage.run('-p 8090:8090')
		}
    }
	
	
}
   