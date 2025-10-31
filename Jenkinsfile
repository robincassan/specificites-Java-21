pipeline {
    agent any

    stages {
        stage('Dependances') {
            steps {
                echo 'Installation des dépendances...'
                sh '''
                    apt-get update -y
                    apt-get install -y apache2 curl
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
                sh '''
                    mkdir -p /var/www/html.backup
                    cp -r /var/www/html/* /var/www/html.backup/ || true
                '''
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
                echo 'Démarrage du serveur Apache...'
                sh '''
                    # Lancer Apache en arrière-plan
                    apache2ctl -DFOREGROUND &
                    sleep 3

                    echo "Test du site..."
                    if curl -f http://localhost/; then
                        echo "Test réussi "
                    else
                        echo "Test échoué "
                        exit 1
                    fi
                '''
            }
        }
    }

    post {
        always {
            echo ' Nettoyage du système...'
            sh '''
                pkill apache2 || true
                apt-get remove -y apache2
                apt-get autoremove -y
            '''
        }

        failure {
            echo ' Échec du déploiement. Vérifiez les logs.'
        }
    }
}
