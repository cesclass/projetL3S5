# projetL3S5

## Système d'exploitation
Ce programme a été pensé pour un système d'exploitation Windows, notament, car la base de données a été crée et installé avec *WAMP Server*. Nous sommes donc incapables de vous assurer le bon fonctionnement de l'application avec un autre système d'exploitation et ne sommes pas responsables de la mauvaise utilisation des logiciels distribués avec ce README.

## Mise en place de la BDD
La base de données a été faite avec WAMP Server. Il est donc conseillé de la reconstruire avec ce logiciel.
Sur phpMyAdmin :
 + Créer la base "nts" avec un inter classement "utf8_unicode_ci"
 + Entrer dans cette base et aller dans l'onglet importer
 + Cliquer sur le bouton [choisir un fichier] et sélectionner "nts_full.sql" à la racine du projet- Importer (normalement, il est inutile de changer les paramètres d'importation)

## Exécution du serveur
Pour lancer le serveur, il est possible de simplement cliquer sur le fichier *Server.jar* mais celui-ci ne possède pas d'interface graphique et risque de s'ouvrir en arrière-plan sans possibilitée de le fermer. Il est donc préférable de l'ouvrir via un terminal
sous Windows (PowerShell) avec la commande suivante : 
```PowerShell
java -jar Server.jar```
Une fois le serveur lancé, on peux commencer à lancer un (des) client(s)

## Exécution du client
Ne jamais démarrer un client sans serveur !
Pour lancer un client, il suffit de cliquer sur le fichier *Client.jar* et une fenêtre de Login s'ouvre.
Il est nécessaire de s'identifier pour accéder à l'application. Pour cela, veuillez utiliser un des identifiants présents dans la base de données.

## Utilisation
Depuis le Login :
 + Se Connecter -> Entrez des identifiants correct pour vous connecter. Si les identifiants ne sont pas correct, vous serez invité à réessayer.

Depuis l'application Client :
 + Lire un Ticket -> Cliquez sur le nom d'un groupe dans l'arbre pour le déplier puis sur un ticket pour l'ouvrir dans le panneau de droite.
 + Consulter le statut des messages -> la couleur du message indique son statut général (GRIS : envoyé, ROUGE : reçu par le serveur, ORANGE : reçu par tous les utilisateurs du ticket, VERT : lu par tous les utilisateurs du ticket). 
 + Consulter le statut d'un message par utilisateur -> Pour accéder au statut de chaque utilisateur par rapport au message, cliquez sur le message voulu et une nouvelle fenêtre s'ouvrira. Cliquez sur OK pour la fermer. Cliquer sur le message mettra aussi à jour son statut général (sa couleur sur l'interface graphique).
 + Envoyer un message -> Saisissez votre message dans la zone de texte en bas du panneau droit et appuyer sur envoyer pour le partager à tous les utilisateurs du ticket en temps réel.
 + Créer un nouveau ticket -> Cliquez sur le boutton *nouveau ticket* pour ouvrir la fenêtre de création de ticket. Toutes les informations doivent être renseignées pour créer un ticket (sinon un message d'erreur vous invitera à réessayer).
