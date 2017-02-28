package domain.helpers

import domain.Hint
import domain.PinCode.PinCode

/**
 * Created by Jenson Harvey on 26.02.2017.
 */
class PreviousTry {
    PinCode pinCode
    Hint hint

    @Override
    String toString() {
        return  'Pin Code: ' + pinCode + '   ' + hint
    }
}
