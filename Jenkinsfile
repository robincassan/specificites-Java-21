pipeline {
    agent any

    stages {
        stage('Dependances') {
            steps {
                echo 'Installation des dépendances...'
                sh '''
                    apt-get update -y
                    apt-get install -y apache2
                '''
            }
        }

        stage('Checkout') {
            steps {
                echo 'Récupération du code depuis GitHub...'
                checkout scm
            }
        }

        stage('Backup') {
            steps {
                echo 'Sauvegarde du site web existant...'
                sh 'cp -r /var/www/html /var/www/html.backup || true'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Déploiement du nouveau site web...'
                sh '''
                    rm -rf /var/www/html/*
                    cp -r * /var/www/html/
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Vérification du déploiement...'
                sh 'curl -f http://localhost/ || (echo "Test échoué" && exit 1)'
            }
        }
    }

    post {
        success {
            echo ' Déploiement réussi !'
        }
        failure {
            echo ' Échec du déploiement. Vérifiez les logs.'
        }
        always {
            echo ' Nettoyage du système...'
            sh '''
                rm -rf /var/www/html/*
                apt-get remove -y apache2
                apt-get autoremove -y
            '''
        }
    }
}
