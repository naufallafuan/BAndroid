# BAndroid
Android Automation

How to run:
1. run your Appium server by typing **apium** in the terminal
2. By default, the port is 127.0.0.1/4723 but if you have modified it or it somehow got changes, please change the port on ServerSetup.java
3. Check what is your deviceId by typing **adb devices** in the terminal
4. Change the deviceId under **capabilities.json** with your deviceId
5. Change the APK source under **capabilities.json**
6. Run the test by typing **mvn test** in the terminal
