package domain.Resolver

import domain.PinCode.DigitPinCode
import domain.PinCode.PinCode
import domain.Resolver.Digit.DigitPool
import domain.helpers.GuessAnalyzer

/**
 * Created by Jenson Harvey on 27.02.2017.
 */
class Resolver {

    DigitPool pool
    HintAnalyzer hintAnalyzer
    GuessAnalyzer guessAnalyzer

    Resolver() {
        pool = new DigitPool()
        hintAnalyzer = new HintAnalyzer()
        guessAnalyzer = new GuessAnalyzer()
    }

    PinCode pickFirstPinCode() {
        PinCode.createRandomUniqueDigitPinCode()
    }

    DigitPinCode pickDigitPinCode() {

    }


}
