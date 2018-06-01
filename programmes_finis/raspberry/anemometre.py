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


class anemometre(capteur):
    
    def __init__(self):
        self.id = 2
        self.nom = "anemo"
        self.uniteDir = 1
        self.direction = 0
        self.uniteForce = 1
        self.force = 0
        
                
    def mesurer(self):
        print("----- ANEMOMETRE -----")
        resultatRequete = gest.getReleve(self.id)
        if len(resultatRequete) == 14:
            print("-> Requete : " + resultatRequete)
            self.uniteDir = int(resultatRequete[2:4], 16)
            self.direction = int(resultatRequete[4:8], 16)
            self.uniteForce = int(resultatRequete[8:10], 16)
            self.force = int(resultatRequete[10:], 16)
            gest.enregistrerUnReleve(self.nom,self.uniteDir,self.direction)
            gest.enregistrerUnReleve(self.nom,self.uniteForce,self.force)
        else:
            print("-> Requete invalide ! len(" + resultatRequete + ") = " + str(len(resultatRequete)))
    
