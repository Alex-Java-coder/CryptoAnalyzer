package Command;

import Alphabet.AlphabetConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class BruteForce {

    public static void bruteForceDecrypt() throws IOException {

        List<String> encryptedLines = Files.readAllLines(Path.of("src/Files/encryptedText.txt"));

        char[] alphabet = AlphabetConstants.ALPHABET;
        int alphabetLength = alphabet.length;

        List<String> resultLines = new ArrayList<>();

        for (int key = 1; key < alphabetLength; key++) {

            resultLines.add("----- Ключ: " + key + " -----");

            for (String line : encryptedLines) {
                resultLines.add(decryptLine(line, key, alphabet));
            }

            resultLines.add("");
        }

        Files.write(Paths.get("src/Files/decryptedBruteForce.txt"), resultLines);

    }

    private static String decryptLine(String line, int key, char[] alphabet) {

        StringBuilder result = new StringBuilder();
        int length = alphabet.length;

        for (char c : line.toCharArray()) {
            int index = findIndex(alphabet, c);
            if (index != -1) {

                int newIndex = (index - key + length) % length;
                result.append(alphabet[newIndex]);
            } else {

                result.append(c);
            }
        }

        return result.toString();

    }

    private static int findIndex(char[] alphabet, char c) {

        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) return i;
        }
        return -1;

    }

}
