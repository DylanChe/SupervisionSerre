#!/usr/bin/python3.4
# coding: utf-8



from gestion import gestion
from BDD import BDD
import mysql.connector
import connexion_bdd
from donnees import donnees
from time import sleep
from potentiometre import potentiometre
from pluviometre import pluviometre
from anemometre import anemometre
from sondeEC import sondeEC
from sondeSS import sondeSS


bdd = BDD()
gest = gestion(bdd)
anemo = anemometre()
pluvio = pluviometre()
sonde_tuyau = sondeEC()
sonde_serre = sondeSS()
periode = gest.RecupPeriode()
print (periode)




while 1:

    pluvio.mesurer()
    #anemo.mesurer()
    #sonde_tuyau.mesurer()
    #sonde_serre.mesurer()
    sleep(10) #periode
    

