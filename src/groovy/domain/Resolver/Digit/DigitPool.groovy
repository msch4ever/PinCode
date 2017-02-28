package domain.Resolver.Digit

import domain.PinCode.DigitPinCode
/**
 * Created by Jenson Harvey on 28.02.2017.
 */
class DigitPool {
    List<DigitHolder> pool

    DigitPool() {
        this.pool = [
                new DigitHolder(digit: Digit.ZERO),
                new DigitHolder(digit: Digit.ONE),
                new DigitHolder(digit: Digit.TWO),
                new DigitHolder(digit: Digit.THREE),
                new DigitHolder(digit: Digit.FOUR),
                new DigitHolder(digit: Digit.FIVE),
                new DigitHolder(digit: Digit.SIX),
                new DigitHolder(digit: Digit.SEVEN),
                new DigitHolder(digit: Digit.EIGHT),
                new DigitHolder(digit: Digit.NINE)
        ]
    }

    void updatePoolAfterPick(DigitPinCode pinCode) {
        updateUsed(pinCode)
        updatePositions(pinCode)
    }

    void updateUsed(DigitPinCode pinCode) {
        pinCode.getUsedDigits().id.each {
            pool.get(it).used = true
        }
    }

    void updatePositions(DigitPinCode pinCode) {
        List<Integer> usedDigitsIds = pinCode.getUsedDigits().id
        usedDigitsIds.eachWithIndex { int digit, int position ->
            DigitHolder currentDigit = pool.get(digit)
            currentDigit.positions.add(position)
            if (currentDigit.positions.size() == 4) {
                currentDigit.wereOnAllPositions = true
            }
        }
    }

    void removeUnneededDigits(DigitPinCode pinCode) {
        pinCode.getUsedDigits().id.each { pool.remove(it)}
    }

    @Override
    String toString() {
        return pool.values().each { it.toString() + "\n" }
    }
}
