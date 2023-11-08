import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Define the length of the password
        System.out.print("Enter the length of the password: ");
        int passwordLength = scanner.nextInt();

        // Step 2: Create a character set
        String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*/=+";

        // Step 3: Create a function to generate the password
        String generatedPassword = generatePassword(passwordLength, characterSet);

        // Step 7: Display the generated password
        System.out.println("Generated Password: " + generatedPassword);
    }

    // Step 4: Use random number generation
    private static String generatePassword(int length, String characterSet) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Step 5: Convert a password to a string
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            char randomChar = characterSet.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}
