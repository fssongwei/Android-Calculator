## Introduction

This is the course project of CS 399 Moblie Application Development. The goal of this project is to develop a calculator android application that provides basic calculation functions like add, subtract, multiply, divide, square root, and percentage. The app has a simple user interface and sounds effect when users click on the button.

When first we start to design this app, we try to use the GridLayout to implement the UI of this application. But the problem is the GridLayout in Android is kind of a new thing, which only supports the larger size of the screens. While we try to implement this in a smaller size screen, all of the components went outside of the screen and there is no solution on the internet to solve this problem. Finally, after some research, we decide to use the Tablelayout, which is more flexible to different kinds of devices and reduces the complication of the UI code. It is also flexible in both vertical and landscape views. The whole color design is inspired by the calculator design of the iOS original calculator application. 

For the division function, it requires the second parameter not to be 0, otherwise, it will throw an error and play the error sound. Another issue is to keep the calculation continuously while we keep pressing the operator button after we got a result. When we first calculate a result, we will store it into a variable and use it as the first parameter of the next calculation.

Besides, due to the different sizes of the screens of the devices, we had to limit the digits of the number less than 10. If the digits of the calculation result are greater than 10, we will use the scientific notation to represent the result.



[APK Download](https://storage.googleapis.com/www.david916.com/projects/SuperCalculator.apk)  
[Source Code](https://github.com/fssongwei/Calculator)



## Updates

##### 2017.10.10 Version 1.0

1. Designed and implemented the user interface of the calculator app
2. Developed the calculator function, such as addition, substraction, multiplication and division.



## Demo

<img src="https://i.loli.net/2020/07/20/gXYnOKSz3DjxBpl.jpg" alt="calculator-Main.jpg" style="zoom: 25%;" />



<img src="https://i.loli.net/2020/07/20/vpzwisVSjcl4gr1.jpg" alt="calculator-Landscape.jpg" style="zoom:25%;" />
