package domain.Resolver

import domain.Hint
import domain.PinCode.DigitPinCode
import domain.Resolver.Digit.DigitPool
/**
 * Created by Jenson Harvey on 01.03.2017.
 */
class HintAnalyzer {

    boolean noHints

    void verifyNoHits(Hint hint, DigitPool pool, DigitPinCode pinCode) {
        if (isHintEmpty(hint)) {
            pool.removeUnneededDigits(pinCode)
            noHints = true
        }
        noHints = false
    }

    private boolean isHintEmpty(Hint hint) {
        hint.contain == 0 && hint.match == 0
    }
}
