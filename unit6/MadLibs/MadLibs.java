import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Reyansh Jajoo
 * @period 3
 * @date 2-20-2026
 * This program allows the user to play a game of Mad Libs.
 */

public class MadLibs {
    private static final String BASE_PATH = "C:\\Users\\Reyansh Jajoo\\OneDrive - Lake Washington School District\\High School\\10th Grade\\School\\3- AP CSA\\LW-CSA-2025\\unit6\\MadLibs\\files\\";

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        printIntro();
        String action = "";
        while (!action.toLowerCase().equals("q")) {
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            action = console.nextLine();
            if (action.toLowerCase().equals("c")) {
                modifyFile(getFileName(console), getOutputFileName(console), console);
            } else if (action.toLowerCase().equals("v")) {
                viewFile(getFileName(console));
            } else if (action.toLowerCase().equals("q")) {
                break;
            }
        }
        console.close();
    }

    /**
     * Prints an introduction to the game of Mad Libs.
     */
    public static void printIntro() {
        System.out.println("""
                Welcome to the game of Mad Libs. I
                will ask you to provide various words
                and phrases to fill in a story.
                The result will be written to an output file. \n""");
    }

    /**
     * Prompts the user for file name until they provide one that exists in the
     * directory
     * 
     * @param console
     * @return the input file name
     */
    public static String getFileName(Scanner console) {
        System.out.print("Input file name: ");
        while (true) {
            String fileName = console.nextLine();

            File f = new File(BASE_PATH + fileName);

            if (f.exists() && f.isFile()) {
                return fileName;
            } else {
                System.out.print("File not found. Try again: ");
            }
        }
    }

    /**
     * Prompts the user for an output file name and returns it.
     * 
     * @param console
     * @return the output file name
     */
    public static String getOutputFileName(Scanner console) {
        System.out.print("Output file name: ");
        return console.nextLine();
    }

    /**
     * Reads the input file, prompts the user for words to replace the placeholders,
     * and writes the final story to the output file.
     * 
     * @param fileName       the name of the input file
     * @param outputFileName the name of the output file
     * @param console        the Scanner
     */
    public static void modifyFile(String fileName, String outputFileName, Scanner console) {
        System.out.println();
        File file = new File(BASE_PATH + fileName);

        String finalText = "";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                String newLine = "";
                for (String word : words) {
                    String toAdd = word;
                    if (word.charAt(0) == '<' && word.charAt(word.length() - 1) == '>') {
                        String aAn = "aeiouAEIOU".indexOf(word.charAt(1)) != -1 ? "an" : "a";
                        String prompt = word.substring(1, word.length() - 1);
                        if (prompt.contains("-")) {
                            prompt = String.join(" ", prompt.split("-"));
                        }
                        System.out.print("Please enter " + aAn + " " + prompt + ": ");
                        toAdd = console.nextLine();
                    }
                    boolean isPunctuation = ".\",!\"?\";:".contains(toAdd);
                    if (!newLine.equals("") && !isPunctuation) {
                        newLine += " ";
                    }
                    newLine += toAdd;
                }

                finalText += newLine + "\n";

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        try (PrintWriter writer = new PrintWriter(BASE_PATH + outputFileName)) {
            writer.print(finalText);
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file.");
        }
        System.out.println("Your mad-lib has been created! \n");
    }

    /**
     * Reads the specified file and prints its contents to the console.
     * 
     * @param fileName the name of the file to view
     */
    public static void viewFile(String fileName) {
        System.out.println();
        File file = new File(BASE_PATH + fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        System.out.println();
    }
}