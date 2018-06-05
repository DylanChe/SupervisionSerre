#!/usr/bin/python3.4
# coding: utf-8
import mysql.connector
import connexion_bdd

cnx = mysql.connector.connect(host="92.222.92.147", user="projetbts", password="Nantes44", \
                              database="supervision_serre")
cur = cnx.cursor()
sqlQuery = "SELECT * FROM materiel WHERE id = {}".format(2)
print(sqlQuery)
cur.execute(sqlQuery)
