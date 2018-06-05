#include <math.h>

#define anemometrePin (18) // Pin de l'anemometre
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

// ANEMOMETRE
// --- DIRECTION DU VENT
int windDirection;
int calwindDirection;
int vaneValue;
int lastwindDirectionValue;
int Offset = 0; 
int degre = 0;

// --- VITESSE DU VENT
volatile unsigned long lastRotate = millis();
volatile unsigned long contactBounceTime;
float windSpeed;
int vitesse = 0;

// PLUVIOMETRE
volatile unsigned long tipTime = millis();
const int tipInterval = 500;
int rainAmount = 0;
int tipCalibration = 2;


void setup() {
  // DEFINITION DE LA COMMUNICATION SERIE
  Serial.begin(9600);
    // INITIALISATION DES VARIABLES
  lastwindDirectionValue = 1;

  // PARAMETRAGE DES ENTREES/SORTIES
  // --- ANEMOMETRE
  pinMode(anemometrePin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(anemometrePin), anemo_rotation, FALLING);
  // --- PLUVIOMETRE
  pinMode(pluviometrePin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(pluviometrePin), pluvio_tip, FALLING);
  
}
void anemo_rotation() {
  if ((millis() - contactBounceTime) > 15 ) { // debounce the switch contact. 
    windSpeed = (2.25 / ((millis() - lastRotate)*0.001)) * 1.61;
    vitesse = int(windSpeed);
    lastRotate = millis();
    contactBounceTime = millis(); 
  }
}

void anemo_direction() {
  vaneValue = analogRead(A2);
  windDirection = map(vaneValue, 0, 1023, 0, 360); 
  calwindDirection = (windDirection + Offset); 
  degre = int(calwindDirection);

  if(calwindDirection > 360) 
    calwindDirection = calwindDirection - 360; 

  if(calwindDirection < 0) 
    calwindDirection = calwindDirection + 360;
  
  lastwindDirectionValue = degre;
}

void pluvio_tip() {
  if((millis() - tipTime) > tipInterval) {
    rainAmount = rainAmount + tipCalibration;
    Serial.println("TIP");
    tipTime = millis();
  }
}

void loop() {
  if ((millis() - lastRotate) > 5000 ) {
    vitesse = 0;
  }
}

void serialEvent() {
  while (Serial.available()) {
    byte inByte = Serial.read();
    receivedBytes[receivedIndex] = inByte;
    receivedIndex += 1;
    
    if (receivedIndex >= numReceivedBytes) {
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
        case 2: // ENVOI DES DONNES DE L'ANEMOMETRE
          anemo_direction();
          idMat = 2;
          idUnit1 = 2;
          valMesurePrinc1 = lastwindDirectionValue >> 8 & 0xFF;
          valMesurePrinc2 = lastwindDirectionValue & 0xFF;
          idUnit2 = 3;
          valMesureSecond1 = vitesse >> 8 & 0xFF;
          valMesureSecond2 = vitesse & 0xFF;
          
          Serial.print(idMat < 16 ? "0" : "");
          Serial.print(idMat, HEX);
          Serial.print(idUnit1 < 16 ? "0" : "");
          Serial.print(idUnit1, HEX);
          Serial.print(valMesurePrinc1 < 16 ? "0" : "");
          Serial.print(valMesurePrinc1, HEX);
          Serial.print(valMesurePrinc2 < 16 ? "0" : "");
          Serial.print(valMesurePrinc2, HEX);
          Serial.print(idUnit2 < 16 ? "0" : "");
          Serial.print(idUnit2, HEX);
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





