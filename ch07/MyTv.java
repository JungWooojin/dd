package com.green.java.ch07;

public class MyTv extends Speaker {
    int channel;

    void channelUp(){channel++;}
    void channelDown(){channel--;}
}


class Mytv2 {
    int channel;
    void channelUp(){channel++;}
    void channelDown(){channel--;}
    Speaker speaker;

    Mytv2(){
        speaker=new Speaker();
    }
    void volumnUp() {speaker.volumnUp();}
    void volumnDown() {speaker.volumnDown();}
    int getVolumn(){return speaker.volumn;}
}


class Speaker{
    int volumn;
    void volumnUp() {volumn++;}
    void volumnDown() {volumn--;}

}