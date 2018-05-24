#include <math.h>  
#define AnemometrePin (18) //Pin du pluviometre

volatile unsigned long Rotations; // cup rotation counter used in interrupt routine 
volatile unsigned long ContactBounceTime; // Timer to avoid contact bounce in interrupt routine 

float WindSpeed; // speed miles per hour 
int val = 0;
int VaneValue;// raw analog value from wind vane 
int Direction;// translated 0 - 360 direction 
int CalDirection;// converted value with offset applied 
int LastValue; 
const byte PluviometrePin = 2;
const int interval = 500;
volatile unsigned long tiptime = millis();


#define Offset 0; 

void setup() { 
  Serial.begin(9600); 

  pinMode(AnemometrePin, INPUT_PULLUP); 
  attachInterrupt(digitalPinToInterrupt(AnemometrePin), isr_rotation, FALLING); 
  LastValue = 1; 
  pinMode(PluviometrePin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(PluviometrePin),count , FALLING);
  
   
  
} 
void count() {

  unsigned long curtime = millis();
  float pluie = 0;
  
  if((curtime - tiptime) < interval) {
    
  }
  
  Serial.println("goutte");
 
  
}

// This is the function that the interrupt calls to increment the rotation count 
void isr_rotation () { 

  if ((millis() - ContactBounceTime) > 15 ) { // debounce the switch contact. 
  Rotations++; 
  ContactBounceTime = millis(); 
  } 
  //Serial.println("wind");
  

}
void getVitesse(int WindSpeed) { 
  if(WindSpeed < 1) {
    Serial.println("Vent Calme");
  }
  else if (WindSpeed < 5){ 
    Serial.println("Tres legrere brise");
  }
  else if (WindSpeed < 11){ 
    Serial.println("legere brise");
  } 
  else if (WindSpeed < 19) {
    Serial.println("petite brise");
  }
  else if (WindSpeed < 28) {
    Serial.println("jolie brise");
  } 
  else if (WindSpeed < 38) {
    Serial.println("bonne brise"); 
  }
  else if (WindSpeed < 49) {
    Serial.println("vent frais"); 
  }  
  else if (WindSpeed < 61) {
    Serial.println("grand frais");
  }
  else if (WindSpeed < 74) {
    Serial.println("coup de vent");
  }
  else if (WindSpeed < 88) {
    Serial.println("fort coup de vent");
  }
  else if (WindSpeed < 102) {
    Serial.println("tempete");
  }
  else if (WindSpeed < 117) {
    Serial.println("violente tempete");
  }
  else 
    Serial.println("bombe meteorologique");
}

// Converts compass direction to heading 
void getHeading(int direction) { 
  if(direction < 22) 
    Serial.println("Nord"); 
  else if (direction < 67) 
    Serial.println("Nord Est"); 
  else if (direction < 112) 
    Serial.println("Est");  
  else if (direction < 157) 
    Serial.println("Sud Est"); 
  else if (direction < 212) 
    Serial.println("Sud"); 
  else if (direction < 247) 
    Serial.println("Sud Ouest"); 
  else if (direction < 292) 
    Serial.println("Ouest"); 
  else if (direction < 337) 
    Serial.println("Nord Ouest"); 
  else 
    Serial.println("Nord"); 
}
void loop() { 

  Rotations = 0; // Set Rotations count to 0 ready for calculations 
  //Serial.print(Rotations);
 
  //Serial.println(val); 
  //val = digitalRead(PotentiometrePin);
  //Serial.println(val);
 

  sei(); // Enables interrupts 
  delay (3000); // Wait 3 seconds to average 
  cli(); // Disable interrupts 

   //convert to mp/h using the formula V=P(2.25/T) 
   //V = P(2.25/3) = P * 0.75 

  WindSpeed = Rotations * 0.75 *1.61; 

  //Serial.print(Rotations); Serial.print("\t\t"); 
  Serial.print(WindSpeed);
  getVitesse(WindSpeed);
  
  VaneValue = analogRead(A2); 
  //delay(500);
  Direction = map(VaneValue, 0, 1023, 0, 360); 
  CalDirection = Direction + Offset; 

  if(CalDirection > 360) 
    CalDirection = CalDirection - 360; 

  if(CalDirection < 0) 
    CalDirection = CalDirection + 360; 

  
  
   
    //Serial.print(VaneValue); Serial.print("\t\t"); 
   Serial.print(CalDirection);
  // getHeading(CalDirection); 
   LastValue = CalDirection; 
  
   
  

} 

