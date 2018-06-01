#!/usr/bin/python3.4
# coding: utf-8

from capteur import capteur

class sonde_temperature(capteur):
    
    def __init__(self):
        self.__tension = 0
        
                
    def mesurer(self):
        print(45)