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
        
                
    def mesurer(self):
        print("----- PLUVIOMETRE -----")
        resultatRequete = gest.getReleve(self.id)
        if len(resultatRequete) == 8:
            print("-> Requete : " + resultatRequete)
            self.unite = int(resultatRequete[2:4], 16)
            self.impulsion = int(resultatRequete[4:], 16)
            gest.enregistrerUnReleve(self.nom,self.unite,self.impulsion)
        else:
            print("-> Requete invalide ! len(" + resultatRequete + ") = " + str(len(resultatRequete)))
     