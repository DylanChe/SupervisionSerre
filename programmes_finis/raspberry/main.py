#!/usr/bin/python3.4
# coding: utf-8



from gestion import gestion
from BDD import BDD
import mysql.connector
import connexion_bdd
from time import sleep
from potentiometre import potentiometre
from pluviometre import pluviometre
from anemometre import anemometre
from sonde_temperature import sonde_temperature


bdd = BDD()
gest = gestion(bdd)
anemo = anemometre()
pluvio = pluviometre()
poten = potentiometre()
sondeEC = sonde_temperature()
<<<<<<< HEAD
# SET sondeEC
# sondeEC.setID(1);
sondeSS = sonde_temperature()
# SET sondeSS
#
=======
sondeEC.setInformation(4,"sonde_tuyau",4)
sondeSS = sonde_temperature()
sondeSS.setInformation(5,"sonde_serre",4)
>>>>>>> 2f591b7ed6813cc09f93388e8a2e073da7e0837a
periode = gest.RecupPeriode()
print (periode)

sleep(1)
gest.clearIncomingData()
sleep(1)

while 1:
    #poten.mesurer()
    #pluvio.mesurer()
    #anemo.mesurer()
    #sondeSS.mesurer()
<<<<<<< HEAD
    #sondeEC.mesurer()
    sleep(5) #periode
=======
    sondeEC.mesurer()
    sleep(5)
>>>>>>> 2f591b7ed6813cc09f93388e8a2e073da7e0837a
    


#abreviationCapt = "poten"
#unite = "mm"
#gest.enregistrerUnReleve(abreviationCapt , unite, valeur,)



