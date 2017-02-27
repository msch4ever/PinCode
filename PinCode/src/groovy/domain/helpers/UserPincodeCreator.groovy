package domain.helpers

import domain.PinCode

/**
 * Created by Jenson Harvey on 26.02.2017.
 */
class UserPincodeCreator {

    PinCode createUserGuess(String input) {
        while(true) {
            if (input != null){
                Map<String, Integer> resultMap = validate(input)
                if (resultMap.size() > 1) {
                    return createPinCode(resultMap)
                } else {
                    println "Please type valid pin code"
                }
            }
        }
    }

    private Map<String, Integer> validate(String input) {
        String cleanInput = input.replaceAll("\\s","")
        if (cleanInput.matches("[0-9]+") && cleanInput.length() <= 4) {
            return [
                    'first' : Integer.parseInt(cleanInput.substring(0,1)),
                    'second': Integer.parseInt(cleanInput.substring(1,2)),
                    'third' : Integer.parseInt(cleanInput.substring(2,3)),
                    'fourth': Integer.parseInt(cleanInput.substring(3))
            ]
        }
        return ['Error': -1]
    }

    private PinCode createPinCode(Map<String, Integer> resultMap) {
        return new PinCode(resultMap.first, resultMap.second, resultMap.third, resultMap.fourth)
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
