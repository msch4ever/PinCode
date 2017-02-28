package domain.Resolver.Digit

/**
 * Created by Jenson Harvey on 27.02.2017.
 */
enum Digit {
    ZERO ('ZERO' , 0),
    ONE  ('ONE'  , 1),
    TWO  ('TWO'  , 2),
    THREE('THREE', 3),
    FOUR ('FOUR' , 4),
    FIVE ('FIVE' , 5),
    SIX  ('SIX'  , 6),
    SEVEN('SEVEN', 7),
    EIGHT('EIGHT', 8),
    NINE ('NINE' , 9)

    int id
    String value

    Digit() { }

    Digit(String value, int id) {
        this.value = value
        this.id = id
    }

    static Digit findById(int id) {
        values()[id]
    }
}
