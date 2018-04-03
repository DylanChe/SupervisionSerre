/*
 *  Explanation: This sketch shows how to use the most important
 *  features of the 4-20mA current loop board in Waspmote. This
 *  standard is used to transmit information of sensor over long
 *  distances. Arduino uses analog inputs for reading the sensor
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

//Include this library for using current loop functions.
#include <currentLoop.h>
#define CHANNEL CHANNEL2


void setup()
{
  //Inits the Serial for viewing data in the serial monitor.
  Serial.begin(115200);

  //Switch ON the 24V DC-DC converter
  sensorBoard.ON();

  delay(100);
  Serial.println("Arduino 4-20mA board switched ON...");
}

void loop()
{
  if (sensorBoard.isConnected(CHANNEL))
  {
    // Get the sensor value in int format (0-1023).
    int value = sensorBoard.readChannel(CHANNEL);
    Serial.print("Int value read from channel 1 : ");
    Serial.println(value);

    // Get the sensor value as a voltage in Volts.
    float voltage = sensorBoard.readVoltage(CHANNEL);
    Serial.print("Voltage value rad from channel 1 : ");
    Serial.print(voltage);
    Serial.println(" V");

    // Get the sensor value as a curren in mA.
    float current = sensorBoard.readCurrent(CHANNEL);
    Serial.print("Current value read from channel 1 : ");
    Serial.print(current);
    Serial.println(" mA");


    Serial.println("***************************************");
    Serial.print("\n");

  }
  else {

    Serial.println("The sensor is not connected...");

  }

  //Delay after reading.
  delay(2500);

}



