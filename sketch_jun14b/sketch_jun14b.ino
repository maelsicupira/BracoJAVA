#include <AccelStepper.h>
#include<Servo.h>
AccelStepper motores[3] = {AccelStepper(1, 4, 7), AccelStepper(1, 5, 9)};
Servo garra;
int velocidade_motor = 100; 

int IN1 = 2;
int IN2 = 3;
int IN3 = 6;
int IN4 = 8;

int steper = 10;
char state = 0;        
boolean stringComplete = false;   
int pos[2] = {0,0};
int sentido[2] = {-1,-1};
int atualPos[2] = {0,0};

void setMotor(int i){
  motores[i].moveTo(pos[i]);
  motores[i].setSpeed(velocidade_motor);
}

void setup() {
  Serial.begin(9600);
  for(int i = 0; i<2; i++){
    motores[i].setMaxSpeed(400);
  }
  garra.attach(11);
  garra.write(150);
}

void sentidoHorario(int i){
  pos[i]+= steper;
  sentido[i] = 0;
 // motores[i].setPinsInverted(false, false, false);
}

void sentidoAntiHorario(int i){
  pos[i]-= steper;
  sentido[i] = 1;
  //motores[i].setPinsInverted(true, false, false);
}


void loop() {
  if(Serial.available()){
     state = (char)Serial.read();
     stringComplete = (state-'0' >0) && (state-'0')<=8;
  }
  if(stringComplete){
     switch(state){
         case '1':{
             Serial.println("OK -> MOTOR 1 SENTIDO HORARIO");
             sentidoHorario(0);
             break;
         }
         case '2':{
             Serial.println("OK -> MOTOR 1 SENTIDO ANTIHORARIO");
             sentidoAntiHorario(0);
             break;
         }
         case '3':{
            Serial.println("OK -> MOTOR 2 SENTIDO HORARIO");
            sentidoHorario(1);
            break;
         }
         case '4':{
            Serial.println("OK -> MOTOR 2 SENTIDO ANTIHORARIO");
            sentidoAntiHorario(1);
            break;
         }
         case '5':{
            Serial.println("OK -> MOTOR 3 SENTIDO HORARIO");
            
            break;
         }
         case '6':{
            Serial.println("OK -> MOTOR 3 SENTIDO ANTIHORARIO");
            
            break;
         }  
         case '7':{
            Serial.println("OK -> GARRA FECHAR");
            garra.write(150);
            break;
         }
         case '8':{
            Serial.println("OK -> GARRA ABRIR");
            garra.write(0);
            break;
         }
         default:{
            Serial.println(state); 
            break;
         }
     }
    state = 0;
    stringComplete = false;
  }
  
  for(int i = 0; i<2; i++){
      int cur = atualPos[i];
      if((sentido[i] == 0 && cur <= pos[i]) || (sentido[i] == 1 && cur >= pos[i])){
        setMotor(i);
        switch(sentido[i]){
          case 0:{
            atualPos[i]++;
            break;
          }
          case 1:{
            atualPos[i]--;
            break;
          }
        }
       
      }

      if(cur == pos[i]){
        //Serial.println("CHEGOU");  
      }
      motores[i].runSpeedToPosition();
    } 
  
}
   
