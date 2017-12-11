package com.demo.camera;

public class PhotoCamera implements WriteListener {
    private boolean cameraOn;
    private ImageSensor sensor;
    private Card card;
    private boolean writeCompleted = true;

    public PhotoCamera(ImageSensor sensor, Card card) {
        this.cameraOn = false;
        this.sensor = sensor;
        this.card = card;
    }


    public void turnOn() {
        cameraOn = true;
        sensor.turnOn();
        writeCompleted();

    }

    public void turnOff() {
        cameraOn = false;
        while (writeCompleted) {
            sensor.turnOff();
            break;
        }

    }

    public void pressButton() {
        if (cameraOn) {
            writeNotCompleted();
            card.write(sensor.read());
        }
    }

    @Override
    public void writeCompleted() {
        writeCompleted = true;
    }

    public void writeNotCompleted(){
        writeCompleted = false;
    }
}

