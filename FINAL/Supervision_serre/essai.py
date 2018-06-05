#!/usr/bin/python3.4
# coding: utf-8
"""
Programme : AmazingNews.py		version 0.5
Date : 07-12-2017
"""

from datetime import datetime
import mysql.connector
import connexion_bdd
import time

cnx = mysql.connector.connect(host="92.222.92.147", user="projetbts", password="Nantes44", \
                          database="supervision_serre")l

def requete_unite(abreviation):
    try:
        

        
        nom = abreviation
        cur = cnx.cursor()
        query = "INSERT INTO unite( unite )" \
                "VALUES ('{}')".format(nom)               
        print(query)
        cur.execute(query)
        cnx.commit()
    except:
        print("Connexion impossible")
        
    
def requete_materiel(abreviationMat, est_fonctionnel,id_unite): #Requete pour materiel
        try:
            abreviation = abreviationMat
            fonc = est_fonctionnel
            unite = id_unite	
            cur = cnx.cursor()
            query = "INSERT INTO materiel(abreviation, est_fonctionnel, id_unite) VALUES ('{}', {}, {})" \
                    .format(abreviation, fonc, unite)
            print(query)
            cur.execute(query)
            cnx.commit()
                 
        except:
            print("Connexion impossible")

requete_unite("cm")
requete_materiel("pluviol",1,2)