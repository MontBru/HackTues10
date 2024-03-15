#include <ArduinoBLE.h>
#include <ArduinoJson.h>


void setup() {
  pinMode(LED_BUILTIN, OUTPUT);
  Serial.begin(9600);
  //while (!Serial);
  BLE.begin();
  
  Serial1.begin(9600);  

  Serial.println("Bluetooth® Low Energy Central - BPM reading");
  BLE.scanForUuid("19b10000-e8f2-537e-4f6c-d104768a1214");
}

void loop() {
  BLEDevice peripheral = BLE.available();

  if (peripheral) {
    Serial.print("Found ");
    Serial.print(peripheral.address());
    Serial.print(" '");
    Serial.print(peripheral.localName());
    Serial.print("' ");
    Serial.print(peripheral.advertisedServiceUuid());
    Serial.println();
    Serial.print(peripheral.characteristicCount());
    Serial.println();


    // Stop scanning
    BLE.stopScan();

    readBPM(peripheral);

    // Start scanning again
    BLE.scanForUuid("19B10000-E8F2-537E-4F6C-D104768A1214");
  }
}

void signa()
{
  digitalWrite(LED_BUILTIN,LOW);
  delay(200);
  digitalWrite(LED_BUILTIN,HIGH);
  delay(200);
  digitalWrite(LED_BUILTIN,LOW);
  delay(200);
  digitalWrite(LED_BUILTIN,HIGH);
  delay(200);
  digitalWrite(LED_BUILTIN,LOW);
  delay(200);
  digitalWrite(LED_BUILTIN,HIGH);
  delay(200);
}

void readBPM(BLEDevice peripheral) {
  Serial.println("Connecting ...");

  if (!peripheral.connect()) {
    Serial.println("Failed to connect!");
    return;
  }

  Serial.println("Connected");

  Serial.println("Discovering attributes ...");
  if (!peripheral.discoverAttributes()) {
    Serial.println("Attribute discovery failed!");
    peripheral.disconnect();
    return;
  }

  BLECharacteristic bpmCharacteristic = peripheral.characteristic("19b10001-e8f2-537e-4f6c-d104768a1214");

  if (!bpmCharacteristic) {
    Serial.println("Peripheral does not have BPM characteristic!");
    peripheral.disconnect();
    return;
  }

  Serial.println("BPM characteristic found!");

    while (peripheral.connected()) {
      byte val = 0;
      if (bpmCharacteristic.readValue(val)) { // Read the value and check if successful
        Serial.println(val); // Print one of the bytes as an example
        String result="";
        
        result+="[";
        
        result+="{\"id\":\"";
        result+=peripheral.address();
        result+="\",\"value\":";
        result+=val;
        result+="}";

        result+=",";
        result+="{\"id\":\"";
        result+=peripheral.address();
        result+="\",\"value\":";
        result+=val;
        result+="}";

        
        result+="]";
        Serial.println("Encoded JSON:");
        Serial.println(result);
        Serial1.println(result);
        signa();
      } else {
        Serial.println("Failed to read BPM value!");
      }
      
      delay(5000); // Read every 5 seconds
  }

  Serial.println("Peripheral disconnected");
}