#!/usr/bin/python3.4
# coding: utf-8

from capteur import capteur
from gestion import gestion
from BDD import BDD
import mysql.connector
import connexion_bdd
from time import sleep
bdd = BDD()
gest = gestion(bdd)

class pluviometre(capteur):
    
    def __init__(self):
        self.id = 1
        self.nom = "pluvio"
        self.unite = 1
        self.impulsion = 0
        self.estFonctionnel = 1
        self.port = "/dev/ttyACM0"
        self.baud = 9600
        
                
    def mesurer(self):
        print("----- PLUVIOMETRE -----")
        print("- Envoi de ISW" + str(self.id))
        isWorking = gest.getOperatingState(self.id, self.port, self.baud)
        if len(isWorking) == 4:
            print("-> estFonctionnel = " + str(isWorking))
            if self.estFonctionnel != int(isWorking[2:], 16):
                self.estFonctionnel = int(isWorking[2:], 16)
                gest.enregistrerUnEtat(self.id, self.estFonctionnel)
                print("-> Etat MAJ dans la BDD : " + str(isWorking))
            print("- Envoi de GET" + str(self.id))
            resultatRequete = gest.getReleve(self.id, self.port, self.baud)
            if len(resultatRequete) == 8:
                print("-> Requete GET : " + str(resultatRequete))
                self.unite = int(resultatRequete[2:4], 16)
                self.impulsion = int(resultatRequete[4:], 16)
                gest.enregistrerUnReleve(self.nom,self.unite,self.impulsion)
            else:
                print("-> Resultat GET invalide ! len(" + str(resultatRequete) + ") = " + str(len(resultatRequete)))
        else:
            print("-> Resultat ISW invalide ! len(" + str(isWorking) + ") = " + str(len(isWorking)))
	
			
         
