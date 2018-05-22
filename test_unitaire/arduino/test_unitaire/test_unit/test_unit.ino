#line 2 "sketch.ino"
#include <ArduinoUnit.h>

#include <currentLoop.h>


test(current_eau_min)
  {
    int current = sensorBoard.readCurrent(CHANNEL1);
    int min_ma = 4;
    assertMoreOrEqual(current,min_ma);
  }
  
test(current_eau_max)
  {
    int current = sensorBoard.readCurrent(CHANNEL1);
    int max_ma = 20;
    assertLessOrEqual(current,max_ma);
  }
  
test(probleme)
  {
    int current = sensorBoard.readCurrent(CHANNEL1);
    int probleme = 0;
    assertLess(current,probleme);
  }

  
void setup()
{
  Serial.begin(9600);
  while(!Serial) {} // Portability for Leonardo/Micro
}

void loop()
{
  Test::run();
}
