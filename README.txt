- Commande de compilation du projet :

javac -cp bin -d bin `find src -name *.java`


- Commande d'exécution du projet :

java -cp bin Application


- Initialisation du jeu :

Plutôt que de rentrer chaque triomino à la main à chaque exécution de l'application,
il est possible d'éditer les fichier dans le dossier files.
Cela est utile surtout pour relancer plusieurs fois un cas de test long à écrire.
Cela facilite également l'édition par rapport au terminal.

plateau.txt permet de définir à l'avance les triominos présent sur le plateau.
Les entrées sont de la forme '15 29 0 1 2 3'
Soit dans l'ordre <position X, position Y, orientation, a, b, c>
orientation vaut 0 pour faire pointer le triomino vers le haut, et 1 vers le bas.
a, b, c sont les coins du triomino écris dans le sens horraire et compris entre 0 et 5.

main.txt permet de définir la main du joueur.
Les entrées sont de la forme '1 2 3'
Soit <a, b, c> les coins du triomino à ajouter dans la main.

pioche.txt permet de définir la pioche du joueur.
Les entrées sont de la forme '1 2 3'
Soit <a, b, c> les coins du triomino à ajouter dans la pioche.


- Option de l'application :

Dans le fichier de l'application vous pouvez modifier deux options :

'Remplir à la main' indique si vous voulez initialiser le jeu à la main 'true'
Ou si vous préférer le faire à partir des fichiers dans files 'false'.

'Mode pas a pas' indique si vous voulez suivre l'évolution du jeu en pas à pas 'true'.
Vous devrez alors appuyer sur 'entrez' pour demander à l'ordinateur de poser le triomino suivant.
Dans le cas contraire le programme s'exécutera d'un seul coup 'false';
Vous pourrez bien évidement remonter dans le terminal pour voir les diférentes action effectuées par l'ordinateur.

Il n'y a certe que deux option, mais c'est deux option de moins à entrer à chaque exécution de l'application.
