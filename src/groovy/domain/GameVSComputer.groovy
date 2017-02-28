package domain

import domain.PinCode.PinCode
import domain.Resolver.Resolver
import domain.helpers.PreviousTry
import domain.helpers.UserPincodeCreator
/**
 * Created by Jenson Harvey on 26.02.2017.
 */
class GameVSComputer {

    int numberOfTries
    List<PreviousTry> previousTries = []
    PinCode originalPinCode
    UserPincodeCreator upc = new UserPincodeCreator()

    Resolver resolver = new Resolver()

    int gameResult = -1

    void playGame() {
        println "It is a simple game. \nMake a pin code and wait. \nGood Luck!!"
        getInput("press Enter to start \n")
        String code = getInput("Type in your pin code!\n")
        originalPinCode = upc.createUserGuess(code)
        println originalPinCode

        PinCode computorGuessPinCode = resolver.pickFirstPinCode() //frist computor try
        Hint hint = resolver.guessAnalyzer.makeAHint(originalPinCode, computorGuessPinCode)
        previousTries.add(new PreviousTry(pinCode: computorGuessPinCode, hint: hint))
        judge(computorGuessPinCode)

        while (gameResult < 0) {
            computorGuessPinCode = resolver.makeGuess()
            hint = resolver.guessAnalyzer.makeAHint(originalPinCode, computorGuessPinCode)
            PreviousTry thisTry = new PreviousTry(pinCode: computorGuessPinCode, hint: hint)
            judge(computorGuessPinCode)
            println thisTry
            previousTries.add(thisTry)
            numberOfTries++
        }
        println 'Thanks for the game'
    }

    private void judge(PinCode computorGuessPinCode) {
        if (analyzer.haveYouGuessedRight(originalPinCode, computorGuessPinCode)) {
            numberOfTries++
            println 'Computor hacked your PinCode with ' + numberOfTries + ' tries!'
            gameResult = 1
        }
    }

    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in))

        System.out.print(prompt)
        System.out.flush()

        try {
            return stdin.readLine()
        } catch (Exception e) {
            return "Error: " + e.getMessage()
        }
    }
}
