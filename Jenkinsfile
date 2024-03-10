node() {
    stage(name: 'Preparation'){
        sh(script: 'rm -rf java-demo-webapp')
        sh(script: 'git clone https://github.com/bmajczak/java-demo-webapp.git')
        
    }
    stage(name: 'Build'){
        dir(path: 'java-demo-webapp/mywebapp/'){
            sh(script: 'mvn clean package')
        }
    }
    stage(name: 'Deploy'){
        sshagent(credentials: ['app01-key']){
            sh(script: 'ssh -o StrictHostKeyChecking=no vagrant@app01 "sudo systemctl stop tomcat"')
            sh(script: 'scp -o StrictHostKeyChecking=no java-demo-webapp/mywebapp/target/*.war vagrant@app01:/tmp/')
            sh(script: 'ssh -o StrictHostKeyChecking=no vagrant@app01 "sudo mv /tmp/*.war /usr/local/tomcat/webapps/"')
            sh(script: 'ssh -o StrictHostKeyChecking=no vagrant@app01 "sudo systemctl start tomcat"')
        }
    }
}