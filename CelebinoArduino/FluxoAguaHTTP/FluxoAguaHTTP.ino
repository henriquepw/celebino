opa

#include <Ethernet.h>
#include <SPI.h>

#define fluxo_pin A0 // water Flux analogic pin connects to A0

float flow ;         // Variável para armazenar o valor em L/min
float minute = 0;    // pulses per minute
int pulses = 0;      // pulses per Second
int s = 0;           // Second counter
int m = 0;           // Minute counter

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192, 168, 1, 177);
IPAddress server{192,168,0,105};

EthernetClient client;

void setup(){
  Serial.begin(9600);
  pinMode(fluxo_pin, INPUT);
  attachInterrupt(0, incpulse, RISING); // Configura o pino A0(Interrupção 0) para trabalhar como interrupção

  Serial.println("\n\nBegin");

  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0)
  {
    Serial.println("Failed to configure Ethernet using DHCP");
    Ethernet.begin(mac, ip);
  }
  Serial.println("Connecting... \n\n");
}

void loop(){
  sensor();
}

void post(String json){
  if (client.connect(server, 8080)){
    Serial.print("Connected - ");
    Serial.println(json);

    client.println("POST /CelebinoService/litersminute/ HTTP/1.1");
    client.println("Host: 192.168.0.105");
    client.println("User-Agent: Arduino");
    client.println("Content-Type: application/json");
    client.println("Connection: Close");
    client.print("Content-Length: ");
    client.println(json.length());
    client.println();
    client.println(json);
    client.stop();

    Serial.println("Client has closed");
  } else {
    Serial.println("Connection failed");
  }
}

void sensor(){
  pulses = 0;
  sei();       //Habilita interrupção
  delay (5000);
  cli();       //Desabilita interrupção

  flow = pulses/5.5; //Converte para L/min
  minute += pulses;
  s+= 5;
  Serial.println(String(flow) + " L/min - " + String(s) + "s");

  if(s == 60){
    flow = minute/5.5;
    m++;

    String json = "{ \"lmin\": " + String(flow) + ", \"airconditioning\": { \"id\": 1 } }";
    post(json);
    minute = 0;
    s = 0;

    Serial.println("\n\n Begin \n\n"); //Imprime Inicio indicando que a contagem iniciou
  }
}

void incpulse(){
  pulses++; //Incrementa a variável de contagem dos pulsos
}
