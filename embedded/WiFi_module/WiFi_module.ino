/**
   PostHTTPClient.ino

    Created on: 21.11.2016

*/

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
//#include <SoftwareSerial.h>
//SoftwareSerial uartConnection(4, 3); // RX, TX


/* this can be run with an emulated server on host:
        cd esp8266-core-root-dir
        cd tests/host
        make ../../libraries/ESP8266WebServer/examples/PostServer/PostServer
        bin/PostServer/PostServer
   then put your PC's IP address in SERVER_IP below, port 9080 (instead of default 80):
*/
//#define SERVER_IP "10.0.1.7:9080" // PC address with emulation on host
#define SERVER_IP "192.168.43.61:5000"

#ifndef STASSID
#define STASSID "Galaxy A51BF86"
#define STAPSK "ndff6393"
#endif

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

void setup() {
  delay(1000);
  pinMode(LED_BUILTIN, OUTPUT);
  Serial.begin(9600);
  //uartConnection.begin(9600);

  WiFi.begin(STASSID, STAPSK);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
  }
  signa();
}



void loop() {
  // wait for WiFi connection
  if ((WiFi.status() == WL_CONNECTED)) {
    
    WiFiClient client;
    HTTPClient http;

    // configure traged server and url
    http.begin(client, "http://" SERVER_IP "/test");  // HTTP
    http.addHeader("Content-Type", "application/json");
    int httpCode;
    while(Serial.available()) {
        signa();
        char receivedChar = Serial.read(); // Read a single character
        static String receivedString; // Declare a static String to accumulate characters

        // Append the received character to the string
        receivedString += receivedChar;

        // Check if the received character is a newline (end of message)
        if (receivedChar == '\n') {
            // Process the complete string
            httpCode = http.POST(receivedString);
        }
    }

    // start connection and send HTTP header and body

    // httpCode will be negative on error
    if (httpCode > 0) {
      // HTTP header has been send and Server response header has been handled
      Serial.printf("[HTTP] POST... code: %d\n", httpCode);

      // file found at server
      if (httpCode == HTTP_CODE_OK) {
        const String& payload = http.getString();
        Serial.println("received payload:\n<<");
        Serial.println(payload);
        Serial.println(">>");
      }
    } else {
      Serial.printf("[HTTP] POST... failed, error: %s\n", http.errorToString(httpCode).c_str());
    }

    http.end();
  }

  delay(100);
}