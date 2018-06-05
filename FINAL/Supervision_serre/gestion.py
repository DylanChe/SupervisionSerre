#!/usr/bin/python3.4
# coding: utf-8


import sys
from datetime import datetime
import mysql.connector
import connexion_bdd
import time
from requete import requete
from BDD import BDD
import binascii

from serial import *

class gestion:

    
    def __init__(self, bdd) : #Constructeur de la classe
        self.__bdd = bdd
        self.__codeResult = 0
        self.__id_materiel = 0
        self.__id_unite = 0
        self.__id_type_materiel = 0
        self.__periode = 0
        
    def getReleve(self, idMateriel, portCapteur, baudCapteur):
        
        test = 0
        with Serial(port = portCapteur , baudrate = baudCapteur, timeout = 1, writeTimeout = 1) as ser:
                if ser.isOpen():
                
                    while test!=2:
                        s = bytearray() # LA REQUETE
                        s.append(71) # G : NE PAS TOUCHER
                        s.append(69) # E : NE PAS TOUCHER
                        s.append(84) # T : NE PAS TOUCHER
                        s.append(idMateriel)  # id_materiel
                    
                        ser.write(s) # ENVOI DE LA REQUETE
                        time.sleep(3)
                        #test = ser.readline()
                        ligne = ser.readline() # RECEPTION DES DONNEES
                        test = test+1
                        if ligne == b'':
                            pass
                            
                        else :
                            
                            result = ligne[:-2]
                            result = str(result, 'utf-8')
                            print ("RECEIVE : ")
                            print (result)
                            #time.sleep(10)
                            return result
							
    def getOperatingState(self, idMateriel, portCapteur, baudCapteur):
        test = 0
        with Serial(port = portCapteur , baudrate = baudCapteur, timeout = 1, writeTimeout = 1) as ser:
            if ser.isOpen():
                while test!=2:
                    s = bytearray() # LA REQUETE
                    s.append(73) # I : NE PAS TOUCHER
                    s.append(83) # S : NE PAS TOUCHER
                    s.append(87) # W : NE PAS TOUCHER
                    s.append(idMateriel)  # id_materiel
                    
                    ser.write(s) # ENVOI DE LA REQUETE
                    #time.sleep(3)
                    #test = ser.readline()
                    ligne = ser.readline() # RECEPTION DES DONNEES
                    test = test+1
                    if ligne == b'':
                        pass
                        
                    else :
                        result = ligne[:-2]
                        result = str(result, 'utf-8')
                        print ("RECEIVE : ")
                        print (result)
                        return result
    
    def enregistrerUnEtat(self, id_materiel, operatingState):
        print("MAJ DE L'ETAT DU CAPTEUR")
        sqlQuery = "UPDATE materiel SET est_fonctionnel={} " \
                "WHERE id = {}"\
                .format(operatingState, id_materiel)
        self.__executerReqInsert(sqlQuery)
        
        if self.__codeResult == 0:
            print("Succès exécution req UPDATE")
        else :
            print("ERROR - \n{}".format(sys.exc_info()))
        
        
        print("AJOUT DANS LE JOURNAL")
        date_releve = datetime.now()
        
        sqlQuery = "INSERT INTO journal(est_fonctionnel, date_panne, id_materiel) " \
                "VALUES ({}, '{}', {})"\
                .format(operatingState, date_releve, id_materiel)
        self.__executerReqInsert(sqlQuery)
        
        if self.__codeResult == 0:
            print("Succès exécution req INSERT")
        else :
            print("ERROR - \n{}".format(sys.exc_info()))


    def enregistrerUnReleve(self, abreviation, unite, valeur):
        """ Enregistre une mesure
            abreviation : str -> type de capteur   exemple : "pluvio" ; "anemo"...
            unite : str-> type d'unite exemple : "mm" ; "°C"...
            valeur : int-> exemple : 200; 40; 10...
            retour : int ->  codeResult = 0 (succès)      codeResult > 0 (échec)
        """
        # récupérer id_unite
        self.__id_unite = unite
        
        #id_unite  = "SELECT id FROM unite WHERE unite = '{}'".format(self.__unite)
        #id_unite = unite

        #recuperer id_releve
        self.__id_releve = valeur
        
        id_releve = "SELECT id FROM releve WHERE valeur = {}".format(self.__id_releve)
 

        
        # récupérer id_materiel
        self.__abreviation = abreviation
        
        id_capteur = "SELECT id FROM materiel WHERE abreviation = '{}'".format(self.__abreviation)
        
        
        # récupérer id_type_materiel
        #id_type_materiel = "SELECT id FROM  type_materiel WHERE nom = {}"\
        #.format(gestion.typeMateriel["Capteur"])
        id_type_materiel = "SELECT id_type_materiel FROM  materiel WHERE abreviation = '{}'"\
                           .format(self.__abreviation)
        
        """ Recupere l'id du typeMateriel entre en parametre
        """
        
        
        
        print(self.__bdd.__dict__)
        print(self.__bdd.cnx.is_connected())#verifie si la connexion est prete return True ou False
        
        #recupere l'id de l'unite rentre en parametre (unite)
        #self.__executerReqSelectIdUnite(id_unite)
        """
        id_unite -> requete sql 
        """
        if self.__codeResult == 0:
            print("Succès exécution req SELECT")
            
        else :
            print("ERROR - \n{}".format(sys.exc_info()))
        
    
        # recupere l'id du materiel rentre en parametre (abreviation)
        self.__executerReqSelectIdMat(id_capteur)
        """
        id_capteur -> requete sql
        """
        if self.__codeResult == 0:
            print("Succès exécution req SELECT")
            
        else :
            print("ERROR - \n{}".format(sys.exc_info()))
        
    
    
        #recupere l'id du type de materiel rentre en pararmetre (abreviation)
        self.__executerReqSelectIdTypeMat(id_type_materiel)
        """
        id_type_mat -> requete sql
        """
        if self.__codeResult == 0:
            print("Succès exécution req SELECT")
            
        else :
            print("ERROR - \n{}".format(sys.exc_info()))
        
        # inserer la mesure
        self.__valeur = valeur 
        #self__id_unite = id_unite
        date_releve = datetime.now()
        #self__id_capteur = id_capteur
        
        
        sqlQuery = "INSERT INTO releve(valeur, id_unite, date_releve, id_materiel) " \
                "VALUES ({}, {}, '{}', {})"\
                .format(self.__valeur, self.__id_unite, date_releve, self.__id_materiel)
        self.__executerReqInsert(sqlQuery)
        
        if self.__codeResult == 0:
            print("Succès exécution req INSERT")
            
        else :
            print("ERROR - \n{}".format(sys.exc_info()))
            
            
    def RecupPeriode(self):
        
        query = "SELECT periode FROM  parametre "
        self.__ReqPeriode(query)
        if self.__codeResult == 0:
            print("Succès exécution req SELECT")
            
        else :
            print("ERROR - \n{}".format(sys.exc_info()))
            
        return self.__periode            
        
                           
        
            
                     
    def __executerReqInsert(self, sqlQuery):
        try:
            if not self.__bdd.cnx.is_connected():
                self.__bdd.cnx.reconnect()
                """
                test si l'objet est connecté a la BDD si non elle tente une connection
                """
                if not self.__bdd.cnx.is_connected():
                    raise ValueError
            cur = self.__bdd.cnx.cursor()
            print(sqlQuery)
            cur.execute(sqlQuery)
            """
            execute la requete entrer en parametre
            """
            self.__bdd.cnx.commit()
            """
            envoie l'exécution de la requete sur la base de données
            """
            cur.close()
        except ValueError:# verifie si il y a une erreurt de connexion a la base de données
            self.__codeResult = 10
            print("ERROR - échec connexion bdd")
        except:# verifie si il y a une autre erreur et nous indique de quel type il s'agit
            self.__codeResult = 11
            print("ERROR - \n{}".format(sys.exc_info()))
        
        return self.__codeResult
                    
                    
    def __executerReqSelectIdUnite(self, sqlQuery):
      try:
        if not self.__bdd.cnx.is_connected():
            self.__bdd.cnx.reconnect()
            if not self.__bdd.cnx.is_connected():
                raise ValueError
        cur = self.__bdd.cnx.cursor()
        print(sqlQuery)
        cur.execute(sqlQuery)
        self.__id_unite = cur.fetchone()[0]
        print( "essai = ", self.__id_unite)
        cur.close()
      except ValueError:
        self.__codeResult = 10
        print("ERROR - échec connexion bdd")
      except:
        self.__codeResult = 11
        print("ERROR - \n{}".format(sys.exc_info()))

      return self.__codeResult, self.__id_unite
    
    def __executerReqSelectIdMat(self, sqlQuery):
      try:
        if not self.__bdd.cnx.is_connected():
            self.__bdd.cnx.reconnect()
            if not self.__bdd.cnx.is_connected():
                raise ValueError
        cur = self.__bdd.cnx.cursor()
        print(sqlQuery)
        """
            affiche la requete entrer en parametre
        """
        cur.execute(sqlQuery)
        """
            execute la requete entrer en parametre
        """
        self.__id_materiel = cur.fetchone()[0]
        """
            récupére seulement l'élément demandé à la base de données
        """
        print( "essai = ", self.__id_materiel)
        cur.close()
      except ValueError:# verifie si il y a une erreurt de connexion a la base de données
        self.__codeResult = 10
        print("ERROR - échec connexion bdd")
      except:# verifie si il y a une autre erreur et nous indique de quel type il s'agit
        self.__codeResult = 11
        print("ERROR - \n{}".format(sys.exc_info()))

      return self.__codeResult, self.__id_materiel
    
    def __executerReqSelectIdTypeMat(self, sqlQuery):
      try:
        if not self.__bdd.cnx.is_connected():
            self.__bdd.cnx.reconnect()
            if not self.__bdd.cnx.is_connected():
                raise ValueError
        cur = self.__bdd.cnx.cursor()
        print(sqlQuery)
        """
            affiche la requete entrer en parametre
        """
        cur.execute(sqlQuery)
        """
            execute la requete entrer en parametre
        """
        self.__id_type_materiel = cur.fetchone()[0]
        """
            récupére seulement l'élément demandé à la base de données
        """
        cur.close()
      except ValueError:# verifie si il y a une erreurt de connexion a la base de données
        self.__codeResult = 10
        print("ERROR - échec connexion bdd")
      except:# verifie si il y a une autre erreur et nous indique de quel type il s'agit
        self.__codeResult = 11
        print("ERROR - \n{}".format(sys.exc_info()))

      return self.__codeResult, self.__id_type_materiel
    
    
    def __ReqPeriode(self, sqlQuery):
      try:
        if not self.__bdd.cnx.is_connected():
            self.__bdd.cnx.reconnect()
            if not self.__bdd.cnx.is_connected():
                raise ValueError
        cur = self.__bdd.cnx.cursor()
        print(sqlQuery)
        """
            affiche la requete entrer en parametre
        """
        cur.execute(sqlQuery)
        """
            execute la requete entrer en parametre
        """
        self.__periode = cur.fetchone()[0]
        """
            récupére seulement l'élément demandé à la base de données
        """
        cur.close()
      except ValueError:# verifie si il y a une erreurt de connexion a la base de données
        self.__codeResult = 10
        print("ERROR - échec connexion bdd")
      except:# verifie si il y a une autre erreur et nous indique de quel type il s'agit
        self.__codeResult = 11
        print("ERROR - \n{}".format(sys.exc_info()))

      return self.__codeResult, self.__periode
    
    def clearIncomingData(self):
        with Serial(port = "/dev/ttyACM0" , baudrate = 9600, timeout = 1, writeTimeout = 1) as ser:
            if ser.isOpen():
                ligne = ser.readline()
        
      
        
        
