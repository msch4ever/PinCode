package domain

import groovy.transform.EqualsAndHashCode
/**
 * Created by Jenson Harvey on 26.02.2017.
 */
@EqualsAndHashCode
class PinCode {
    private int firstPlace
    private int secondPlace
    private int thirdPlace
    private int fourthPlace

    List<Integer> representation

    PinCode() { }

    PinCode(int firstPlace, int secondPlace, int thirdPlace, int fourthPlace) {
        this.firstPlace = firstPlace
        this.secondPlace = secondPlace
        this.thirdPlace = thirdPlace
        this.fourthPlace = fourthPlace
        this.representation = setRepresentaion()
    }

    static PinCode createRandomUniqueDigitPinCode() {
        PinCode pinCode = new PinCode()
        pinCode.firstPlace = Math.random() * 9
        while( true ) {
            pinCode.secondPlace = Math.random() * 9
            if( pinCode.secondPlace != pinCode.firstPlace ) break
        }
        while( true ) {
            pinCode.thirdPlace = Math.random() * 9
            if( pinCode.thirdPlace != pinCode.firstPlace && pinCode.thirdPlace != pinCode.secondPlace ) break
        }
        while( true ) {
            pinCode.fourthPlace = Math.random() * 9
            if( pinCode.fourthPlace != pinCode.firstPlace && pinCode.fourthPlace != pinCode.secondPlace && pinCode.fourthPlace != pinCode.thirdPlace ) break
        }
        pinCode.representation = pinCode.setRepresentaion()

        println 'random PinCode has been created '
        pinCode
    }

    private List<Integer> setRepresentaion() {
        return [firstPlace, secondPlace, thirdPlace, fourthPlace]
    }

    @Override
    String toString() {
        return firstPlace + ' ' + secondPlace + ' ' + thirdPlace + ' ' + fourthPlace

    }
}
