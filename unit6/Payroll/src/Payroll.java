package src;

import java.io.FileNotFoundException;

public class Payroll {
    private static Company company;


    /**
     * Main method of the payroll program. Contains the command line implemeting commands for
     * help:             Command (? for help) > ? 
     * loading dataset:  Command (? for help) > load Contoso
     * employee report:  Command (? for help) > employee 1047 
     * paystub report:   Command (? for help) > paystub 1047 03/16/26
     * week report:      Command (? for help) > week 03/16/26
     * termination:      Command (? for help) > quit
     * @param args - command line arguments (not used)
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the payroll system!");
        String command = "";
        while (!command.equals("quit") && !command.equals("q")) {
            System.out.print("Command (? for help) > ");
            command = System.console().readLine();
            String[] commandParts = command.split(" ");
            switch (commandParts[0]) {
                case "help":
                case "?":
                    help();
                    break;

                case "load":
                    if (commandParts.length < 2) {
                        System.out.println("Error: Company name is required.");
                        break;
                    }

                    String companyName = commandParts[1];
                    company = new Company(companyName);
                    String result = company.load("unit6/Payroll/data/" + companyName);
                    System.out.println(result);
                    break;
                
                case "employee":
                    if (company == null){
                        System.out.println("Error: No company loaded.");
                        break;
                    }
                    if (commandParts.length < 2) {
                        System.out.println("Error: Employee ID is required.");
                        break;
                    }

                    int employeeId;
                    try {
                        employeeId = Integer.parseInt(commandParts[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid employee ID.");
                        break;
                    }

                    String report = company.getEmployeeReport(employeeId);
                    System.out.println(report);
                    break;
                
                case "paystub":
                    if (company == null){
                        System.out.println("Error: No company loaded.");
                        break;
                    }

                    if (commandParts.length < 2) {
                        System.out.println("Error: Employee ID and weekID is required.");
                        break;
                    }

                    try {
                        int empId = Integer.parseInt(commandParts[1]);
                        String weekId = commandParts[2];
                        System.out.println(company.getPaystub(empId, weekId));
                    } catch (NumberFormatException e) {
                        System.out.println("Employee ID must be a number.");
                    }
                    break;
                
                case "week":
                    if (company == null){
                        System.out.println("No company loaded.");
                        break;
                    }

                    if (commandParts.length < 2) {
                        System.out.println("Error: Week ID is required.");
                        break;
                    }
                    System.out.println(company.getWeekReport(commandParts[1]));
                    break;
            }
        }
    }

    public static void help(){
        System.out.println("Avaliable commands:");
        System.out.println("\t> load {companyName} - load dataset for company.");
        System.out.println("\t> employee {employeeID} - print total wages for an employee.");
        System.out.println("\t> paystub {employeeID} - print a week paystub for an employee.");
        System.out.println("\t> week {week} - print total and detailed wages for a week.");
        System.out.println("\t> quit - terminates the program.");
    }

    // TODO: Add additional methods, as needed.
}
