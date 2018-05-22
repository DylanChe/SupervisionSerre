#line 2 "sketch.ino"
#include <ArduinoUnit.h>

#include <currentLoop.h>


test(current_eau_tuyau_min)            
  {
    int current = sensorBoard.readCurrent(CHANNEL3);
    int min_ma = 4;
    assertMoreOrEqual(current,min_ma);
  }
  
test(current_eau_tuyau_max)
  {
    int current = sensorBoard.readCurrent(CHANNEL3);
    int max_ma = 20;
    assertLessOrEqual(current,max_ma);
  }
  
test(probleme_inferieur)
  {
    int current = sensorBoard.readCurrent(CHANNEL3);
    int probleme = 4;
    assertLess(current,probleme);
  }
  
test(probleme_superieur)
  {
    int current = sensorBoard.readCurrent(CHANNEL3);
    int probleme = 20;
    assertMore(current,probleme);
  }
  
void setup()
{
  Serial.begin(9600);
  while(!Serial) {}
}

void loop()
{
  Test::run();
}
