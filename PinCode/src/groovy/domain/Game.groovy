package domain

import domain.helpers.PreviousTry
import domain.helpers.UserGuessAnalyzer
import domain.helpers.UserPincodeCreator
/**
 * Created by Jenson Harvey on 26.02.2017.
 */
class Game {

    int numberOfTries
    List<PreviousTry> previousTries = []
    PinCode originalPinCode
    UserPincodeCreator upc = new UserPincodeCreator()
    UserGuessAnalyzer analyzer = new UserGuessAnalyzer()

    int gameResult = -1

    void playGame() {
        getInput("press any key to start")
        originalPinCode = PinCode.createRandomUniqueDigitPinCode()
        //println originalPinCode

        while (gameResult < 0) {
            displayHistory()
            PinCode userGuessPinCode = upc.createUserGuess()
            println 'analyzing your input '

            Hint hint = analyzer.makeAHint(originalPinCode, userGuessPinCode)
            PreviousTry thisTry = new PreviousTry(pinCode: userGuessPinCode, hint: hint)
            println thisTry
            if (analyzer.haveYouGuessedRight(originalPinCode, userGuessPinCode)) {
                numberOfTries++
                println 'You hacked this PinCode with ' + numberOfTries + ' tries!'
                if (numberOfTries > 10) {
                    println 'You Are So Stupid!!'
                }
                gameResult = 1
            } else {
                String decision = getInput('Do you want to surrender? Type Y to quit or any other kay to resume')
                if (decision.equalsIgnoreCase("y")) {
                    println 'LOOOOL!'
                    println 'this was the original pincode: ' + originalPinCode
                    gameResult = 1
                }
                previousTries.add(thisTry)
                numberOfTries++
            }

        }

        println 'Thanks for the game'

    }

    private void displayHistory() {
        if (previousTries.size()!= 0) {
            println "\n \n \n"
            previousTries.each { println it }
            println "\n \n \n"
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
