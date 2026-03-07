package src;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class describing an Employee, with all its content and operations. 
 */
public class Employee {
    private final int id;
    private final String name;
    private final double rate;
    private final ArrayList<double[]> weeks = new ArrayList<>();
    private final ArrayList<String> weekIds = new ArrayList<>();
    /**
     * Constructs a new Employee object given the reader scanner of its data file.
     * @param input - scanner positioned at the beginning of the employee's data file. 
     */
    public Employee(Scanner input) {
        this.id = Integer.parseInt(valueAfterColon(input.nextLine()));
        this.name = valueAfterColon(input.nextLine());
        this.rate = Double.parseDouble(valueAfterColon(input.nextLine()));
        
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.startsWith("Week")) {
                String[] parts = valueAfterColon(line).split(" ");
                double[] days = new double[7];
                String weekId = extractWeekId(line);
                for (int i = 0; i < 7; i++) {
                    days[i] = Double.parseDouble(parts[i]);
                }   
                weeks.add(days);
                weekIds.add(weekId);
            }
        }
    }

    /**
     * Get the employee's ID
     * @return the employee's ID
     */
    public int getID() { return this.id; }

    /**
     * Get the employee's name
     * @return the employee's name
     */
    public String getName() { return this.name; }

    /**
     * Get the employee's hourly rate
     * @return the employee's hourly rate
     */
    public double getRate() { return this.rate; }

    /**
     * Get the employee's hours worked for each week
     * @return the employee's hours worked for each week
     */
    public ArrayList<double[]> getWeeks() { return weeks; }

    /**
     * Get the employee's week IDs
     * @return the employee's week IDs
     */
    public ArrayList<String> getWeekIds() { return weekIds; }

    /**
     * Helper method to extract the value after the colon in a line of the employee's data file.
     * @param line - a line of the employee's data file
     * @return the value after the colon in the line
     */
    private static String valueAfterColon(String line) {
        int i = line.indexOf(":");
        return line.substring(i + 1).trim();
    }

    /**
     * Helper method to extract the week ID from a line of the employee's data file.
     * @param line - a line of the employee's data file that starts with "Week"
     * @return the week ID extracted from the line
     */
    private static String extractWeekId(String line){
        int colon = line.indexOf(":");
        String left = line.substring(0, colon).trim();
        left = left.substring(4).trim();
        return left;
    }
}
