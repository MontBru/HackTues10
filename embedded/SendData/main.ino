
/*  Getting_BPM_to_Monitor prints the BPM to the Serial Monitor, using the least lines of code and PulseSensor Library.
 *  Tutorial Webpage: https://pulsesensor.com/pages/getting-advanced
 *
--------Use This Sketch To------------------------------------------
1) Displays user's live and changing BPM, Beats Per Minute, in Arduino's native Serial Monitor.
2) Print: "♥  A HeartBeat Happened !" when a beat is detected, live.
2) Learn about using a PulseSensor Library "Object".
4) Blinks the builtin LED with user's Heartbeat.
--------------------------------------------------------------------*/
#include <ArduinoJson.h>
#include <PulseSensorPlayground.h>     // Includes the PulseSensorPlayground Library.   

//  Variables
const int PulseWire = 0;       // PulseSensor PURPLE WIRE connected to ANALOG PIN 0
const int LED = LED_BUILTIN;          // The on-board Arduino LED, close to PIN 13.
int Threshold = 60;           // Determine which Signal to "count as a beat" and which to ignore.
                               // Use the "Gettting Started Project" to fine-tune Threshold Value beyond default setting.
                               // Otherwise leave the default "550" value. 

void setup() {   
  delay(1000);
  Serial.begin(115200);          // For Serial Monitor
  Serial.println("hello");
  // Configure the PulseSensor object, by assigning our variables to it. 
}

int beats = 0;
bool prev_state = false;
int myBPM = 0;
int prev_time = 0;
int Signal;

void loop() {

    Signal = analogRead(PulseWire);
    if(Signal > Threshold && prev_state == false ){
      digitalWrite(LED,HIGH);
      prev_state = true;
      beats++;
    } else {
      digitalWrite(LED,LOW);
      prev_state = false;
    }


    delay(20);
    int cur = millis();

    if(cur - prev_time >= 5000)
    {  
      prev_time = cur;
      StaticJsonDocument<200> jsonDocument; // Adjust the size as needed
      jsonDocument["BPM"] = 60 / (cur/1000) * beats;
      String jsonString;
      serializeJson(jsonDocument, jsonString);
      Serial.println("Encoded JSON:");
      Serial.println(jsonString);
    }

    delay(20);                    // considered best practice in a simple sketch.

}

  
