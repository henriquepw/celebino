
#include <SPI.h>
#include <Ethernet.h>
#include <dht.h>

#define umid_pin A0 //Humidity analogic pin connects to A0
#define dht_pin A1 //DHT data pin connects to A1
#define lum_pin A2 //Luminosity analogic pin connects to A2

dht DHT; //Starting the DHT sensor
int umid_value; //Var of humidity
int lum_value; //Var of luminosity


byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

IPAddress server{192,168,1,9};

IPAddress ip(192, 168, 1, 177);

EthernetClient client;


void setup() {
  Serial.begin(9600);
  pinMode(umid_pin, INPUT);
  pinMode(dht_pin, INPUT);
  pinMode(lum_pin, INPUT);
  while (!Serial) {
    ;
  }

  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    Ethernet.begin(mac, ip);
  }
  delay(1000);
  Serial.println("connecting...");

  if (client.connect(server, 8080)) {
    Serial.println("connected");
    String PostData = sensor();
    Serial.println(PostData);
    Serial.println("OKOK");
    client.println("POST /CelebinoService/gardenstatus/ HTTP/1.1");
    client.println("Host: 192.168.1.9");
    client.println("User-Agent: Arduino");
    client.println("Content-Type: application/json");
    client.println("Connection: Close");
    client.print("Content-Length: ");
    client.println(PostData.length());
    client.println();
    client.println(PostData);
    client.println();
    client.stop();
    Serial.println("Client has closed");
  } else {
    Serial.println("connection failed");
  }
}

void loop() {
  if (client.available()) {
    char c = client.read();
    Serial.print(c);
  }

  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();

    while (true);
  }
}

String sensor(){
  umid_value = analogRead(umid_pin); //Reading the analogic humidity value
  DHT.read11(dht_pin); //Reading the analogic temperature and air humidity values
  lum_value = analogRead(lum_pin); //Reading the analogic luminosity value

  umid_value = map(umid_value,0,1023,100,0);
  String json = "{\"garden\":{\"id\": 1}, \"soilHumidity\":" + String(umid_value) + ", \"airHumidity\":"+String(DHT.humidity)+", \"airTemperature\":"+String(DHT.temperature)+", \"sunLight\":"+String(lum_value)+"}";
  return json;
}
