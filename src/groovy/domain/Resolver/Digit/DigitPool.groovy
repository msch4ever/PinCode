package domain.Resolver.Digit

/**
 * Created by Jenson Harvey on 28.02.2017.
 */
class DigitPool {
    DigitHolder zero
    DigitHolder one
    DigitHolder two
    DigitHolder three
    DigitHolder four
    DigitHolder five
    DigitHolder six
    DigitHolder seven
    DigitHolder eight
    DigitHolder nine

    DigitPool() {
        this.zero  = new DigitHolder(digit: Digit.ZERO)
        this.one   = new DigitHolder(digit: Digit.ONE)
        this.two   = new DigitHolder(digit: Digit.TWO)
        this.three = new DigitHolder(digit: Digit.THREE)
        this.four  = new DigitHolder(digit: Digit.FOUR)
        this.five  = new DigitHolder(digit: Digit.FIVE)
        this.six   = new DigitHolder(digit: Digit.SIX)
        this.seven = new DigitHolder(digit: Digit.SEVEN)
        this.eight = new DigitHolder(digit: Digit.EIGHT)
        this.nine  = new DigitHolder(digit: Digit.NINE)
    }
}
