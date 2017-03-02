package domain.Resolver.Digit

import domain.PinCode.DigitPinCode
import domain.PinCode.PinCode
/**
 * Created by Jenson Harvey on 28.02.2017.
 */
class DigitPool {
    Map<String, DigitHolder> pool

    DigitPool() {
        this.pool = [
                "ZERO" : new DigitHolder(digit: Digit.ZERO),
                "ONE"  : new DigitHolder(digit: Digit.ONE),
                "TWO"  : new DigitHolder(digit: Digit.TWO),
                "THREE": new DigitHolder(digit: Digit.THREE),
                "FOUR" : new DigitHolder(digit: Digit.FOUR),
                "FIVE" : new DigitHolder(digit: Digit.FIVE),
                "SIX"  : new DigitHolder(digit: Digit.SIX),
                "SEVEN": new DigitHolder(digit: Digit.SEVEN),
                "EIGHT": new DigitHolder(digit: Digit.EIGHT),
                "NINE" : new DigitHolder(digit: Digit.NINE)
        ]
    }

    void updatePoolAfterPick(DigitPinCode pinCode) {
        updateUsed(pinCode)
        updatePositions(pinCode)
    }

    void updateUsed(DigitPinCode pinCode) {
        pinCode.usedDigits.value.each {
            pool.get(it)?.used = true
        }
    }

    void updatePositions(DigitPinCode pinCode) {
        List<String> usedDigitsIds = pinCode.usedDigits.value
        usedDigitsIds.eachWithIndex { String digit, int position ->
            DigitHolder currentDigit = pool.get(digit)
            if (currentDigit) {
                currentDigit.positions.add(position)
                if (currentDigit.positions.size() == 4) {
                    currentDigit.wereOnAllPositions = true
                }
            }
        }
    }

    void removeUnneededDigits(DigitPinCode pinCode) {
        pinCode.usedDigits.value.each { this.pool.remove(it)}
    }

    // TODO: refactor this
    void removeRestDigits(DigitPinCode pinCode) {
        def values = this.pool.findAll {
            !pinCode.usedDigits.value.contains(it.value.digit.value)
        }
        values.each { this.pool.remove(it.value.digit.value)}
    }

    PinCode createPinCodeFormPool() {
        List<Integer> availableDigits = pool.collect {
            it.value.digit.id
        }
        int randomSize = availableDigits.size()

        PinCode pinCode = new PinCode()
        pinCode.firstPlace = availableDigits.get(Math.random() * randomSize as int)
        while( true ) {
            pinCode.secondPlace = availableDigits.get(Math.random() * randomSize as int)
            if( pinCode.secondPlace != pinCode.firstPlace ) break
        }
        while( true ) {
            pinCode.thirdPlace = availableDigits.get(Math.random() * randomSize as int)
            if( pinCode.thirdPlace != pinCode.firstPlace && pinCode.thirdPlace != pinCode.secondPlace ) break
        }
        while( true ) {
            pinCode.fourthPlace = availableDigits.get(Math.random() * randomSize as int)
            if( pinCode.fourthPlace != pinCode.firstPlace && pinCode.fourthPlace != pinCode.secondPlace && pinCode.fourthPlace != pinCode.thirdPlace ) break
        }
        pinCode.representation = pinCode.setRepresentaion()
        pinCode
    }

    Digit pickRandomDigit() {
        List<Integer> availableDigits = pool.collect {
            it.value.digit.id
        }
        Digit.findById(availableDigits.get(Math.random() * availableDigits.size() as int))
    }

    /*PinCode createPinCodeWithThreeRandomDigits(Digit digitToKeep, Hint hint, int position) {

    }

    PinCode createPinCodeWithTwoRandomDigits(Set<Digit> digits, Hint hint, Set<Integer> positions) {

    }

    PinCode createPinCodeWithThreeRandomDigits(Set<Digit> digits, Hint hint, Set<Integer> positions) {

    }*/

    DigitPinCode createFromDigitList(Set<Digit> digits, Set<Integer> positions) {
        Random randomGenerator = new Random()
        List<Digit> newUsedDigits = Arrays.asList(new Digit[4])
        digits.eachWithIndex { Digit it, int i ->
            newUsedDigits.set(positions[i], it)
        }
        Set<Integer> unusedPositions = []
        while (unusedPositions.size() < 4 - positions.size()) {
            int random = randomGenerator.nextInt(4)
            if (!positions.contains(random)){
                unusedPositions.add(random)
                while (true) {
                    Digit digit = pickRandomDigit()
                    if (!newUsedDigits.contains(digit)) {
                        newUsedDigits.set(random, digit)
                        break
                    }
                }
            }
        }
        new DigitPinCode(newUsedDigits[0], newUsedDigits[1], newUsedDigits[2], newUsedDigits[3])
    }

    PinCode shufflePinCode(PinCode) {

    }

    @Override
    String toString() {
        return pool.values().each { it.toString() + "\n" }
    }
}
