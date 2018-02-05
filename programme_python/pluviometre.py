#!/usr/bin/python3.4
import time
from datetime import datetime
import serial

ser = serial.Serial('/dev/ttyUSB0', 9600)

x = 1 while True:
	print ser.readline()
	x += 1
