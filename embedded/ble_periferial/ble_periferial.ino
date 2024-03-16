#include <ArduinoBLE.h>
#include <PulseSensorPlayground.h>     // Includes the PulseSensorPlayground Library.   

BLEService bpmService("19B10000-E8F2-537E-4F6C-D104768A1214"); // Bluetooth® Low Energy BPM Service
BLEIntCharacteristic bpmCharacteristic("19B10001-E8F2-537E-4F6C-D104768A1214", BLERead);// Bluetooth® Low Energy BPM Characteristic - custom 128-bit UUID, read and writable by central

const int ledPin = LED_BUILTIN; // pin to use for the LED
const int PulseWire = 0;       // PulseSensor PURPLE WIRE connected to ANALOG PIN 0
const int LED = LED_BUILTIN;          // The on-board Arduino LED, close to PIN 13.
int Threshold = 67;          
//PulseSensorPlayground pulseSensor;

void setup() {
  Serial.begin(115200);
  //while (!Serial);
    // begin initialization
  if (!BLE.begin()) {
    Serial.println("starting Bluetooth® Low Energy module failed!");

    while (1);
  }


  // set LED pin to output mode
  pinMode(ledPin, OUTPUT);

  // set advertised local name and service UUID:
  BLE.setLocalName("BPM");
  BLE.setAdvertisedService(bpmService);
  bpmService.addCharacteristic(bpmCharacteristic);
  BLE.addService(bpmService);
  bpmCharacteristic.writeValue(0);
  BLE.advertise();
  Serial.println("BLE BPM Peripheral");
  // pulseSensor.analogInput(PulseWire);   
  // pulseSensor.blinkOnPulse(LED);       //auto-magically blink Arduino's LED with heartbeat.
  // pulseSensor.setThreshold(Threshold); 
  // if (pulseSensor.begin()) {
  //   Serial.println("We created a pulseSensor Object !");  //This prints one time at Arduino power-up,  or on Arduino reset.  
  // }
}
int beats = 0, Signal = 0;
void loop() {

  Signal = analogRead(PulseWire);  // Read the PulseSensor's value.


   if(Signal > Threshold){
      beats++;
      digitalWrite(LED,HIGH);
      delay(150);                          // If the signal is above "550", then "turn-on" Arduino's on-Board LED.
   } else {
     digitalWrite(LED,LOW);                //  Else, the sigal must be below "550", so "turn-off" this LED.
   }
  // listen for Bluetooth® Low Energy peripherals to connect:
  BLEDevice central = BLE.central();

  // if a central is connected to peripheral:
  if (central) {
    int time = millis()/1000;
    int BPS = beats*60/time;
    
    Serial.print("Beats = ");
    Serial.print(beats);
    Serial.print("   time = ");
    Serial.print(time);
    Serial.print("   res = ");
    Serial.println(BPS);
    bpmCharacteristic.writeValue(BPS);
    // when the central disconnects, print it out:
    Serial.print(F("Disconnected from central: "));
    Serial.println(central.address());
  }
  delay(100);
}