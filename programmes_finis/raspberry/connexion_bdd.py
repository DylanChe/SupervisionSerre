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
	'user': 'projet bts',
	'password': 'Nantes44',
	'host': '92.222.92.147',
	'database': 'supervision_serre',
}


def insertInto(query):
	cnx = mysql.connector.connect(**config)
	cur = cnx.cursor()
	cmd = query
	cur.execute(cmd)
	cnx.commit()
	cur.close()
	cnx.close()
