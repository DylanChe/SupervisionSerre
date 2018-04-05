#!/usr/bin/python3.4
# coding: utf-8
"""
Programme : bdd.py		version 1.0
Date : 07-12-2017
"""

import time
from datetime import datetime
import mysql.connector


config = {
	'user': 'Amazingnews',
	'password': 'Nantes44',
	'host': '10.16.2.139',
	'database': 'bddmp7',
}

def selectOne(query):
	cnx = mysql.connector.connect(**config)
	cur = cnx.cursor()
	cmd = query
	cur.execute(cmd)
	result = cur.fetchone()
	cur.close()
	cnx.close()
	return result[0]

def insertInto(query):
	cnx = mysql.connector.connect(**config)
	cur = cnx.cursor()
	cmd = query
	cur.execute(cmd)
	cnx.commit()
	cur.close()
	cnx.close()
