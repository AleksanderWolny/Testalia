package allMethods;

import java.util.Random;

public class GenerateRandomTestData {

    public String generateRandomNameSurname() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int maxChars = 10;
        int generateNameNumber = random.nextInt(maxChars);
        int generateSurnameNumber = random.nextInt(maxChars);
        StringBuilder name = new StringBuilder(generateNameNumber);
        for (int i = 0; i < generateNameNumber; i++) {
            name.append(chars.charAt(random.nextInt(chars.length())));
        }
        StringBuilder surname = new StringBuilder(generateSurnameNumber);
        for (int i = 0; i < generateSurnameNumber; i++) {
            surname.append(chars.charAt(random.nextInt(chars.length())));
        }
        return name + " " + surname;
    }

    public String generateAnySequenceOfNumber(int sequenceLength) {
        Random random = new Random();
        StringBuilder fifteenNumber = new StringBuilder(sequenceLength);
        for (int i = 0; i < 15; i++) {
            fifteenNumber.append(random.nextInt(10));
        }
        return fifteenNumber.toString();
    }

    public String generateCvv() {
        Random random = new Random();
        StringBuilder threeNumber = new StringBuilder(3);
        for (int i = 0; i < 3; i++) {
            threeNumber.append(random.nextInt(10));
        }
        return threeNumber.toString();
    }
}