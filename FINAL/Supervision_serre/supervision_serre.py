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
from requete import requete
req = requete()
cnx = mysql.connector.connect(host="92.222.92.147", user="projetbts", password="Nantes44", \
                              database="supervision_serre")
typeMat = "mic"
req.requete_type_materiel(typeMat,cnx)


