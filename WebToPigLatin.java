import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WebToPigLatin {
    public static void main(String[] args) {
        try {
            File inputFile = new File(args[0]);
            FileWriter outputFile = new FileWriter(args[1]);
            Scanner fileReader = new Scanner(inputFile);

            while (fileReader.hasNext()) {
                String input = fileReader.next();

                if (isHtmlTag(input) == 1) {
                    outputFile.write(input + "\n");
                } else if (isHtmlTag(input) == 2) {
                    while (!input.contains(">")) {
                        outputFile.write(input + " ");
                        input = fileReader.next();
                    }
                } else {
                    String pigLatinWord = convertToPigLatin(input);
                    outputFile.write(pigLatinWord + " ");
                }
            }
            fileReader.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int isHtmlTag(String input) {
        if (input.startsWith("<") && input.endsWith(">") || input.endsWith("/>")) {
            return 1;
        }
        if (input.startsWith("<") && !input.endsWith(">")) {
            return 2;
        }

        return 3;
        // if it returns 1 it is normal one thing closing
        // if it returns 2 it is not closed
        // if it returns 3 it is false
    }

    private static String convertToPigLatin(String input) {
        if (input.isEmpty()) {
            return input;
        }

        if (input.charAt(0) == '\'') {
            return input;
        }
        if (input.contains("0") || input.contains("1") || input.contains("2") || input.contains("3")
                || input.contains("4") || input.contains("5") || input.contains("6") || input.contains("7")
                || input.contains("8") || input.contains("9")) {
            return input;
        }

        boolean isCapital = Character.isUpperCase(input.charAt(0));
        input = input.toLowerCase();

        if (input.charAt(0) == 'a' || input.charAt(0) == 'A' || input.charAt(0) == 'e'
                || input.charAt(0) == 'E' || input.charAt(0) == 'i' || input.charAt(0) == 'I'
                || input.charAt(0) == 'o' || input.charAt(0) == 'O' || input.charAt(0) == 'u'
                || input.charAt(0) == 'U') {
            input = input + "way";
            if (isCapital) {
                input = Character.toUpperCase(input.charAt(0)) + input.substring(1);
            }
            return input;
        } else {
            int stoppingIndex = 0;
            for (int index = 0; index < input.length(); index++) {
                if (input.charAt(index) == 'A' || input.charAt(index) == 'a'
                        || input.charAt(index) == 'e'
                        || input.charAt(index) == 'E' || input.charAt(index) == 'i'
                        || input.charAt(index) == 'I'
                        || input.charAt(index) == 'o' || input.charAt(index) == 'O'
                        || input.charAt(index) == 'u'
                        || input.charAt(index) == 'U') {
                    stoppingIndex = index;
                    break;
                }
            }
            if (stoppingIndex == 0) {
                return input;
            }

            String pigLatin = input.substring(stoppingIndex) + input.substring(0, stoppingIndex) + "ay";

            if (isCapital) {
                input = Character.toUpperCase(pigLatin.charAt(0)) + pigLatin.substring(1);
            } else {
                input = pigLatin;
            }
            return input;
        }
    }
}
