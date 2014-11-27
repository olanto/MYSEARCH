MYSEARCH
========

#Requis pour ce projet

##Avoir un solr-jetty-maven qui tourne
Prendre le projet depuis: <a href="http://github.com/ksclarke/solr-jetty-maven">solr-jetty-maven</a>. 

##Importer des données de test
Faire un import des documents dans la collection1 de solr: http://localhost:8983/solr/#/collection1/documents.

Choisir "Document Type" > "Solr Command (raw XML or JSON)",
puis copier/coller dans le champ "Document(s)" le contenu de "src/main/resources/olanto.xml".

Puis, il faut supprimer la première ligne une fois le contenu copié.

Pour cela faites "Crtl+Home" puis supprimer la ligne 

	<?xml version="1.0" encoding="UTF-8"?>
car l'import n'a pas besoin de cette balise <?xml..>

Puis appuyez sur "Submit Document". Vous aurez un résumé de l'import une fois l'import terminé. 

Enfin, il faut reloader les indexes de solr, pour cela allez dans http://localhost:8983/solr/#/~cores/collection1 et cliquez sur "Reload". Les documents sont à présent indexés et on peut faire des recherches.

 
##Démarrer
###Problème avec les tests

	mvn clean install
si les tests produisent des erreurs, c'est sans doute un problème avec le serveur Olanto de démo qui est arrêté. Il faudra prendre contact avec <a href="mailto:jacques@simple-shift.com">Jacques Guyot</a> ou <a href="mailto:karim@simple-shift.com">Karim Benzineb</a> afin de faire redémarrer le serveur Olanto.

Pour palier au problème des tests on peut lancer jetty sans faire les tests, comme ceci:

	mvn clean install -DskipTests jetty:run
Dans ce cas, les requêtes vers Olanto produisent une erreur qui sera interceptée.
Par contre, la partie Solr fonctionnera sans problèmes.

##Démarrer avec Jetty
Exécuter la commande:

	mvn clean install jetty:run 

##Démarrer dans Tomcat7
###Configuration

Dans le fichier setenv.bat qui se trouve dans %CATALINA_HOME%\bin, il faut le créer s'il n'existe pas, ajoutez la variable d'environnement :

	-Dfile.encoding=UTF-8
à JAVA_OPTS, par exemple:

	set JAVA_OPTS=-Dfile.encoding=UTF-8

###Publier le war
Exécuter la commande:

	mvn clean install
afin d'obtenir le war.

Maintenant, copiez le fichier target/mysearch-xxx.war dans le répertoire %CATALINA_HOME%\webapps\. Vous pouvez laisser la version ou l'enlever.

Vous pouvez aller sur le navigateur à l'adresse: <a href="http://localhost:8080/mysearch">http://localhost:8080/mysearch</a> .


