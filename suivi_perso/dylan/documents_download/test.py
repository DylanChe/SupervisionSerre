#!/usr/bin/env python
# -*- coding: latin-1 -*-

import serial

class rainGaugeDavis7852:
	

ser = serial.Serial('/dev/ttyACM0', 9600)
while 1 :
  	print(ser.readline())