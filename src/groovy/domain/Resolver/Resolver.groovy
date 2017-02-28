package domain.Resolver

import domain.PinCode.DigitPinCode
import domain.PinCode.PinCode
import domain.Resolver.Digit.DigitPool

/**
 * Created by Jenson Harvey on 27.02.2017.
 */
class Resolver {

    DigitPool pool

    Resolver() {
        pool = new DigitPool()
    }

    PinCode pickFirstPinCode() {
        PinCode.createRandomUniqueDigitPinCode()
    }

    DigitPinCode pickDigitPinCode() {

    }



}
