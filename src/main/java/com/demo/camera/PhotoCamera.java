package com.demo.camera;

public class PhotoCamera implements WriteListener {
    private boolean cameraState;
    private ImageSensor sensor;
    private Card card;
    private Boolean writeCompleted;

    public PhotoCamera(ImageSensor sensor,Card card) {
        this.cameraState = false;
        this.sensor = sensor;
        this.card = card;
    }

    public PhotoCamera(ImageSensor sensor) {
        this.cameraState = false;
        this.sensor = sensor;
    }

    public PhotoCamera( Card card) {
        this.cameraState = false;
        this.card = card;
    }

    public boolean isCameraState() {
        return cameraState;
    }


    public void turnOn() {
        cameraState = true;
        sensor.turnOn();

    }

    public void turnOff() {
        cameraState = true;
        sensor.turnOff();
    }

    public void pressButton() {
        if(cameraState){


            card.write(sensor.read());
        }
    }

    @Override
    public void writeCompleted() {


    }
}

