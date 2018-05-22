#line 2 "sketch.ino"
#include <ArduinoUnit.h>

#include <currentLoop.h>

#define TEMPERATURE_AIR CHANNEL1
#define TEMPERATURE_EAU CHANNEL3


float current;
float valeur;
float current_eau_min;


test(ok)
  {
    current = sensorBoard.readCurrent(CHANNEL1);
    float min_ma = 4;
    assertMoreOrEqual(current,min_ma);
  }


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

  if (sensorBoard.isConnected(TEMPERATURE_AIR))
  {
    // Get the sensor value as a curren in mA
    current = sensorBoard.readCurrent(TEMPERATURE_AIR );
    Serial.print("La valeur du capteur temperature de l'air est : ");
    Serial.print(current);
    Serial.println(" mA");
    valeur = ((current-4)*45)/16;
    Serial.println(valeur);
  }
  else {
    Serial.println("Il n'y a pas de capteur connecté au port 1..");
  }

  // Humidity sensor measure
  //=======================================================

  delay(100);

  if (sensorBoard.isConnected(TEMPERATURE_EAU))
  {
    // Get the sensor value as a curren in mA.
    current = sensorBoard.readCurrent(TEMPERATURE_EAU);
    Serial.print("La valeur du capteur temperature de l'eau est : ");
    Serial.print(current);
    Serial.println(" mA"); 
    valeur = ((current-4)*100)/16;
    Serial.println(valeur);
  }
  else {
    Serial.println("Il n'y a pas de capteur connecté au port 3..");
  }


  Serial.println("***************************************");
  Serial.print("\n");

  delay(3000);
  Test::run();


}


