package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class describing a Company, with all its content and operations. 
 */
public class Company {
    private String name;
    private ArrayList<Employee> employees;
    private ArrayList<String> weekIds = new ArrayList<>();

    /**
     * Constructs a new Company object.
     * @param name - name of the commpany.
     */
    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        }

    /**
     * Load the dataset for this company from the given directory.
     * Returns "OK" on success, or a specific error on failure.
     * @param - path to the directory containing the dataset for this company.
     * @return "OK" if succesfull, or an error message on failure.
     * @throws FileNotFoundException 
     */
    public String load(String dirPath) throws FileNotFoundException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return "Error: Company dataset not found.";
        }

        File[] files = dir.listFiles();
        boolean firstLoaded = false;
        for (File file : files) {
            if (file.isFile()) {
                Scanner input = new Scanner(file);
                Employee employee = new Employee(input);
                employees.add(employee);
                input.close();
                if (!firstLoaded) {
                    weekIds.addAll(employee.getWeekIds());
                    firstLoaded = true;
                }
            }
        }

        return "OK";
    }
    
    /**
     * Generates a report for a specific employee, across all the work weeks.
     * The report should look as shown below:
     * 
     * Command (? for help) > employee 1047
     * Total wages for employee 'Olivia Reed': $11,926.00
     * 
     * @param employeeId the employee ID for whom to generate the report.
     * @return the employee report if successful, or a custom error message on failure.
     */
    public String getEmployeeReport(int employeeId) {
        if (employees.size() == 0) {
            return "Error: No company loaded.";
        }
        for (Employee e : employees) {
            if (e.getID() == employeeId) {
                double totalHours = 0;
                for (double[] week : e.getWeeks()) {
                    for (double day : week) {
                        totalHours += day;
                    }
                }
                double totalPay = totalHours * e.getRate();

                return String.format("Total wages for employee '%s': $%,.2f", e.getName(), totalPay);
            }
        }
        return "Error: Employee not found.";
    }

    /**
     * Generates a paystub report for a specific employee and a specific week of work.
     * The report should look as shown below:
     * 
     * Command (? for help) > paystub 1047 03/16/26
     * ----- CONTOSO -----
     * Name: OLIVIA REED
     * ID: 1047
     * Hourly Rate: 26.8
     *    Week 03/16/26
     *    Hours worked: 30.00
     *    Pay: $804.00
     * 
     * @param employeeId - a specific employee with this company.
     * @param weekId - a specific week for which to generate the report.
     * @return the paystub report if successful, or a custom error message on failure.
     */
    public String getPaystub(int employeeId, String weekId) {
        if (employees.size() == 0) {
            return "Error: No company loaded.";
        }
        Employee emp = null;
        for (Employee e : employees) {
            if (e.getID() == employeeId) {
                emp = e;
                break;
            }
        }

        if (emp == null) {
            return "Error: Employee not found.";
        }
        int weekIndex = weekIds.indexOf(weekId);
        if (weekIndex == -1) {
            return "Error: Week not found.";
        }

        if (weekIndex >= emp.getWeeks().size()) {
            return "Error: Employee does not have data for the specified week.";
        }

        double hours = 0;
        for (double h : emp.getWeeks().get(weekIndex)){
            hours += h;
        }

        double pay = hours * emp.getRate();

        String result = "";
        result += "----- " + name.toUpperCase() + " -----\n";
        result += "Name: " + emp.getName().toUpperCase() + "\n";
        result += "ID: " + emp.getID() + "\n";
        result += String.format("Hourly Rate: $%.2f\n", emp.getRate());
        result += "   Week " + weekId + "\n";
        result += String.format("   Hours worked: %.2f\n", hours);
        result += String.format("   Pay: $%,.2f", pay);

        return result;
    }

    /**
     * Generates a report for a specific week of work across across all employees.
     * The report should look as shown below:
     * 
     * Command (? for help) > week 03/16/26
     * ----- CONTOSO ----- Week: 03/16/26 ----- Payments: $12,730.85
     * 1047_OLIVIA REED, Hours_30.00, Pay_$804.00
     * 1512_JILL DOE, Hours_39.25, Pay_$1,193.20
     * 2279_ETHAN BROOKS, Hours_31.00, Pay_$973.40
     * ...
     * 
     * @param weekId - a string identifier for a specific week of work.
     * @return the week report if successful, or a custom error message on failure.
     */
    public String getWeekReport(String weekId) {
        if (employees.size() == 0) {
            return "Error: No company loaded.";
        }
        int weekIndex = weekIds.indexOf(weekId);
        if (weekIndex == -1) {
            return "Error: Week not found.";
        }

        double totalPayments = 0;

        for (Employee e : employees) {
            if (weekIndex >= e.getWeeks().size()) {
                continue;
            }
            double hours = 0;
            double[] days = e.getWeeks().get(weekIndex);

            for (double h : days){
                hours += h;
            }

            totalPayments += hours * e.getRate();
        }

        String output = "----- " + name.toUpperCase() + " ----- Week: " + weekId
            + " ----- Payments: " + String.format("$%,.2f", totalPayments) + "\n";
        
        for (Employee e : employees) {
            if (weekIndex >= e.getWeeks().size()) {
                continue;
            }
            double hours = 0;
            double[] days = e.getWeeks().get(weekIndex);

            for (double h : days){
                hours += h;
            }

            double pay = hours * e.getRate();
            output += e.getID() + "_" + e.getName().toUpperCase()
                + ", Hours_" + String.format("%.2f", hours)
                + ", Pay_" + String.format("$%,.2f", pay)
                + "\n";
        }
        output = output.substring(0, output.length()-1);
        return output;
    }
}
