## MarilynPic



Tthe marilynPic project is an image editor project made in java.
In this project to put a picture of your chose and it will create an Andy Warhol's Pop Art style of the same image.

![Alt text](https://github.com/gavriel200/MarilynPic/blob/master/examples/MarilynBWAFTER.jpg)

## get started

You will need to put your image into the ImageBefore folder. you can have only one picture at a time.
also make sure that you put a .jpg image.

Then you need to configure the marilyn.conf file.
in that file you have 4 configureations.

* TopLeft
* TopRight
* DownLeft
* DownRight

And each configuration has three values seperated by comma.
Red, Green and Blue.

you can configure each color in three wayes:

* you can just put an X and the color value wont change
* you cat put in a number between 0 - 255 and the color will be set to that value
* and you can put in a number between 0%-100%: in that case if the value you put more then 50% then the value will change acording to the percent(each value will change diffrently) if you put less then 50% it will go down. And if you put 50% the value wont change.

after you've configured the configuration file you need to run the Main.java file and it will do the rest.
