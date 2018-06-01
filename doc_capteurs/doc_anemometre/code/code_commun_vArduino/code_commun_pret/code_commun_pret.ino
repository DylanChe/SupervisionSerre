#include <math.h>  
#define AnemometrePin (18) //Pin de l'anemometre

volatile unsigned long Rotations; //  nombre de rotations effectuées par l'anémometre
volatile unsigned long ContactBounceTime; // Timer to avoid contact bounce in interrupt routine 

float VitesseVent; // speed miles per hour 
int val = 0;
int ValeurCapt;// raw analog value from wind vane 
int Direction;// translated 0 - 360 direction 
int DegDirection;// converted value with offset applied 
int ValDirection; 
const byte PluviometrePin = 2;
const int intervale = 500;
volatile unsigned long tiptime = millis();


#define Offset 0; 

void setup() { 
  Serial.begin(9600); 

  pinMode(AnemometrePin, INPUT_PULLUP); 
  attachInterrupt(digitalPinToInterrupt(AnemometrePin), GirRotation, FALLING); 
  Serial.println("Davis Wind Speed Test"); 
  Serial.print("Km/h");
  Serial.print("\tIndication");
  Serial.print("\t\t\tDegre\t\tDirection");
  Serial.println("\t\t\tPluie");
  ValDirection = 1; 
  pinMode(PluviometrePin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(PluviometrePin),comptage , FALLING);
  
   
  
} 
void comptage() {

  unsigned long curtime = millis();
  float pluie = 0;
  
  if((curtime - tiptime) < intervale) {
    
  }
  
  Serial.println("\t\t\t\t\t\t\t\t\t\t\tgoutte");
 
  
}

// This is the function that the interrupt calls to increment the rotation comptage 
void GirRotation () { 

  if ((millis() - ContactBounceTime) > 15 ) { // debounce the switch contact. 
  Rotations++; 
  ContactBounceTime = millis(); 
  } 
  //Serial.println("wind");
  

}
void getVitesse(int VitesseVent) { 
  if(VitesseVent < 1) {
    Serial.print("\tVent Calme");
    Serial.print("\t\t");
  }
  else if (VitesseVent < 5){ 
    Serial.print("\tTres legrere brise");
    Serial.print("\t");
  }
  else if (VitesseVent < 11){ 
    Serial.print("\tlegere brise");
    Serial.print("\t\t");
  } 
  else if (VitesseVent < 19) {
    Serial.print("\tpetite brise");
    Serial.print("\t\t"); 
  }
  else if (VitesseVent < 28) {
    Serial.print("\tjolie brise");
    Serial.print("\t\t");
  } 
  else if (VitesseVent < 38) {
    Serial.print("\tbonne brise");
    Serial.print("\t\t"); 
  }
  else if (VitesseVent < 49) {
    Serial.print("\tvent frais");
    Serial.print("\t\t"); 
  }  
  else if (VitesseVent < 61) {
    Serial.print("\tgrand frais");
    Serial.print("\t\t");
  }
  else if (VitesseVent < 74) {
    Serial.print("\tcoup de vent");
    Serial.print("\t\t");
  }
  else if (VitesseVent < 88) {
    Serial.print("\tfort coup de vent");
    Serial.print("\t");
  }
  else if (VitesseVent < 102) {
    Serial.print("\ttempete");
    Serial.print("\t\t");
  }
  else if (VitesseVent < 117) {
    Serial.print("\tviolente tempete");
    Serial.print("\t");
  }
  else 
    Serial.print("\tbombe meteorologique");
    Serial.print("\t"); 
}

// Converts compass direction to heading 
void getHeading(int direction) { 
  if(direction < 22) 
    Serial.println("\t\tNord"); 
  else if (direction < 67) 
    Serial.println("\t\tNord Est"); 
  else if (direction < 112) 
    Serial.println("\t\tEst");  
  else if (direction < 157) 
    Serial.println("\t\tSud Est"); 
  else if (direction < 212) 
    Serial.println("\t\tSud"); 
  else if (direction < 247) 
    Serial.println("\t\tSud Ouest"); 
  else if (direction < 292) 
    Serial.println("\t\tOuest"); 
  else if (direction < 337) 
    Serial.println("\t\tNord Ouest"); 
  else 
    Serial.println("\t\tNord"); 
}
void loop() { 

  Rotations = 0; // Set Rotations comptage to 0 ready for calculations 
  //Serial.print(Rotations);
 
  //Serial.println(val); 
  //val = digitalRead(PotentiometrePin);
  //Serial.println(val);
 

  sei(); // Enables interrupts 
  delay (3000); // Wait 3 seconds to average 
  cli(); // Disable interrupts 

   //convert to mp/h using the formula V=P(2.25/T) 
   //V = P(2.25/3) = P * 0.75 

  VitesseVent = Rotations * 0.75 *1.61; 

  //Serial.print(Rotations); Serial.print("\t\t"); 
  Serial.print(VitesseVent);
  getVitesse(VitesseVent);
  
  ValeurCapt = analogRead(A2); 
  //delay(500);
  Direction = map(ValeurCapt, 0, 1023, 0, 360); 
  DegDirection = Direction + Offset; 

  if(DegDirection > 360) 
    DegDirection = DegDirection - 360; 

  if(DegDirection < 0) 
    DegDirection = DegDirection + 360; 

  
  
   
    //Serial.print(ValeurCapt); Serial.print("\t\t"); 
   Serial.print(DegDirection);Serial.print("°");
   getHeading(DegDirection); 
   ValDirection = DegDirection; 
  
   
  

} 


