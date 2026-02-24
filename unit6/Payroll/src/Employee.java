package src;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class describing an Employee, with all its content and operations. 
 */
public class Employee {
    private int id;
    private String name;
    private double rate;
    private ArrayList<double[]> weeks = new ArrayList<>();
    private ArrayList<String> weekIds = new ArrayList<>();
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

    public int getID() { return this.id; }
    public String getName() { return this.name; }
    public double getRate() { return this.rate; }
    public ArrayList<double[]> getWeeks() { return weeks; }
    public ArrayList<String> getWeekIds() { return weekIds; }

    private static String valueAfterColon(String line) {
        int i = line.indexOf(":");
        return line.substring(i + 1).trim();
    }

    private static String extractWeekId(String line){
        int colon = line.indexOf(":");
        String left = line.substring(0, colon).trim();
        left = left.substring(4).trim();
        return left;
    }
}
