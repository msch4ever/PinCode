package domain

/**
 * Created by Jenson Harvey on 26.02.2017.
 */
class Hint {

    int match
    int contain

    Hint() { }

    boolean noMatches() {
        match == 0
    }

    @Override
    String toString() {
        return  "match=" + match +  ", contain=" + contain
    }
}
