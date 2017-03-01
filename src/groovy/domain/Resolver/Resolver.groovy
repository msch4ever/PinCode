package domain.Resolver

import domain.Hint
import domain.PinCode.DigitPinCode
import domain.PinCode.PinCode
import domain.Resolver.Digit.Digit
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

    PinCode pickPinCode(Hint hint, DigitPinCode previous) {
        int leave = hint.contain + hint.match
        Set<Digit> digitsToLeave = []
        Set<Digit> digitsToAdd = []
        if (leave == 0) return pool.createPinCodeFormPool()
        if (leave == 1) {
            digitsToLeave.addAll(previous.pickOneDigit())
            return pool.createPinCodeWithThreeRandomDigits()
        }






        while (digitsToLeave.size() < leave) {
            Digit digit = pool.pickRandomDigit()
            if (!previous.usedDigits.contains(digit)) {

            }
        }
    }


}
