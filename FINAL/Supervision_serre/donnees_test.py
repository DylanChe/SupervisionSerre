#!/usr/bin/python3.4
# coding: utf-8

import time
import binascii

from serial import *
test = 0
with Serial(port = "/dev/ttyACM0" , baudrate = 9600, timeout = 1, writeTimeout = 1) as ser:
        if ser.isOpen():
        
            while test !=2 :
                s = bytearray() # LA REQUETE
                s.append(71) # G : NE PAS TOUCHER
                s.append(69) # E : NE PAS TOUCHER
                s.append(84) # T : NE PAS TOUCHER
                s.append(1)  # id_materiel
            
                ser.write(s) # ENVOI DE LA REQUETE
                #time.sleep(3)
                #test = ser.readline()
                ligne = ser.readline() # RECEPTION DES DONNEES
                #print(ligne)
                test = test+1
                if ligne == b'':
                    pass
                    
                else :
                    print("Succes")
                    result = ligne[:-2]
                    result = str(result, 'utf-8')
                    print ("RECEIVE : ")
                    print (result)
                time.sleep(10)
                    
                
                
                
                
                    
                
    
                
            
            
    