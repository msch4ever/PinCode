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
        println "It is a simple game. \nGuess a pin code, analyze the hint you receive and make another one. \nGood Luck!!"
        getInput("press Enter to start \n")
        originalPinCode = PinCode.createRandomUniqueDigitPinCode()
        println originalPinCode

        while (gameResult < 0) {
            displayHistory()
            String input = getInput("Make your guess or type Q to surrender!\n")
            if (input.equalsIgnoreCase("q")) {
                println 'LOOOOL! \nHere is the original pincode: ' + originalPinCode
                gameResult = 1
                break
            }
            PinCode userGuessPinCode = upc.createUserGuess(input)
            Hint hint = analyzer.makeAHint(originalPinCode, userGuessPinCode)
            PreviousTry thisTry = new PreviousTry(pinCode: userGuessPinCode, hint: hint)
            if (analyzer.haveYouGuessedRight(originalPinCode, userGuessPinCode)) {
                numberOfTries++
                println 'You hacked this PinCode with ' + numberOfTries + ' tries!'
                if (numberOfTries > 10) {
                    println 'You Are So Stupid!!'
                }
                gameResult = 1
            }
            previousTries.add(thisTry)
            numberOfTries++
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
