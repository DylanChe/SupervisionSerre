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

class sonde_temperature(capteur):
    
    def __init__(self):
            self.id = 0
            self.nom = ""
            self.unite = 0
            self.temperature = 0
    
    def setInformation(self, id_capteur, nom_capteur, unite_capteur):
        self.id = id_capteur
        self.nom = nom_capteur
        self.unite = unite_capteur
                
    def mesurer(self):
        print("----- CAPTEUR TEMPERATURE -----")
        resultatRequete = gest.getReleve(self.id)
        if len(resultatRequete) == 8:
            print("-> Requete : " + resultatRequete)
            self.unite = int(resultatRequete[2:4], 16)
            self.temperature = int(resultatRequete[4:], 16)
            gest.enregistrerUnReleve(self.nom,self.unite,self.temperature)
        else:
            print("-> Requete invalide ! len(" + resultatRequete + ") = " + str(len(resultatRequete)))