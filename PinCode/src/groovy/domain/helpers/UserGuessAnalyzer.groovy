package domain.helpers

import domain.Hint
import domain.PinCode

/**
 * Created by Jenson Harvey on 26.02.2017.
 */
class UserGuessAnalyzer {

    Hint makeAHint(PinCode originalPinCode, PinCode userGuessPinCode) {
        int contains = 0
        int matches = 0
        originalPinCode.representation.eachWithIndex { int entry1, int index1 ->
            userGuessPinCode.representation.eachWithIndex { int entry2, int index2 ->
                if (entry1 == entry2 && index1 == index2) {
                    matches++
                }
                if (entry1 == entry2) {
                    contains++
                }
            }
        }
        contains = contains - matches
        new Hint(match: matches, contain: contains)
    }

    boolean haveYouGuessedRight(PinCode originalPinCode, PinCode userGuessPinCode) {
        if (originalPinCode == userGuessPinCode) {
            println 'Congratulations! You Won!!'
            return true
        }
        println 'Sorry, make another try!'
        return false
    }
}
