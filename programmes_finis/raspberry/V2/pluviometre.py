#!/usr/bin/python3.4
# coding: utf-8

from gestion import gestion

gest = gestion()

class pluviometre():
	def __init__(self):
		self.id = 0
		self.nom = ""
		
	def setAttributes(self, p_id, p_nom):
		self.id = p_id
		self.nom = p_nom
		
	def mesurer(self):
		print("----- PLUVIOMETRE -----")
		queryResult = gest.getReleve(self.id)
		if len(queryResult) == 8: #REQUETE GET
			print("- Requete GET : " + queryResult)
		elif len(queryResult) == 2: #REQUETE ISWORKING
			print("- Requete ISWORKING : " + queryResult)
		else:
            print("-> Requete invalide ! len(" + resultatRequete + ") = " + str(len(resultatRequete)))