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
    }
    
	docker.withRegistry('http://54.233.110.154:5043', 'docker-repository-credentials') {
		stage('Build image') {
			customImage = docker.build("planets-api")
		}
        stage('Push image') {
			customImage.push("${env.BUILD_NUMBER}")
            customImage.push("latest")
			
		}
		stage('Stopping previus container') {
			sh 'docker stop $(docker ps -q --filter ancestor=planets-api )'			
		}
		stage('Run image') {
			customImage.run('-p 8081:8081')
		}
    }
	
	
}
   