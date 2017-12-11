package com.demo.camera

import spock.lang.Specification

class PhotoCameraSpec extends Specification {


    def "Should power up the sensor when camera is switched on"() {
        given:
        ImageSensor testSensor = Mock(ImageSensor)
        PhotoCamera testCamera = new PhotoCamera(testSensor)

        when:
        testCamera.turnOn()

        then:
        1 * testSensor.turnOn()

    }

    def "Should power down sensor when camera is switched off"(){
        given:
        ImageSensor testSensor = Mock(ImageSensor)
        PhotoCamera testCamera = new PhotoCamera(testSensor)

        when:
        testCamera.writeCompleted()
        testCamera.turnOff()

        then:
        1 * testSensor.turnOff()

    }

    def "Pushing the button when camera is Off should do nothing"(){
        given:
        ImageSensor testSensor = Mock(ImageSensor)
        Card testCard = Mock(Card)
        PhotoCamera testCamera = new PhotoCamera(testSensor,testCard)

        when:
        testCamera.pressButton()

        then:
        0 * testSensor.turnOff()
        0 * testSensor.turnOn()
        0 * testSensor.read()
        0 * testCard.write()
    }
    def "Pressing the burron when camera is on should copy date from sensor to card"(){
        given:
        ImageSensor testSensor = Mock(ImageSensor)
        Card testCard = Mock(Card)
        PhotoCamera testCamera = new PhotoCamera(testSensor,testCard)
        testCamera.turnOn()

        when:
        testCamera.pressButton()

        then:
        1 * testCard.write(testSensor.read())

    }

    def "Turning camera off when card is coping data wont turn off sensor"() {
        given:
        ImageSensor testSensor = Mock(ImageSensor)
        Card testCard = Mock(Card)
        PhotoCamera testCamera = new PhotoCamera(testSensor, testCard)
        testCamera.turnOn()

        when:
        testCamera.pressButton()
        testCamera.turnOff()

        then:
        0 * testSensor.turnOff()
    }

    def "When saving is over sensor should be turned off"(){
        given:
        ImageSensor testSensor = Mock(ImageSensor)
        Card testCard = Mock(Card)
        PhotoCamera testCamera = new PhotoCamera(testSensor, testCard)
        testCamera.turnOn()

        when:
        testCamera.pressButton()
        testCamera.turnOff()
        testCamera.writeCompleted()
        testCamera.turnOff()


        then:
        1 * testSensor.turnOff()
    }

}
