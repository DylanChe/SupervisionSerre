const int LED=2; //declaration constante de broche 

// --- Déclaration des variables globales ---
// ... 

// --- Initialisation des fonctionnalités utilisées ---
// ... 

//**************** FONCTION SETUP = Code d'initialisation *****
// La fonction setup() est exécutée en premier et 1 seule fois, au démarrage du programme

void setup()   { // debut de la fonction setup()

// --- ici instructions à exécuter au démarrage --- 

 pinMode(LED, OUTPUT); //met la broche en sortie 



} // fin de la fonction setup()
// ********************************************************************************

//*************** FONCTION LOOP = Boucle sans fin = coeur du programme *************
// la fonction loop() s'exécute sans fin en boucle aussi longtemps que l'Arduino est sous tension

void loop(){ // debut de la fonction loop()

// --- ici instructions à exécuter par le programme principal --- 

digitalWrite(LED,HIGH); // met la broche au niveau haut (5V) – allume la LED

delay(500); // pause de 500 millisecondes (ms)

digitalWrite(LED,LOW); // met la broche au niveau bas (0V) – éteint la LED

delay(500); // pause de 500ms

}
