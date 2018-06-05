#!/usr/bin/python3.4
# coding: utf-8

import unittest
import random

from gestion import gestion
from BDD import BDD
import mysql.connector
import connexion_bdd

class RandomTest(unittest.TestCase):
    def test_insert(self):
        bdd = BDD()
        gest = gestion(bdd)
        typeMat1 = "Microme"
        test = gest.ajouterNouveauTypeMat(typeMat1)
        
        self.assertEqual(test, 1)
    
    


    
    
unittest.main()    


