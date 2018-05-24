#include <math.h>
#define anemometrePin (18) // Pin de l'anemometre
#define pluviometrePin (2) // Pin du pluviometre

// ANEMOMETRE
// --- DIRECTION DU VENT
int windDirection;
int calDirection;
int vaneValue;
int lastDirectionValue;
// --- VITESSE DU VENT
volatile unsigned long contactBounceTime;
volatile unsigned long rotations;
float windSpeed;

// PLUVIOMETRE
volatile unsigned long tipTime = millis();
const int tipInterval = 500;
int rainAmount = 0;
int tipCalibration = 2;


struct trame
{
  byte idMateriel;
  byte idUnite;
  byte valeurMesure1;
  byte valeurMesure2;
};

void setUpTrame(trame *tramToSetUp, int intIdMat, int intIdUnit, int intValMesure) {
  byte byteIdMat = intIdMat;
  byte byteIdUnit = intIdUnit;
  byte byteValMesure1 = intValMesure >> 8 & 0xFF;
  byte byteValMesure2 = intValMesure & 0xFF;

  tramToSetUp->idMateriel = byteIdMat;
  tramToSetUp->idUnite = byteIdUnit;
  tramToSetUp->valeurMesure1 = byteValMesure1;
  tramToSetUp->valeurMesure2 = byteValMesure2;
}

void sendTrame(trame *tramToSend) {
  byte idMat = tramToSend->idMateriel;
  byte idUnit = tramToSend->idUnite;
  byte valMesure1 = tramToSend->valeurMesure1;
  byte valMesure2 = tramToSend->valeurMesure2;
  byte byteTramToSend[4] = {idMat, idUnit, valMesure1, valMesure2};
  Serial.print(idMat, HEX);
  Serial.print(idUnit, HEX);
  Serial.print(valMesure1, HEX);
  Serial.print(valMesure2, HEX);
  Serial.println();
}

void setup() {
  // DEFINITION DE LA COMMUNICATION SERIE
  Serial.begin(9600);

  // INITIALISATION DES VARIABLES
  lastDirectionValue = 1;

  // PARAMETRAGE DES ENTREES/SORTIES
  // --- ANEMOMETRE
  pinMode(anemometrePin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(anemometrePin), anemo_rotation, FALLING);
  // --- PLUVIOMETRE
  pinMode(pluviometrePin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(pluviometrePin), pluvio_tip, FALLING);
}

void anemo_rotation() {
  if ((millis() - contactBounceTime) > 15 ) { 
    rotations++; 
    contactBounceTime = millis(); 
  } 
}

void pluvio_tip() {
  unsigned long curtime = millis();
  if((curtime - tipTime) < tipInterval) {
    rainAmount = rainAmount + tipCalibration;
  }
}

void loop() {
  /*
  trame trameToSend;
  setUpTrame(&trameToSend, 1, 2, 565);
  sendTrame(&trameToSend);
  */
}
