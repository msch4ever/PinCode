package domain.Resolver.Digit

/**
 * Created by Jenson Harvey on 27.02.2017.
 */
enum Digit {
    ZERO ('0', 1),
    ONE  ('1', 1),
    TWO  ('2', 2),
    THREE('3', 3),
    FOUR ('4', 4),
    FIVE ('5', 5),
    SIX  ('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE ('9', 9)

    int id
    String value

    Digit(String value, int id) { }
}
