package Command;

import Alphabet.AlphabetConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Decrypt {

    public static void decryptText(String key, String numRevers) throws IOException {

        List<String> encryptedLines = Files.readAllLines(Path.of("src/Files/encryptedText.txt"));

        List<String> decryptedLines = decryptCaesar(encryptedLines, Integer.parseInt(key), numRevers);

        Files.write(Paths.get("src/Files/decryptedText.txt"), decryptedLines);

    }

    private static List<String> decryptCaesar(List<String> lines, int key, String numRevers) {

        List<String> decryptedLines = new ArrayList<>();
        for (String line : lines) {

            decryptedLines.add(decryptString(line, key, numRevers));
        }
        return decryptedLines;

    }

    private static String decryptString(String input, int key, String numRevers) {

        StringBuilder result = new StringBuilder();
        char[] alphabet = AlphabetConstants.ALPHABET;

        String actualDirection = numRevers.equals("2") ? "1" : "2";

        for (char c : input.toCharArray()) {
            int index = findIndex(alphabet, c);
            if (index != -1) {

                int newIndex = calculateNewIndex(index, key, alphabet.length, actualDirection);
                result.append(alphabet[newIndex]);
            } else {

                result.append(c);
            }
        }

        return result.toString();

    }

    private static int findIndex(char[] alphabet, char c) {

        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return -1;

    }

    private static int calculateNewIndex(int index, int key, int length, String numRevers) {

        if (numRevers.equals("2")) {

            return (index + key) % length;
        } else {

            return (index - key + length) % length;
        }

    }

}
