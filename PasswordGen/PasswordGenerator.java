import java.security.SecureRandom;
import java.util.Scanner;
// import necessary Java classes. 
// SecureRandom is used for secure random number generation, and Scanner is used for user input.

public class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //The main method is the entry point of the program. It creates a Scanner object to read user input.

        // Step 1: Define the length of the password
        System.out.print("Enter the length of the password: ");
        int passwordLength = scanner.nextInt();     
        // This part prompts the user to enter the desired length of the password.

        // Step 2: Create a character set
        String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*/=+";
        // Here, a character set is defined, which includes uppercase letters, lowercase letters, numbers, and special characters.

        // Step 3: Create a function to generate the password
        String generatedPassword = generatePassword(passwordLength, characterSet);     
        // The generatePassword method is called to generate a password based on the specified length and character set.

        // Step 7: Display the generated password
        System.out.println("Generated Password: " + generatedPassword);
        // The generated password is then printed to the console.
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
        // This is the generatePassword method. It uses a loop to randomly select characters from 
        // the character set and appends them to a StringBuilder to form the password. 
        // The resulting password is then converted to a string and returned.

        return password.toString();
    }
}
