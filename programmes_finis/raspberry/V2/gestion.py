#!/usr/bin/python3.4
# coding: utf-8

import sys
import time
import binascii
from datetime import datetime
from serial import *

class gestion:
	def __init__(self):
		self.__codeResult = 0
		self.__periode = 0
		
	def getReleve(self, portCapteur, idMateriel):
		print("--- ENVOI DE GETRELEVE POUR ID = " + idMateriel)
		with Serial(port = portCapteur, baudrate = 9600, timeout = 1, writeTimeout = 1) as ser:
			if ser.isOpen():
				print("- Communication serie ouverte !")
				s = bytearray()
				s.append(71)
				s.append(69)
				s.append(84)
				s.append(idMateriel)
				
				ser.write(s)
				
				ligne = ser.readline()
				result = ligne[:-2]
				result = str(result, 'utf-8')
				print("--- Resultat = " + result)
				return result
			else:
				print("- Impossible d'ouvrir la communication serie !")
				