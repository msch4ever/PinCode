package domain.Resolver.Digit

/**
 * Created by Jenson Harvey on 27.02.2017.
 */
class DigitHolder {

    Digit digit
    DigitStats stats
    Set<Integer> positions = new HashSet<>()
    boolean used
    boolean doNotBelong
    boolean wereOnAllPositions

    @Override
    String toString() {
        return  "digit=" + digit +
                ", stats=" + stats +
                ", positions=" + positions +
                ", used=" + used +
                ", doNotBelong=" + doNotBelong +
                ", wereOnAllPossitions=" + wereOnAllPositions + "\n"
    }
}
