#!/usr/bin/python3.4
# coding: utf-8


import mysql.connector


class BDD:
    def __init__(self): #Constructeur pour tester la connexion a la bdd
        try:
            self.cnx = mysql.connector.connect(host="92.222.92.147", user="projetbts", \
                                                 password="Nantes44", \
                              database="supervision_serre")
        except:
            print("Connexion impossible")