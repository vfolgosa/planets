node {

	def customImage
	
	stage('Checkout') {
		checkout scm
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
   