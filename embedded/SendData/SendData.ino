#include <SPI.h>
#include <WiFiNINA.h>
#include <ArduinoJson.h>
#include <PulseSensorPlayground.h>     // Includes the PulseSensorPlayground Library.   

//  Variables
const int PulseWire = 0;       // PulseSensor PURPLE WIRE connected to ANALOG PIN 0
const int LED = LED_BUILTIN;          // The on-board Arduino LED, close to PIN 13.
int Threshold = 60;           // Determine which Signal to "count as a beat" and which to ignore.

char ssid[] = "Stefan";        // your network SSID (name)
char pass[] = "qwertyuiop";    // your network password (use for WPA, or use as key for WEP)
int keyIndex = 0;            // your network key index number (needed only for WEP)

int status = WL_IDLE_STATUS;
char server[] = "www.google.com";
WiFiClient client;

void setup() {   
  delay(1000);
  Serial.begin(115200);          // For Serial Monitor
  Serial.println("hello");

  while (!Serial);

  // check for the WiFi module:
  if (WiFi.status() == WL_NO_MODULE) {
    Serial.println("Communication with WiFi module failed!");
    while (true);
  }

  String fv = WiFi.firmwareVersion();
  if (fv < WIFI_FIRMWARE_LATEST_VERSION) {
    Serial.println("Please upgrade the firmware");
  }
  
  while (status != WL_CONNECTED) {
    Serial.print("Attempting to connect to SSID: ");
    Serial.println(ssid);
    status = WiFi.begin(ssid);
    delay(10000);
  }
  Serial.println("Connected to WiFi");
  printWifiStatus();

  Serial.println("\nStarting connection to server...");
}

bool prev_state = false;
int beats = 0, prev_time = 0, Signal;

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
    jsonDocument["BPM"] = 60 * beats / (cur/1000) ;
    String jsonString;
    serializeJson(jsonDocument, jsonString);
    Serial.println("Encoded JSON:");
    Serial.println(jsonString);
    Serial.println(beats);
    if (client.connect(server, 80)) {
      Serial.println("connected to server");
      // Make a HTTP request:
      client.println("POST /your-endpoint HTTP/1.1"); // Replace with your actual endpoint
      client.println("Host: www.google.com");
      client.println("Content-Type: application/json"); // Specify JSON content type
      client.print("Content-Length: ");
      client.println(jsonString.length()); // Set the content length
      client.println("Connection: close");
      client.println();

      // Send the JSON data as the request body
      client.println(jsonString);
    }
  }
  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting from server.");
    client.stop();
  }
  delay(20);                    // considered best practice in a simple sketch.

}

void printWifiStatus() {
  // print the SSID of the network you're attached to:
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());

  // print your board's IP address:
  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);

  // print the received signal strength:
  long rssi = WiFi.RSSI();
  Serial.print("signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
}
  
