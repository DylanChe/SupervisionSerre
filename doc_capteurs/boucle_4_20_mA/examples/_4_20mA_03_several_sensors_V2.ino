#line 2 "sketch.ino"
#include <ArduinoUnit.h>

// Include this library for using current loop functions
#include <currentLoop.h>

#define TEMPERATURE_AIR CHANNEL1
#define TEMPERATURE_EAU CHANNEL3


float current;
float valeur;

void setup()
void loop()
{

  // Mesure température de l'air
  // Temprature sensor measure
  //=========================================================

  if (sensorBoard.isConnected(TEMPERATURE_AIR))
  {
    // Get the sensor value as a curren in mA
    current=get_current_air;
    current = sensorBoard.readCurrent(TEMPERATURE_AIR );
    Serial.print("La valeur du capteur temperature de l'air est : ");
    Serial.print(current);
    Serial.println(" mA");
    valeur=get_temperature_air; 
    Serial.print("La température de l'air est de");
    Serial.print(valeur);
    Serial.println(" °C");
    valeur = ((current-4)*45)/16;
    Serial.println(valeur);
  }
  else {
    Serial.println("Il n'y a pas de capteur connecté au port 1..");
  }

  // mesure température eau
  // Humidity sensor measure
  //=======================================================

  delay(100);

  if (sensorBoard.isConnected(TEMPERATURE_EAU))
  {
    current=get_current_eau;
    // Get the sensor value as a curren in mA.
    current = sensorBoard.readCurrent(TEMPERATURE_EAU);
    Serial.print("La valeur du capteur temperature de l'eau est : ");
    Serial.print(current);
    Serial.println(" mA"); 
    valeur=get_temperature_eau;
    Serial.print("La température de l'eau est de : ");
    Serial.print(valeur);
    Serial.println(" °C");
    valeur = ((current-4)*100)/16;
    Serial.println(valeur);
  }
  else {
    Serial.println("Il n'y a pas de capteur connecté au port 3..");
    Test::run();


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
test(current_air_max) 
{
  float current=get_current_air;
  int max_ma=20;
  assertLessOrEqual(current,y);
}

test(current_air_min)
{
  float current=get_current_air;
  int min_ma=4;
  assertMoreOrEqual(current,min_ma);
}

//test unitaire ma temperature eau
test(current_eau_max)
{
  float current=get_current_eau;
  int max_ma=20;
  assertLessOrEqual(current,y);
}

test(current_eau_min)
{
  float current=get_current_eau;
  int min_ma=4;
  assertMoreOrEqual(current,min_ma);
}
