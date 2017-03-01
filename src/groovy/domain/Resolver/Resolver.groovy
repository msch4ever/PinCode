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
        Random randomGenerator = new Random()
        int leave = hint.contain + hint.match
        Set<Digit> digitsToLeave = []
        if (leave == 0) return pool.createPinCodeFormPool()
        if (leave == 1) {
            int position = randomGenerator.nextInt(4)
            digitsToLeave.add(previous.usedDigits[position + 1])
            return pool.createPinCodeWithThreeRandomDigits(digitsToLeave[0], hint, position)
        }
        if (leave == 2) {
            Set<Integer> positions = []
            while (positions.size() < 2) {
                int random = randomGenerator.nextInt(4) + 1
                positions.add(random)
            }
            digitsToLeave.add(previous.usedDigits[positions[0]])
            digitsToLeave.add(previous.usedDigits[positions[1]])
            return pool.createPinCodeWithTwoRandomDigits(digitsToLeave, hint, positions)
        }
        if (leave == 3) {
            Set<Integer> positions = []
            while (positions.size() < 3) {
                int random = randomGenerator.nextInt(4) + 1
                positions.add(random)
            }
            digitsToLeave.add(previous.usedDigits[positions[0]])
            digitsToLeave.add(previous.usedDigits[positions[1]])
            digitsToLeave.add(previous.usedDigits[positions[2]])
            return pool.createPinCodeWithThreeRandomDigits(digitsToLeave, hint, positions)
        }
    }


}
