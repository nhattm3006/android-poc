# Exported Activity

- Install 2 PoC app on Android device/emulator:
  - vuln.app: vulnerable app
  - exploit.app: exploiting app
- Exploit:
  - Malware attack: open vuln app, then open exploit app and press button exploit
  - Physical attack: use adb command  
  ```./adb shell am start "com.poc.exported_activity/com.poc.exported_activity.Activity2"```
