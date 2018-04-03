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
#include <currentLoop.h>

#define TEMPERATURE CHANNEL1
#define HUMIDITY CHANNEL2


float current;

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

  // Temprature sensor measure
  //=========================================================

  if (sensorBoard.isConnected(TEMPERATURE))
  {
    // Get the sensor value as a curren in mA
    current = sensorBoard.readCurrent(TEMPERATURE );
    Serial.print("Current value read from temperature sensor : ");
    Serial.print(current);
    Serial.println(" mA");
  }
  else {
    Serial.println("Temperature sensor is not connected...");
  }

  Serial.println("***************************************");
  Serial.print("\n");

  // Humidity sensor measure
  //=======================================================

  delay(100);

  if (sensorBoard.isConnected(HUMIDITY))
  {
    // Get the sensor value as a curren in mA.
    current = sensorBoard.readCurrent(HUMIDITY);
    Serial.print("Current value read from humidity sensor : ");
    Serial.print(current);
    Serial.println(" mA");
  }
  else {
    Serial.println("Humidity sensor is not connected...");
  }


  Serial.println("***************************************");
  Serial.print("\n");

  delay(3000);
}




