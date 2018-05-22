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

  // Temprature sensor measure
  //=========================================================

  if (sensorBoard.isConnected(TEMPERATURE_AIR))
  {
    // Get the sensor value as a curren in mA
    current = sensorBoard.readCurrent(TEMPERATURE_AIR );
    Serial.print("Valeur mesurée : ");
    Serial.print(current);
    Serial.println(" mA");
    valeur = ((current-4)*45)/16;
    Serial.print("Le capteur de température de l'air indique: ");
    Serial.print(valeur);
    Serial.println(" °C.");
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
    Serial.print("Valeur mesurée : ");
    Serial.print(current);
    Serial.println(" mA"); 
    valeur = ((current-4)*100)/16;
    Serial.print("Le capteur de température de l'eau indique: ");
    Serial.print(valeur);
    Serial.println(" °C.");
  }
  else {
    Serial.println("Il n'y a pas de capteur connecté au port 3..");
  }


  Serial.println("***************************************");
  Serial.print("\n");

  delay(3000);


}


