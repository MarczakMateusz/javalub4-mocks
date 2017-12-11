package com.demo.camera;

public class PhotoCamera implements WriteListener {
    private boolean cameraOn;
    private ImageSensor sensor;
    private Card card;
    private boolean writeCompleted = false;

    public PhotoCamera(ImageSensor sensor, Card card) {
        this.cameraOn = false;
        this.sensor = sensor;
        this.card = card;
    }

    public PhotoCamera(ImageSensor sensor) {
        this.cameraOn = false;
        this.sensor = sensor;
    }

    public void turnOn() {
        cameraOn = true;
        sensor.turnOn();

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
            card.write(sensor.read());
        }
    }

    @Override
    public void writeCompleted() {
        writeCompleted = true;
    }
}

