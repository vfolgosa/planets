node {

	def customImage
	
	stage('Checkout') {
		checkout scm
    }
    
    stage('Build package'){
    	dir('config-service') {
        	sh 'mvn clean install'
        	def pom = readMavenPom file:'pom.xml'
        	print pom.version
        	env.version = pom.version
        	currentBuild.description = "Release: ${env.version}"
       }
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
			customImage.run('-p 8081:8081')
		}
    }
	
	
}


node('dind-node') {
  
    stage('Build') { # (2)
      dir('config-service') {
        sh 'mvn clean install'
        def pom = readMavenPom file:'pom.xml'
        print pom.version
        env.version = pom.version
        currentBuild.description = "Release: ${env.version}"
      }
    }
    stage('Image') {
      dir ('config-service') {
        docker.withRegistry('https://192.168.99.100:5000') {
          def app = docker.build "piomin/config-service:${env.version}" # (3)
          app.push() # (4)
        }
      }
    }
}
   