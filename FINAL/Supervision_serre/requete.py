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


class requete:
    
    cnx = mysql.connector.connect(host="92.222.92.147", user="projetbts", password="Nantes44", \
                              database="supervision_serre")

    def __init__(self):
        """ Constructeur
        """
        pass

    def requete_materiel(self, abreviationMat, est_fonctionnel,m_id_unite): #Requete pour materiel
        try:
            self.abreviation = abreviationMat
            self.est_fonctionnel = est_fonctionnel
            self.id_unite = m_id_unite	
            cur = cnx.cursor()
            query = "INSERT INTO materiel(abreviation, est_fonctionnel, id_unite) VALUES ('{}', {}, {})" \
                    .format(abreviation, etat, id_unite)
            print(query)
            cur.execute(query)
            cnx.commit()
                 
        except:
            print("Connexion impossible")
            
            
        


    def requete_releve(self, m_valeur, id_unite, m_id_materiel):
        try:
            self.valeur = m_valeur
            self.id_unite = m_id_unite
            self.date_releve = datetime.now()
            self.id_materiel = m_id_materiel
            cur = cnx.cursor()
            query = "INSERT INTO releve(valeur, id_unite, date_releve, id_materiel) " \
                    "VALUES ({}, {}, '{}', {})"\
                    .format(valeur, id_unite, date_releve, id_materiel)
            print(query)
            cur.execute(query)
            cnx.commit()
            
        except:
            print("Connexion impossible")
            
        

    def requete_unite(self,m_unite):
        try:
            self.unite = m_unite
            cur = cnx.cursor()
            query = "INSERT INTO unite( unite ) VALUES ('{}')".format(unite)
            print(query)
            cur.execute(query)
            cnx.commit()
        except:
            print("Connexion impossible")


    def requete_materiel_unite(self, m_id, m_id_unite):
        try:
            self.id = m_id
            self.id_unite = m_id_unite
            cur = cnx.cursor()
            query = "INSERT INTO materiel_unite( id, id_unite ) VALUES ({},{})"\
                    .format(id, id_unite)
            print(query)
            cur.execute(query)
            cnx.commit()
        except:
            print("Connexion impossible")


    def requete_journal(self, m_est_fonctionnel, m_id_materiel):
        try:
            self.est_fonctionnel = m_est_fonctionnel
            self.date_panne = datetime.now()
            self.id_materiel = m_id_materiel
            cur = cnx.cursor()
            query = "INSERT INTO journal( est_fonctionnel, date_panne, id_materiel )" \
                    "VALUES ({},'{}',{})".format(est_fonctionnel, date_panne, id_materiel)               
            print(query)
            cur.execute(query)
            cnx.commit()
        except:
            print("Connexion impossible")
            

    def requete_unite(self,m_abreviation):
        try:
            cnx = mysql.connector.connect(host="92.222.92.147", user="projetbts", password="Nantes44", \
                              database="supervision_serre")

            
            abreviation = "cm"
            cur = cnx.cursor()
            query = "INSERT INTO unite( abreviation )" \
                    "VALUES ('{}')".format(m_abreviation)               
            print(query)
            cur.execute(query)
            cnx.commit()
        except:
            print("Connexion impossible")   
    
  
  


                

