const byte interruptPin = 2;
const int interval = 500;
int nbImpulsion;
volatile unsigned long tiptime = millis();

void setup() {
  nbImpulsion = 0;
  
  Serial.begin(9600);

  pinMode(interruptPin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(interruptPin),count , FALLING);
}

void loop() {
  
}

void count() {
  unsigned long curtime = millis();
  
  if ((curtime - tiptime) < interval) {
    return;
  }
  
  nbImpulsion = nbImpulsion + 1;
}

void mesurer() {
  Serial.println(nbImpulsion);
  nbImpulsion = 0;
}

