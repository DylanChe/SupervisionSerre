/*
 *
 *  Explanation: This sketch shows how to use the most important
 *  features of the 4-20mA current loop board in Waspmote. This
 *  standard is used to transmit information of sensor over long
 *  distances. Waspmote uses analog inputs for reading the sensor
 *  values.
 *
 *  Copyright (C) 2014 Libelium Comunicaciones Distribuidas S.L.
 *  http://www.libelium.com
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *  Version:          0.1
 *  Design:           David Gascon
 *  Implementation:   Ahmad Saad
 */

// Include this library for using current loop functions
#line 2 "sketch.ino"
#include <ArduinoUnit.h>

#include <currentLoop.h>

#define TEMPERATURE_AIR CHANNEL1
#define TEMPERATURE_EAU CHANNEL3

float current;
float valeur;

void setup()
{
  // Init Serial for viewing data in the serial monitor.
  Serial.begin(115200);
  delay(100);
  // Switch the 24V DC-DC converter
  sensorBoard.ON();
  delay(2000);
}

void loop()
{

  // Mesure température de l'air
  //=========================================================

  if (sensorBoard.isConnected(TEMPERATURE_AIR))
  {
    // Get the sensor value as a curren in mA
	  current=get_current_air;
    Serial.print("La valeur du capteur temperature de l'air est : ");
    Serial.print(current);
    Serial.println(" mA");
    valeur=get_temperature_air; 
    Serial.print("La température de l'air est de");
    Serial.print(valeur);
    Serial.println(" °C");
  }
  else {
    Serial.println("Il n'y a pas de capteur connecté au port 1..");
  }

  // mesure température eau
  //=======================================================

  delay(100);

  if (sensorBoard.isConnected(TEMPERATURE_EAU))
  {
    current=get_current_eau;
    Serial.print("La valeur du capteur temperature de l'eau est : ");
    Serial.print(current);
    Serial.println(" mA"); 
    valeur=get_temperature_eau;
    Serial.print("La température de l'eau est de : ");
    Serial.print(valeur);
    Serial.println(" °C");
  }
  else {
    Serial.println("Il n'y a pas de capteur connecté au port 3..");
  }


  Serial.println("***************************************");
  Serial.print("\n");

  delay(3000);
}

//obtention valeur mA capteur
float get_current_air() {
	current=0;
	if (sensorBoard.isConnected(TEMPERATURE_AIR))		
  {
    // Obtenir la valeur du capteur en mA
    current = sensorBoard.readCurrent(TEMPERATURE_AIR );
  }
  return current;
}

//obtention valeur mA capteur
float get_current_eau() {
  current=0;
	if (sensorBoard.isConnected(TEMPERATURE_EAU))
  {
    // Obtenir la valeur du capteur en mA
    current = sensorBoard.readCurrent(TEMPERATURE_EAU);
  }
  return current;
}

//obtention valeur °C
float get_temperature_air() {
  valeur=0;
  if (sensorBoard.isConnected(TEMPERATURE_AIR))
  {
	  // Calcul pour obtenir la valeur en degrés
	  valeur = ((get_current_air-4)*45)/16;
  }
  return valeur;
}

//obtention valeur °C
float get_temperature_eau() {
	valeur=0;
  if (sensorBoard.isConnected(TEMPERATURE_EAU))
  {
    // Calcul pour obtenir la valeur en degrés
    valeur = ((get_current_eau-4)*100)/16;
  }
  return valeur;
}

//test unitaire mA température air
test(current_air) 
{
  float current=get_current_air;
  int max_ma=20;
  assertLessOrEqual(current,y);
}

test(current_air)
{
  float current=get_current_air;
  int min_ma=4;
  assertMoreOrEqual(current,min_ma);
}

//test unitaire ma temperature eau
test(current_eau)
{
  float current=get_current_eau;
  int max_ma=20;
  assertLessOrEqual(current,y);
}

test(current_eau)
{
  float current=get_current_eau;
  int min_ma=4;
  assertMoreOrEqual(current,min_ma);
}

void setup()
{
  Serial.begin(9600);
  while(!Serial) {} // Portability for Leonardo/Micro
}

void loop()
{
  Test::run();
}

