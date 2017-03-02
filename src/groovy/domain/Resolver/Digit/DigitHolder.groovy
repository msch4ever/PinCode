package domain.Resolver.Digit

import domain.Hint

/**
 * Created by Jenson Harvey on 27.02.2017.
 */
class DigitHolder {

    int numberOfPicks = 0

    Digit digit
    DigitStats stats
    Set<Integer> positions = new HashSet<>()
    List<Hint> hints = []
    boolean used
    boolean doNotBelong
    boolean wereOnAllPositions

    boolean shouldYouRemoveMe() {
        if (wereOnAllPositions) {
            if ( hints.find { it.match == 1 } ){
                return false
            }
        }
        return false
    }

    @Override
    String toString() {
        return  "digit=" + digit +
                ", stats=" + stats +
                ", positions=" + positions +
                ", hints=" + hints +
                ", numberOfPicks=" + numberOfPicks +
                ", used=" + used +
                ", doNotBelong=" + doNotBelong +
                ", wereOnAllPossitions=" + wereOnAllPositions + "\n"
    }
}
