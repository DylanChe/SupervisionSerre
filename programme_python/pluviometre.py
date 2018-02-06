#!/usr/bin/python3.4
import time
from datetime import datetime
import serial

ser = serial.Serial('/dev/ttyUSB0', 9600)

x = 1 while True:
	now = datetime.datetime.now()
	heure = now.strftime('%Y-%m-%d %H:%M:%S.%f')
	print(heure, ser.readline())
	x += 1
