pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
				figlet "Build"
                bat 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
				figlet "Compile"
                bat 'mvn compile'
            }
        }

		stage('Run Jar') {
            steps {
                figlet "Run Jar"
                bat 'start mvnw.cmd spring-boot:run'
                sleep 10
            }
        }
		
		stage('Test & JMeter & Selenium') {
            steps {
				figlet "Test & JMeter& Selenium"
				bat 'mvn verify -Pperformance'
            }
        }

        stage('Test WS Newman') {
            steps {
				figlet "Test WS Newman"
                bat "newman run Dxc.postman_collection.json --reporters cli,junit,htmlextra,json --reporter-htmlextra-export newman/DXC.html --reporter-junit-export newman/DXC.xml --reporter-json-export newman/DXC.json --disable-unicode"
            }
        }
    }
}