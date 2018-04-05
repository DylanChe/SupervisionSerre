#!/usr/bin/python3.4
# coding: utf-8
"""
Programme : AmazingNews.py		version 0.5
Date : 07-12-2017
"""

import time
import blinkLed
import bmp280Sensor
from datetime import datetime
import mysql.connector
import RPi.GPIO as GPIO
#import mcCrypt590996
import bdd

idCapteur = 1
periodeMesure_capteur = 0

GPIO.setwarnings(False)
print('')
print("========================= AmazingNews =========================")

try:
	print("[INFO] Demarrage du thread LED...")
	thread1 = blinkLed.threadLed(1)
	thread1.start()
	print("[INFO] Thread LED : OK")
	
	print("[INFO] Demarrage du thread DISPLAY...")
	#thread2 = mcCrypt590996.threadDisplay(2)
	#thread2.start()
	print("[INFO] Thread DISPLAY : PAS OK")
	
	query = "SELECT periodeMesure_capteur FROM capteur WHERE id_capteur = '" + str(idCapteur) + "' LIMIT 1"
	periodeMesure_capteur = bdd.selectOne(query)
	
	while (1):
		currentTime = time.strftime('%y-%m-%d %H:%M:%S',time.localtime())
		#ajouterMesure(bmp280Sensor.getTemperature(), idCapteur)
		query = "INSERT INTO releves (date_releves, temperature_releves, id_capteur) VALUES ('" + str(datetime.now()) + "', '" + str(bmp280Sensor.getTemperature()) + "', '" + str(idCapteur) + "')"
		bdd.insertInto(query)
		print('[', currentTime, '] Temperature : ', str(bmp280Sensor.getTemperature()), '*C')
		time.sleep(periodeMesure_capteur)
except:
	print('[ERREUR] Arret du programme...')
	sys.exit(0)
	#thread1.pause()

