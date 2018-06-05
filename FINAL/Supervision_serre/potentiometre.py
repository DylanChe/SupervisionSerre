#!/usr/bin/python3.4
# coding: utf-8

from capteur import capteur

class potentiometre(capteur):
    
    def __init__(self):
        self.__direction = 0
        
                
    def mesurer(self):
        print (12)