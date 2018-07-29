node {

	def customImage
	
	stage('Checkout') {
		checkout scm
    }
    stage('Build') { 
        sh 'mvn clean package'
        def pom = readMavenPom file:'pom.xml'
        print pom.version
        env.version = pom.version
        currentBuild.description = "Release: ${env.version}"
        stash includes: '**/target/*.jar', name: 'app'
    }
    
	docker.withRegistry('http://54.233.110.154:5043', 'docker-repository-credentials') {
		stage('Build image') {
			script{
				unstash 'app'
				customImage = docker.build("planets-service")
			}
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

