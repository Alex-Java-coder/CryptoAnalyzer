package Command;

import Alphabet.AlphabetConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Encrypt {

    public static void encryptText(String key, String numRevers) throws IOException {

        Path path = Path.of("src/Files/inText.txt");

        List<String> inputLines = Files.readAllLines(path);

        List<String> encryptedLines = encryptCaesar(inputLines, Integer.parseInt(key), numRevers);

        Files.write(Paths.get("src/Files/encryptedText.txt"), encryptedLines);

    }


    private static List<String> encryptCaesar(List<String> lines, int key, String numRevers) {

        List<String> encryptedLines = new ArrayList<>();

        for (String line : lines) {
            encryptedLines.add(encryptString(line, key, numRevers));
        }

        return encryptedLines;

    }

    private static String encryptString(String input, int key, String numRevers) {

        StringBuilder result = new StringBuilder();

        char[] alphabet = AlphabetConstants.ALPHABET;

        for (char c : input.toCharArray()) {

            int index = findIndex(alphabet, c);

            if (index != -1) {

                int newIndex = calculateNewIndex(index, key, alphabet.length, numRevers);
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
