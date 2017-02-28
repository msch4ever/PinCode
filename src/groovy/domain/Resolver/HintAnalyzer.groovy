package domain.Resolver

import domain.Hint
import domain.PinCode.DigitPinCode
import domain.Resolver.Digit.DigitPool

/**
 * Created by Jenson Harvey on 01.03.2017.
 */
class HintAnalyzer {

    void analyzeHint(Hint hint, DigitPool pool, DigitPinCode pinCode) {
        if (isHintEmpty(hint)) {
            pool.removeUnneededDigits(pinCode)
        }
    }

    private boolean isHintEmpty(Hint hint) {
        hint.contain == 0 && hint.match == 0
    }
}
