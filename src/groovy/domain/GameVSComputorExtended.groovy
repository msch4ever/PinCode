package domain

import domain.PinCode.DigitPinCode
import domain.PinCode.PinCode
import domain.Resolver.Resolver
import domain.helpers.PreviousTry
import domain.helpers.UserPincodeCreator
/**
 * Created by Jenson Harvey on 26.02.2017.
 */
class GameVSComputorExtended {

    int numberOfTries
    List<PreviousTry> previousTries = []
    PinCode originalPinCode
    PinCode computorGuessPinCode
    UserPincodeCreator upc = new UserPincodeCreator()

    Resolver resolver = new Resolver()

    int gameResult = -1

    boolean playGame() {
        println "It is a simple game. \nMake a pin code and wait. \nGood Luck!!"
        getInput("press Enter to start \n")
        String code = getInput("Type in your pin code!\n")
        originalPinCode = upc.createUserGuess(code)
        println originalPinCode

        computorGuessPinCode = resolver.pickFirstPinCode() //frist computor try
        Hint hint = resolver.guessAnalyzer.makeAHint(originalPinCode, computorGuessPinCode)
        previousTries.add(new PreviousTry(pinCode: computorGuessPinCode, hint: hint))
        resolver.pool.updatePoolAfterPick(new DigitPinCode(computorGuessPinCode), hint)
        resolver.hintAnalyzer.verifyNoHits(hint, resolver.pool, new DigitPinCode(computorGuessPinCode))
        judge(computorGuessPinCode)

        while (gameResult < 0) {
            computorGuessPinCode = resolver.pickPinCode(hint, new DigitPinCode(computorGuessPinCode))
            hint = resolver.guessAnalyzer.makeAHint(originalPinCode, computorGuessPinCode)
            PreviousTry thisTry = new PreviousTry(pinCode: computorGuessPinCode, hint: hint)
            resolver.pool.updatePoolAfterPick(new DigitPinCode(computorGuessPinCode), hint)
            if (!resolver.hintAnalyzer.noHits) {
                resolver.hintAnalyzer.verifyNoHits(hint, resolver.pool, new DigitPinCode(computorGuessPinCode))
            }
            judge(computorGuessPinCode)
            println thisTry
            previousTries.add(thisTry)
            numberOfTries++
        }
        println 'Thanks for the game'
        String decision = getInput("\npress Y to start new game or Q to quit \n")
        if (decision.equalsIgnoreCase('y')) {
            return true
        }
        if (decision.equalsIgnoreCase('q')) {
            return false
        }
        false
    }

    private void judge(PinCode computorGuessPinCode) {
        if (resolver.guessAnalyzer.haveYouGuessedRight(originalPinCode, computorGuessPinCode)) {
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
