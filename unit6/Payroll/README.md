# AP4~DTS.Payroll

Lab assignment for the Unit 4 AP CS-A class (new 2025 curriculum) focused on Data Analysis and Class Design. In this assignment you are required to implement the payroll system for a fictional company. The program has access to a dataset of employee records, in the form of a large number of files, placed in a folder named after the company.

## Input
This repository contains a `data` folder with two subfolders: `Contoso` and `Initech`. Each of these contain a number of text files, named as follows:
```
data/
├─ Contoso/
│  ├─ 1047.txt
│  ├─ 1512.txt
│  └─ ...
└─ Initech/
     ├─ 2741.txt
	 └─ ...
```
Each file is named with the _Employee ID_ of one employee, and contains employment data for that employee as shown below:

<u>**data/Contoso/4096.txt**:</u>
```
    Employee ID: 4096
    Employee Name: Liam Carter
    Hourly Rate: 35.9
    Week 03/02/26: 5.25 2.0 6.5 1.75 7.25 3.5 4.0
    Week 03/09/26: 2.75 7.0 3.25 5.5 1.0 6.75 4.5
    ...
```
where:
- _Employee ID_: a string containing decimal digits, providing the employee unique identifier.
- _Employee Name_: a string containing the full name of the employee (can be multiple words).
- _Hourly Rate_: a numerical double value, giving the $ amount of one hour of work for this employee.
- List of work weeks, _Week MM/DD/YYY_: a sequence of 7 numerical double values, representing the number of hours of work this employee performed during that specific week.  

## Requirements
You are required to write a complete program performing the following actions:
- [ ] Prints a greeting message,
- [ ] Enters a command loop supporting the following commands:  

    <div style="font-size: 1.1em; font-family: Consolas, monospace;"><strong>&gt; help</div>
    <div style="margin-left: 2em;">
    Prints a list of available commands. Example:
        <pre><code>> help<br>Available commands:
    > load {companyName} - load dataset for company.
    > employee {employeeID} - print total wages for an employee.
    > paystub {employeeID} {week} - print a week paystub for an employee.
    > week {week} - print total and detailed wages for a week.
    > quit - terminates the program.</code></pre>
    </div>

    <div style="font-size: 1.1em; font-family: Consolas, monospace;"><strong>&gt; load</strong> <em>companyName</em></div>
    <div style="margin-left: 2em;">
        Locates the folder for the <em>companyName</em> and loads its content into memory. Prints `OK` when successful, or an explicit error message on failure. Example:
        <pre><code>> load xyz<br>Error: company dataset not found!<br>> load Contoso<br>OK</code></pre>
    </div>

    <div style="font-size: 1.1em; font-family: Consolas, monospace;"><strong>&gt; employee</strong> <em>employeeId</em></div>
    <div style="margin-left: 2em;">
        Reports the total $-amount a specific employee was paid over all weeks of work. Example:
        <pre><code>> employee 4096<br> Total wages for employee 'Liam Carter': $16,307.57</code></pre>
    </div>

    <div style="font-size: 1.1em; font-family: Consolas, monospace;"><strong>&gt; paystub</strong> <em>employeeId</em> <em>weekId</em></div>
    <div style="margin-left: 2em;">
        Prints the paystub for a specific employee and a given week of work. Example:
        <pre><code>> paystub 7358 05/25/26
    ----- CONTOSO -----
    Name: SOFIA KIM
    ID: 7358
    Hourly Rate: 33.3
    Week 03/30/26:
        Hours worked: 30.00
        Pay: $999.00</code></pre>
    </div>

    <div style="font-size: 1.1em; font-family: Consolas, monospace;"><strong>&gt; quit</strong></div>
    <div style="margin-left: 2em;">
        Exits the command loop. Example:
        <pre><code>> quit
    Goodbye!</code></pre>
    </div>

- [ ] <u>Extra challenge:</u> add the following command to your program:

    <div style="font-size: 1.1em; font-family: Consolas, monospace;"><strong>&gt; week</strong> <em>week</em></div>
    <div style="margin-left: 2em;">
        Reports the total $-amount the company is paying to all its employees for a specific week of work, followed by the list of payments for each employee during that week. Example:
        <pre><code>> week 05/25/26
        ----- CONTOSO ----- Week: 05/25/26 ----- Payments: $12,171.80
        1047_OLIVIA REED, Hours_29.50, Pay_$790.60
        1512_JILL DOE, Hours_28.00, Pay_$851.20
        2279_ETHAN BROOKS, Hours_30.25, Pay_$949.85
        ...</code></pre>
    </div>

## Developer Notes
This lab includes several incomplete java classes, located in the `src` folder:
- [ ] [**Payroll.java**](https://florinteo.github.io/EduLabs/ap4~dts.Payroll/src/Payroll.java): contains the entry point in this program. The `main()` method is just a stub, you are expected to complete it by implementing the command loop. You may any helper methods you may need in this file.
- [ ] [**Company.java**](https://florinteo.github.io/EduLabs/ap4~dts.Payroll/src/Company.java): models a Company with all its content (employees, weeks of work, etc) and operations. This class contains stubs for the class constructor and the required features for this program:
  - `load()`: load the entire dataset from the disk, in response to the `> load ..` command,
  - `getEmployeeReport()`: generates the employee report, in response to the `> employee ..` command,
  - `getPaystub()`: generates the paystub for an employee and a week, in response to the `> paystub` command,
  - `getWeekReport()`: generates the week report, in response to the `> week ..` command.
  
  You may add additional methods to this class, as needed.
- [ ] [**Employee.java**](https://florinteo.github.io/EduLabs/ap4~dts.Payroll/src/Employee.java): models an Employee with all its content (id, weeks of work, etc) and operations. You are only provided with a stub for the class constructor. You need to provide its implementation as well as the design and code for additional methods you may find necessary.
- [ ] [**Week.java**](https://florinteo.github.io/EduLabs/ap4~dts.Payroll/src/Week.java): models a Week of work, with all its content (id, work hours, etc) and operations. You are only provided with a stub for the class constructor. You need to provide its implementation, as well as the design and code for additional methods you may find necessary.

## Hints and Tips ##

You may need a way to print currency values to the console in a cleaner way. For instance, a payment in the amount of 15232.76389 dollars (a double value) should be printed to the console as `$15,232.76`. Notice the rounding at two digits after the decimap point, the "`$`" currency prefix and the "`,`" thousands separator. The simplest way in Java to achieve this is demonstrated below:
```
double amount = 15232.76389;
System.out.printf("The amount is: $%,.2f\n", amount);
// This prints:
// The amomunt is $15,232.76
```
The [printf()](https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html#printf-java.lang.String-java.lang.Object...-) method is a powerful way to compose strings from multiple variables before printing the result to the console. Its power reside in the flexibility of its [format](https://www.geeksforgeeks.org/java/java-string-format-method-with-examples/#examples-of-string-format-method) parameter. In the example above, the sequence "`%,.2f`" will be replaced by the double value in the variable `amount`, with the following interpretation:
- value is a floating (or double) number (`f`)
- use thousands separator (`,`)
- use only two digits after the decimal point (`.2`)

Note that unlike `println()`, the method `printf()` does not add a line separator automatically. This needs to be added explicitly if needed (`\n`).