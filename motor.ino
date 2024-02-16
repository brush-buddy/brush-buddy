#include <AFMotor.h>

AF_DCMotor motor0(1);
AF_DCMotor motor1(2);
AF_DCMotor motor2(3);
AF_DCMotor motor3(4);

unsigned long motorTime[4] = {0, 0, 0, 0};
unsigned long startTime = 0;
unsigned long runTime = 0;

enum Estate {WAIT, RUN, END};
Estate state = WAIT;

void setup() {
  Serial.begin(9600); // 시리얼 통신 시작
  motor0.setSpeed(255);
  motor1.setSpeed(255); // 모터 2 속도 설정
  motor2.setSpeed(255); // 모터 3 속도 설정
  motor3.setSpeed(255); // 모터 4 속도 설정
  startTime = millis();
}

void loop() {
  switch(state)
  {
    case WAIT:
      if (Serial.available()) {
        startTime = millis();
        motorTime[0] = Serial.parseInt();
        motorTime[1] = Serial.parseInt();   
        motorTime[2] = Serial.parseInt();
        motorTime[3] = Serial.parseInt(); 
        Serial.flush(); // 시리얼 버퍼 비우기
        state = RUN;
      }
      break;

    case RUN:
      runTime = millis()-startTime;
      motor0.run (runTime > motorTime[0] ?  RELEASE : BACKWARD);
      motor1.run (runTime > motorTime[1] ?  RELEASE : BACKWARD);
      motor2.run (runTime > motorTime[2] ?  RELEASE : BACKWARD);
      motor3.run (runTime > motorTime[3] ?  RELEASE : BACKWARD);
      if ( (runTime > motorTime[0]) && (runTime > motorTime[1]) && (runTime > motorTime[2]) && (runTime > motorTime[3]) )
        state = END;
      break;
      
    case END:
      Serial.println("Motor operation completed");
      state = WAIT;
      break;

    default:
    // error
      state = WAIT;
      break;
  }
}