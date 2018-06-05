#include <math.h>

#define pluviometrePin (2) // Pin du pluviometre

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

// PLUVIOMETRE
volatile unsigned long tipTime = millis();
const int tipInterval = 500;
int rainAmount = 0;
int tipCalibration = 2;


void setup() {
  // DEFINITION DE LA COMMUNICATION SERIE
  Serial.begin(9600);

  // PARAMETRAGE DES ENTREES/SORTIES
  // --- PLUVIOMETRE
  pinMode(pluviometrePin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(pluviometrePin), pluvio_tip, FALLING);
  
}

void pluvio_tip() {
  if((millis() - tipTime) > tipInterval) {
    rainAmount = rainAmount + tipCalibration;
    tipTime = millis();
  }
}

void loop() {
  
}

void clearReceivedBytes() {
  receivedBytes[0] = '0';
  receivedBytes[1] = '0';
  receivedBytes[2] = '0';
  receivedBytes[3] = '0';
  receivedIndex = 0;
}

void serialEvent() {
  while (Serial.available()) {
    byte inByte = Serial.read();
    receivedBytes[receivedIndex] = inByte;
    receivedIndex += 1;

    if (receivedIndex >= numReceivedBytes) { // LE MESSAGE EST COMPLET
      if (receivedBytes[0] == 73)



      
      switch (receivedBytes[3]) {
        case 1: // ENVOI DES DONNEES DU PLUVIOMETRE
          idMat = 1;
          idUnit1 = 1;
          valMesurePrinc1 = rainAmount >> 8 & 0xFF;
          valMesurePrinc2 = rainAmount & 0xFF;
          rainAmount = 0;
          
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
        default: // ERREUR : ID MATERIEL INCONNU
          Serial.println("ERROR");
          break;
      }
      clearReceivedBytes();
    }
  }
}





