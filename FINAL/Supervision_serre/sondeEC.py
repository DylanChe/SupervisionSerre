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

class sondeEC(capteur):
    
    def __init__(self):
        self.__temperature = 0
        self.id = 4
        self.nom = "sonde_tuyau"
        self.unite = 4
        self.port = "/dev/ttyACM1"
        self.baud = 115200
        self.estFonctionnel = 0
        
                
    def mesurer(self):
        print("----- SONDE TUYAU -----")
        print("- Envoi de ISW" + str(self.id))
        isWorking = "0401"
        if len(isWorking) == 4:
            print("-> estFonctionnel = " + str(isWorking))
            if self.estFonctionnel != int(isWorking[2:], 16):
                self.estFonctionnel = int(isWorking[2:], 16)
                gest.enregistrerUnEtat(self.id, self.estFonctionnel)
                print("-> Etat MAJ dans la BDD : " + str(isWorking))
            print("- Envoi de GET" + str(self.id))
        
            resultatRequete = gest.getReleve(self.id, self.port, self.baud)
            if len(resultatRequete) == 8:
                print("-> Requete : " + resultatRequete)
                self.unite = int(resultatRequete[2:4], 16)
                temp = int(resultatRequete[4:], 16)
                self.temperature = temp / 10
                gest.enregistrerUnReleve(self.nom,self.unite,self.temperature)
            else:
                print("-> Requete invalide ! len(" + resultatRequete + ") = " + str(len(resultatRequete)))
        else:
            print("-> Resultat ISW invalide ! len(" + str(isWorking) + ") = " + str(len(isWorking)))

        