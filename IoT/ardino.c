void setup() {
  pinMode(LED_BUILTIN, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0) {
    char receivedChar = Serial.read();
    
    // 'H' 문자를 받으면 LED 켜기
    if (receivedChar == 'H') {
      digitalWrite(LED_BUILTIN, HIGH);
      Serial.println("LED ON");
    }
    // 'L' 문자를 받으면 LED 끄기
    else if (receivedChar == 'L') {
      digitalWrite(LED_BUILTIN, LOW);
      Serial.println("LED OFF");
    }
  }
}