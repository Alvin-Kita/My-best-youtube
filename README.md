# My Best Youtube

## *Application*

L'application My Best YouTube est une application Android qui permettra de gérer une liste
personnelle des meilleurs vidéo trouvées sur YouTube. Pour Chaque vidéo on pourra renseigner son
titre, une description, l'url YouTube et choisir une catégorie (Sport, Music, Comédie, …).

## *Context de développement*

L'application sera développée sur mon PC Archlinux en Java avec Android Studio et le plugin
SonartLint pour optimiser au maximum le
code [Page officiel du plugin SonarLint](https://plugins.jetbrains.com/plugin/7973-sonarlint). <br>
Je vais utiliser mon téléphone de test sous Android 11, un Crosscall Core-X5 pour tester
l'application, et également un émulateur Android Pixel A3.

## *Note*

- Si la base est vide au lancement de l'application, une entrée avec la toute première vidéo de YouTube sera ajoutée.

- J'utilise un iframe et donc javascript pour afficher la vidéo, je ne veux pas pas payer pour l'API
de YouTube, et souhaite avoir un affichage directement dans l'application. <br> 
En contrepartie la sécurité sur cette page doit être renforcée à terme pour éviter les attaques XSS (cross-site scripting).

## *Détails de l'application*

[Page d'accueil](Images/MainMenu.png)
Au lancement de l'application, on arrive sur la page d'accueil qui affiche la liste des vidéos ajoutées. 

## *Liens utiles*

- [Procédure de connexion de téléphone en adb](Procédure_connexion_telephone_adb.md)
- [Documentation Android](https://developer.android.com/docs)
- [Couleurs Catppuccin utilisées pour l'application](https://github.com/catppuccin/catppuccin)