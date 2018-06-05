#!/usr/bin/python3.4
# coding: utf-8

from gestion import gestion
from time import sleep
from pluviometre import pluviometre

pluvio = pluviometre()
pluvio.setAttributes(1, "pluvio")

while 1:
	pluvio.mesurer()
	sleep(5)
