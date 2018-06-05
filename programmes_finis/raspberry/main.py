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
# SET sondeEC
# sondeEC.setID(1);
sondeSS = sonde_temperature()
# SET sondeSS
#
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
    #sondeEC.mesurer()
    sleep(5) #periode
    


#abreviationCapt = "poten"
#unite = "mm"
#gest.enregistrerUnReleve(abreviationCapt , unite, valeur,)



