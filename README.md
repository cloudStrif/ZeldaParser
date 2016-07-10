
========Projet ADS4 (VERSION EXECUTABLE SUR LINUX)  =============
===============================================================================
<img src ="ads2/img2/logo.png">
![img](https://github.com/raitogriffith/parser/blob/master/out.gif)
Nom du projet : Zelda_Draw ads4.<em>lien : </em>http://www.palaiszelda.com/jeux_logiciels.php#jeu6


Note :- Ce projet comporte du son (fond sonore et bruitage de deplacement de la tortue rendant le projet plus agreable a voir et a tester ,car tous se passe en direct, ou sinon mettre le PC en muet.).On peut la desactiver via le menu.
     
	 -Lancer le programme de preference ,c'est plus illustratif car les deplacements et sons sont programmés de sorte a comprendre le code.(ex :HAUTPINCEAU ,BASPINCEAU ,AVANCE N marqué par des sons , deplacement en directe de la tortue ect .. )

Comment lancer le programme ?
=====> Pour ce faire aller dans ads2/ puis : faire "java Fenetre" , le code ettant deja compile l'application se lancera. <====== 
====>OU faire java -jar Programme.jar dans le dossier ads2<====</br></br>
GRAMMMAIRE : </br>
	/*</br>
	 * PROJET ADS4 </br>
	 * </br>
	 * Grammaire : Programme -> nontermCode2 nontermCode
	 * 				nontermCode2 -> VAR identificateur  ; | Rien -> nontermInst2()
	 * 				nontermCode -> VARIABLE  = nontermExp() | HAUTPINCEAU | BASPINCEAU | AVANCE = nontermExp() | TOURNE nontermExp() |PRINT nontermExp()</br>
	 * 				| COULEUR (nontermExp() , nontermExp() ,nontermExp() ) ; | EPAISSEUR nontermExp() |</br>
	 * 				TANTQUE nontermExp() FAISONS{nontermProg() } | SI nontermExp() ALORS{nontermProg() ;} nonterm() ;||DEBUT BlocInstruction(Debut) FIN ;</br>
	 * 				FOR nontermExp() TIMES{nontermProg() ;} | WHILE (nontermExp() >|<|MOD nontermExp() ) {nontermProg(); } | IF(nontermExp() >|<|MOD  nontermExp())
	 * 				THEN{nontermProg() ;} nonterm2 ; </br>
	 * 				nonterm ->SINON nontermPorg() |rien</br>
	 * 				nonterm2 -> ELSE nontermProg() |rien</br>
	 * 				
	 * 			 nonTermExp-> nombre nonTermExpSuite | identificateur nonTermExpSuite | (nonTermExp) nonTermExpSuite</br>
	 * 			nonTermExpSuite->operator nonTermExpSuite | rien</br>
	 * 
	 * 				operateur -> + expr | -expr |*expr |/expr</br>
	 * 				BlocInstruction -> Programme
	 * 				nontermsup -> > | < | MOD |=
	 */</br>







Il y a des fichiers exemples pour montrer la synthaxe du language.(maison.ln , carre.ln ,triforce.ln ect ...)
Les fichiers TESTS sont  : 
	
	-ali2 : trace un cercle (boucle WHILE)
	-ali : exemple d'erreure (division pas zero et synthaxe marqué dans la console)
	-spiral.ln  : trace une spiral 
	-maison.ln : dessine une maison avec 2 fenetres
	-triforce.ln : dessine une triforce -> Triangle sur triangle inverse.
	-triangle.ln : trace un triangle
	-exempleProjet.ln :  qui est l'exemple fournis dans le projet.(carre)
	-carre.ln : trace un carre (utilisant boucle et condition avec MOD )
	
==> CRTL +o  ,taper le nom du fichier test puis ENTER.
==> CRTL+W pour supprimer l'onglet courant.

(on autorise de tourner d'un angle que l'on veut , pas forcement un mutiple de 90 car tracer QUE des rectangle/carre n'est pas forcement adapte au dessin , on peut aussi faire des triangle ! cf fichier tests ) : 



Descrition : Link tiré du jeu vidéo Zelda suivant les commandes ,dessine ce que vous voulez.
<img src = "ads2/img/b1.gif">

Le programme a executer (ou a ecrire) est dans l'interface graphique.On peut créer ou ouvrir un fichier grace au menu de l'interface graphique.
(c'est un language "interpreté").Le programme marche en fait en deux temps , la compilation ,qui utilise la grammaire et verifie que tous semble correcte et remplie la pile d'instruction en retour ,puis l'execution qui consiste a depiler la pile d'action,si il y a une erreur l'execution ne se fait pas ,dans le projet lorsque l'on appui sur le bouton exec les deux sont fait en meme temps.


Pour executer il faut faire CRTL+e ou menu>EXECUTER 
==>CRTL+i = reinitialiser : on le fais a chaque fin de dessin en general.<==

Pour Créer un nouveau doc CTRL+T ou via le menu directement.(CRTL+S = sauvegarde).


Les Exceptions sont geres et apparaissent en bas de l'IG ,si tout est bon il affiche : Le code est bon.(Aussi , un bruitage nous informe de la coherence du code.)


Commandes (celles ci suivent la grammaire du Projet mais en a aussi d'autres) :

COULEUR(R,G,B) : Change la couleur du curseur avec les couleur rgb 
HAUTPINCEAU : permet de dire de ne pas dessiner
BASPINCEAU : permet de dire d'ecrire.
VAR x :declare une variable entiere (imperativement en debut de programme)
DEBUT...FIN : entoure la suite d'action a executer.
SI ..ALORS  ou  SI ALORS{} SINON{}. implémenté et
TANTQUE a FAISONS{} IMPLÉMENTÉ comme demandé dans le sujet Projet ads4.
EPAISSEUR val : change l'epaisseur du curseur de la tortue.
AVANCE val : avance de val pixels 
TOURNE val :tourne de val degrés 
FOR n TIMES{} boucle for 
PRINT val : affiche dans la console val

mais aussi : WHILE (a >b){}  resp <(ou egalite)  et WHILE(a MOD b){} : tantque le modulo ne fais pas 0.
De meme pour IF(a>b) resp < et IF(a MOD b)THEN {} ELSE{}

Ses commandes sont plus pratique pour programmer.

# LUNARLINUX
Project


