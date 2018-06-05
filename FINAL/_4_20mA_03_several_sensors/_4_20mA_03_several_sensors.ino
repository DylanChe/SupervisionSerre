#include <currentLoop.h>

#define TEMPERATURE_AIR CHANNEL1
#define TEMPERATURE_EAU CHANNEL3


float current;

// COMMUNICATION
// --- RECEPTION
const byte numReceivedBytes = 4;
char receivedBytes[numReceivedBytes];
int receivedIndex = 0;

// --- ENVOI
byte idMat;
byte idUnit1;
byte valMesurePrinc1;
byte valMesurePrinc2;
byte idUnit2;
byte valMesureSecond1;
byte valMesureSecond2;

// --- TEMPERATURE AIR
float valeur_air;
int valeur_air_entier;

// --- TEMPERATURE EAU
float valeur_eau;
int valeur_eau_entier;



void setup()
{
  // Init Serial for viewing data in the serial monitor.
  Serial.begin(115200);
  // Switch the 24V DC-DC converter
  sensorBoard.ON();
 
}

void temperature_air() {
  if (sensorBoard.isConnected(TEMPERATURE_AIR))
  {
    current = sensorBoard.readCurrent(TEMPERATURE_AIR );
    valeur_air = ((current-4)*45)/16;
    
  }
  else 
  {
    Serial.println("Il n'y a pas de capteur connecté au port 1..");
  }
}

void loop() {
  
}

void temperature_eau() {
  if (sensorBoard.isConnected(TEMPERATURE_EAU))
  {
    current = sensorBoard.readCurrent(TEMPERATURE_EAU );
    valeur_eau = ((current-4)*100)/16;
  }
  else 
  {
    Serial.println("Il n'y a pas de capteur connecté au port 3..");
  }
}

void serialEvent() {
  while (Serial.available()) {
    byte inByte = Serial.read();
    receivedBytes[receivedIndex] = inByte;
    receivedIndex += 1;
    
    if (receivedIndex >= numReceivedBytes) {
      switch (receivedBytes[3]) {
        case 5: // ENVOI DES DONNEES DE TEMPERATURE AIR
        temperature_air();
          idMat = 5;
          idUnit1 = 4;
          valeur_air_entier = valeur_air * 10;
          //Serial.println(valeur_air_entier);
          valMesurePrinc1 = valeur_air_entier >> 8 & 0xFF;
          valMesurePrinc2 = valeur_air_entier & 0xFF;
          
          Serial.print(idMat < 16 ? "0" : "");
          Serial.print(idMat, HEX);
          Serial.print(idUnit1 < 16 ? "0" : "");
          Serial.print(idUnit1, HEX);
          Serial.print(valMesurePrinc1 < 16 ? "0" : "");
          Serial.print(valMesurePrinc1, HEX);
          Serial.print(valMesurePrinc2 < 16 ? "0" : "");
          Serial.print(valMesurePrinc2, HEX);
          Serial.println();
          break;
          
        case 4: // ENVOI DES DONNEES DE TEMPERATURE EAU
        temperature_eau();
          idMat = 4;
          idUnit1 = 4;
          valeur_eau_entier = valeur_eau * 10;
          valMesureSecond1 = valeur_eau_entier >> 8 & 0xFF;
          valMesureSecond2 = valeur_eau_entier & 0xFF;
          
          Serial.print(idMat < 16 ? "0" : "");
          Serial.print(idMat, HEX);
          Serial.print(idUnit1 < 16 ? "0" : "");
          Serial.print(idUnit1, HEX);
          Serial.print(valMesureSecond1 < 16 ? "0" : "");
          Serial.print(valMesureSecond1, HEX);
          Serial.print(valMesureSecond2 < 16 ? "0" : "");
          Serial.print(valMesureSecond2, HEX);
          Serial.println();
          break;
          
        default: // ERREUR : ID MATERIEL INCONNU
          Serial.println("ERROR");
          break;
      }
      receivedBytes[0] = '0';
      receivedBytes[1] = '0';
      receivedBytes[2] = '0';
      receivedBytes[3] = '0';
      receivedIndex = 0;
      
    }
  }
}

