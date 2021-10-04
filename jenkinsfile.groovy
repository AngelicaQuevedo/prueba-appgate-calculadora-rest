pipeline {
    tools {
        jdk 'openjdk-1.1'
        maven 'Maven_3_6_3'
    }
    stages {
    
    	stage("Pull") {
            steps {
                git branch: "${env.BRANCH_NAME}", credentialsId: "1234Test", url: "https://github.com/AngelicaQuevedo/prueba-appgate-calculadora-rest.git"
            }
        }
        stage('Build') {
            steps {
                echo 'Building calculator'
                sh "mvn clean install"
            } 
        }
        
        stage('Publish') {
            steps {
                echo 'Deploying target/calculadora-rest-0.0.1-SNAPSHOT.jar to testing environment'
            } 
        }
    }
    post {
        failure {
            mail to: "angelicaquevedo@gmail.com", subject: "calculator Build Pipeline failed : ${env.BUILD_ID}", body: "calculator Pipeline failed\n${env.JENKINS_URL}job/calculator/job/${env.BRANCH_NAME}/${env.BUILD_ID}"
        }
        success {
            script {
                bitbucketStatusNotify(buildState: 'SUCCESSFUL',
                repoSlug: "${env.PROJECT_NAME}",
                commitId: "${env.LAST_COMMIT}"
                )
            }
        }
    }
}
