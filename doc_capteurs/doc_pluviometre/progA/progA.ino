const byte interruptPin = 2;
const int interval = 500;
volatile unsigned long tiptime = millis();

void setup() {
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
  
  Serial.println("tip");
}
