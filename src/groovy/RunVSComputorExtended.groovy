import domain.GameVSComputorExtended
/**
 * Created by Jenson Harvey on 01.03.2017.
 */
class RunVSComputorExtended {
    static void main(String[] args) {
        boolean decision = true
        while (decision) {
            decision = new GameVSComputorExtended().playGame()
        }
        System.exit(0)
    }
}
