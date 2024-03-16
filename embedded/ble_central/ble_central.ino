//последна версия на [{},{}]
#include <ArduinoBLE.h>
#include <ArduinoJson.h>
#include <Vector.h>
#define ELEMENT_COUNT_MAX 3

int beats_per_min[ELEMENT_COUNT_MAX];
String mac_adderess[ELEMENT_COUNT_MAX];
int last = 0;

void setup() {
  delay(1000);
  pinMode(LED_BUILTIN, OUTPUT);
  Serial.begin(9600);
  
  if (!BLE.begin()) {
    Serial.println("starting Bluetooth® Low Energy module failed!");

    while (1);
  }
  BLE.scanForUuid("19b10000-e8f2-537e-4f6c-d104768a1214");//""
  Serial1.begin(9600);  
  Serial.println("Bluetooth® Low Energy Central - BPM reading");
}

String message="";
int prev_millis = 0;

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
      BLE.stopScan();
      int va = get_value(peripheral);
      if(last<ELEMENT_COUNT_MAX)
      {
        beats_per_min[last] = va;
        mac_adderess[last++] = peripheral.address();
      }
      peripheral.disconnect();
      //BLE.scanForUuid("19b10000-e8f2-537e-4f6c-d104768a1214");
      
  }

  int current_millis = millis();
  if(current_millis - prev_millis >= 10 * 1000)
  {
    prev_millis = current_millis;
    message += "[";
    
    Serial.print("size = ");
    Serial.println(last);
    for(int i = 0 ; i < last;i++)
    {
      String node = zip_json(i);
      
      Serial.print("node = ");
      Serial.println(node);
      if(node=="")
      {
        Serial.println("Error taking!");
        continue;
      }
      message+= node;
      if( i + 1 != last)
        message+=",";
    }
    message+="]";
    Serial.println(message);
    Serial1.println(message);
    signa();
    String receivedString;
    while(Serial1.available())
    {
      char receivedChar = Serial1.read(); // Read a single c
      receivedString+=receivedChar;
      if (receivedChar == '\n')
      {
        Serial.println(receivedString);
        break;
      }
    }
    last = 0;
    message="";
    Serial.println("-------------------------");
    delay(500);
    BLE.scanForUuid("19b10000-e8f2-537e-4f6c-d104768a1214");
  }
}
int get_value(BLEDevice peripheral)
{
  Serial.println("Connecting ...");
  // Try to connect
  if (!peripheral.connect()) {
    Serial.println("Failed to connect!");
    return 0;
  }

  Serial.println("Connected");
  Serial.println("Discovering attributes ...");
  if (!peripheral.discoverAttributes()) {
    Serial.println("Attribute discovery failed!");
    peripheral.disconnect();
    return 0;
  }

  BLECharacteristic bpmCharacteristic = peripheral.characteristic("19b10001-e8f2-537e-4f6c-d104768a1214");

  if (!bpmCharacteristic) {
    Serial.println("Peripheral does not have BPM characteristic!");
    peripheral.disconnect();
    return 0;
  }

  Serial.println("BPM characteristic found!");

  if (peripheral.connected()) {
    byte val = 0;
    if (bpmCharacteristic.readValue(val))
      return val;
  }
}

void signa()
{
  digitalWrite(LED_BUILTIN,LOW);
  delay(150);
  digitalWrite(LED_BUILTIN,HIGH);
  delay(150);
  digitalWrite(LED_BUILTIN,LOW);
  delay(150);
  digitalWrite(LED_BUILTIN,HIGH);
  delay(150);
  digitalWrite(LED_BUILTIN,LOW);
  delay(150);
  digitalWrite(LED_BUILTIN,HIGH);
  delay(150);
}

String zip_json(int i) {
  Serial.println(i);
  String result="";
  result+="{\"id\":\"";
  result+="100";
  result+="\",\"value\":";
  result+=beats_per_min[i];
  result+="}";
  Serial.println("Encoded JSON:");
  Serial.println(result);
  return result;
}