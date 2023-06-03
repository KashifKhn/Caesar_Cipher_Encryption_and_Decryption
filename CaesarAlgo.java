package Caesar;

import java.util.Scanner;

public class CaesarAlgo {
    public static char[] capitalAlphabets = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static char[] smallAlphabets = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String encrypt(String plainText, int key) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            int index = 0;
            if (ch >= 'a' && ch <= 'z') {
                encryptCh(key, cipherText, ch, index, smallAlphabets);
            } else if (ch >= 'A' && ch <= 'Z') {
                encryptCh(key, cipherText, ch, index, capitalAlphabets);
            } else if (ch >= '0' && ch <= '9') {
                index = ch - '0'; // Get the numerical value of the digit
                index = (index + key) % 10; // Apply the encryption shift to the index
                char encryptedDigit = (char) (index + '0'); // Convert the index back to a character
                cipherText.append(encryptedDigit);
            } else {
                cipherText.append(ch);
            }
        }

        return cipherText.toString();
    }

    private static void encryptCh(int key, StringBuilder cipherText, char ch, int index, char[] alphabets) {
        for (int j = 0; j < alphabets.length; j++) {
            if (alphabets[j] == ch) {
                index = j;
                break;
            }
        }
        index = (index + key) % 26;
        cipherText.append(alphabets[index]);
    }

    public static String decrypt(int shift, String cipherText) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            int index = 0;
            if (ch >= 'a' && ch <= 'z') {
                decryptCh(shift, plainText, ch, index, smallAlphabets);
            } else if (ch >= 'A' && ch <= 'Z') {
                decryptCh(shift, plainText, ch, index, capitalAlphabets);
            } else if (ch >= '0' && ch <= '9') {
                index = ch - '0'; // Get the numerical value of the digit
                index = (index - shift) % 10; // Apply the encryption shift to the index
                char encryptedDigit = (char) (index + '0'); // Convert the index back to a character
                plainText.append(encryptedDigit);
            } else {
                plainText.append(ch);
            }
        }
        return plainText.toString();
    }

    private static void decryptCh(int key, StringBuilder cipherText, char ch, int index, char[] alphabets) {
        for (int j = 0; j < alphabets.length; j++) {
            if (alphabets[j] == ch) {
                index = j;
                break;
            }
        }
        index = (index - key) % 26;
        cipherText.append(alphabets[index]);
    }


    // Function to print a line of a given length
    public static void printLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    // Function to print an empty line with spaces
    public static void printEmptyLine(int length) {
        System.out.print("*");
        for (int i = 0; i < length; i++) {
            System.out.print(" ");
        }
        System.out.println("*");
    }

    // Function to print text centered within a line of a given length
    public static void printCenteredText(String text, int length) {
        int padding = (length - text.length()) / 2;
        System.out.print("*");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(text);
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        if (text.length() % 2 != 0 && length % 2 == 0) {
            System.out.print(" ");
        }
        System.out.println("*");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char ch;
        do {
            System.out.println("*******[ Welcome to Caesar Cipher ]*******");
            System.out.println();
            String title = " What do You want Encryption or Decryption ";
            String option1 = "1. Encryption";
            String option2 = "2. Decryption";
            int titleLength = title.length();
            int option1Length = option1.length();
            int option2Length = option2.length();
            int maxLength = Math.max(Math.max(titleLength, option1Length), option2Length);
            // Calculate the padding required to center align the text
            int padding = (maxLength - titleLength) / 2;
            // Print the box with centered text
            printLine(maxLength + 2);
            printEmptyLine(maxLength);
            printCenteredText(title, maxLength);
            printCenteredText(option1, maxLength);
            printCenteredText(option2, maxLength);
            printEmptyLine(maxLength);
            printLine(maxLength + 2);

            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.println("Enter the Plain Text");
                String plainText = sc.nextLine();
                System.out.println("Enter the Key");
                int key = sc.nextInt();
                String cipherText = encrypt(plainText, key);
                System.out.println("Cipher Text is: " + cipherText);
            } else if (choice == 2) {
                System.out.println("Enter the Cipher Text");
                String cipherText = sc.nextLine();
                System.out.println("Enter the Key");
                int key = sc.nextInt();
                String plainText = decrypt(key, cipherText);
                System.out.println("Plain Text is: " + plainText);
            } else {
                System.out.println("Invalid Choice");
            }

            System.out.println();
            System.out.println("******************************************************");
            System.out.println("***********  Do you want to continue (Y/N) ***********");
            System.out.println("******************************************************");
            System.out.println();
            ch = sc.next().charAt(0);
        } while (ch != 'N' && ch != 'n');
    }
}
