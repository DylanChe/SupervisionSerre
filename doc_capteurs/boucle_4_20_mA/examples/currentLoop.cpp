/*! \file currentLoop.cpp

	\brief Library for managing the 4-20mA Board
	Copyright (C) 2014 Libelium Comunicaciones Distribuidas S.L.
	http://www.libelium.com

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Lesser General Public License as published by
	the Free Software Foundation, either version 2.1 of the License, or
	(at your option) any later version.   
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Lesser General Public License for more details.  

	You should have received a copy of the GNU Lesser General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.

	Version:	0.1

	Implementation:	Ahmad Saad
*/

/***********************************************************************
 * Includes
 ***********************************************************************/
#include "Arduino.h"
#include "currentLoop.h"

/***********************************************************************
 * Class contructors
 ***********************************************************************/
 
 currentLoop::currentLoop() {}


/***********************************************************************
 * Methods of the Class
 ***********************************************************************/


//!*************************************************************
//!	Name:	ON()									
//!	Description: Powers ON the 4-20mA 24V supply
//!	Param : void			
//!	Returns: void						
//!*************************************************************
void currentLoop::ON()
{
	pinMode(5, OUTPUT);
	digitalWrite(5, HIGH);
	delay(1000);
	digitalWrite(5, LOW); 
}


//!*************************************************************
//!	Name:	ON()									
//!	Description: Powers ON the 4-20mA 24V supply
//!	Param : void			
//!	Returns: void						
//!*************************************************************
void currentLoop::OFF()
{
	digitalWrite(5, HIGH);
}
	
	
//!*************************************************************
//!	Name:	readChannel(uint8_t channel)
//!	Description: Get the sensor value in integer format.
//!	Param : uint8_t channel: the channel to read.	
//!	Returns: int : the avlue read (0-1023)
//!*************************************************************
int currentLoop::readChannel(uint8_t channel)
{
	long analogValue = 0;
	
	for (int i = 0; i < 50 ; i ++){
		analogValue = analogValue + analogRead(channel);
	} 
	
	return (analogValue / 50);
}


//!*************************************************************
//!	Name:	readVoltage(uint8_t channel)
//!	Description: Get the sensor value as a voltage in Volts.
//!	Param : uint8_t channel: the channel to read.
//!	Returns: the voltage value read (0 - 3V3)
//!*************************************************************
float currentLoop::readVoltage(uint8_t channel)
{
	return (readChannel(channel) * 5.0 /1023);
}


//!*************************************************************
//!	Name:	readCurrent(uint8_t channel)
//!	Description: Get the sensor value as a current in mA. 
//!	Param : uint8_t channel: the channel to read.					
//!	Returns: the current value read (4-20mA).
//!*************************************************************
float currentLoop::readCurrent(uint8_t channel)
{
	return (readVoltage(channel) / 165.0 * 1000);
}


//!*************************************************************
//!	Name:	readCurrent(uint8_t channel, float offSet)
//!	Description: Get the sensor value as a current in mA. 
//!	Param : uint8_t channel: the channel to read.
//!	        float offSet: correction offSet. 
//!	Returns: the current value read (4-20mA).
//!*************************************************************
float currentLoop::readCurrent(uint8_t channel, float offSet)
{
	return ((readVoltage(channel) / 165.0 * 1000) - offSet);
}


//!*************************************************************
//!	Name:	isConnected(uint8_t channel)
//!	Description: It checks if the current loop is well connected
//!	Param : the channel to read.			
//!	Returns: "0" if no cnnected, "1" if connected.
//!*************************************************************
uint8_t currentLoop::isConnected(uint8_t channel)
{
	if (readVoltage(channel) < 0.6)
		return 0;
	else
		return 1;
}
	
 //***********************************************************
 // Private Methods 										 *
 //***********************************************************	
 
 currentLoop sensorBoard = currentLoop();

 
