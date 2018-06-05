#!/usr/bin/python3.4
# coding: utf-8

from capteur import capteur
from gestion import gestion
from donnees import donnees
from BDD import BDD
import mysql.connector
import connexion_bdd
from time import sleep

import time
import binascii

from serial import *
bdd = BDD()
gest = gestion(bdd)
donn = donnees()




class anemometre(capteur):
    
    def __init__(self):
        self.id = 2
        self.nom = "anemo"
        self.uniteDir = 1
        self.direction = 0
        self.uniteForce = 1
        self.force = 0
        self.port = "/dev/ttyACM0"
        self.baud = 9600
        self.estFonctionnel = 0
        
                
    def mesurer(self):
        print("----- ANEMOMETRE -----")
        print("- Envoi de ISW" + str(self.id))
        isWorking = "0201"
        """
        envoie l'Id du capteur et si il est fonctionnel
        """
        
        if len(isWorking) == 4:
            """
            verifie la longueur de la donnees
            """
            print("-> estFonctionnel = " + str(isWorking))
            if self.estFonctionnel != int(isWorking[2:], 16):
                self.estFonctionnel = int(isWorking[2:], 16)
                gest.enregistrerUnEtat(self.id, self.estFonctionnel)
                print("-> Etat MAJ dans la BDD : " + str(isWorking))
            print("- Envoi de GET" + str(self.id))
    
            resultatRequete = gest.getReleve(self.id, self.port, self.baud)
            """
            récupère les données envoyées par l'Arduino
            """
            if len(resultatRequete) == 14:
                """
                verifie si la longueur des données est exacte (ici 14 octets)
                """
                print("-> Requete : " + resultatRequete)
                self.uniteDir = int(resultatRequete[2:4], 16)
                """
                modifie l'hexa reçu en int pour l'octet 3 et 4
                """
                self.direction = int(resultatRequete[4:8], 16)
                """
                modifie l'hexa reçu en int pour l'octet 5 à 8
                """
                self.uniteForce = int(resultatRequete[8:10], 16)
                """
                modifie l'hexa reçu en int pour l'octet 9 et 10
                """
                self.force = int(resultatRequete[10:], 16)
                """
                modifie l'hexa reçu en int pour l'octet 10 et plus
                """
                gest.enregistrerUnReleve(self.nom,self.uniteDir,self.direction)
                """
                enregistre le releve pour la direction du vent
                """
                
                gest.enregistrerUnReleve(self.nom,self.uniteForce,self.force)
                """
                enregistre le releve pour la vitesse du vent
                """
                
            else:
                """
                nous indique que la longueur des données n'est pas exact
                """
                print("-> Requete invalide ! len(" + resultatRequete + ") = " + str(len(resultatRequete)))
        else:
            print("-> Resultat ISW invalide ! len(" + str(isWorking) + ") = " + str(len(isWorking)))
