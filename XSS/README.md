# XSS

- Install PoC app on Android device/emulator
- Exploit:
  - Test case 1: app allow execute alert() function  
    ```javascript:alert(1)```
  - Test case 2: by default, WebView does not implement JavaScript alert dialogs  
    ```javascript:document.write("JavaScript")```
