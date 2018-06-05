#!/usr/bin/python3.4
# coding: utf-8

from serial import *
with Serial(port = "/dev/ttyACM0" , baudrate = 9600, timeout = 1, writeTimeout = 1) as ser:
    if ser.isOpen():
        while True:
            ligne = ser.readline()
            #print(bytes(ligne))
            result = str(ligne, 'utf-8')
            #print(type(ligne))
            #result = ligne.decode('utf-8')
            #result2 = result[:-1]
            print (result[:-2])
            #print (ligne)


#nombre = input("Entrez un nombre : ")
#ser.write(nombre.encode('ascii'))



