public class Employee {
    public int getHours(){
        return 40;
    }

    public double getSalary(){
        return 40000.0;
    }

    public double getVacationDays(){
        return 10.0;
    }

    public String getVacationForm(){
        return "yellow";
    }

    @Override
    public String toString() {
        return "Hours: " + getHours() +
                " Salary: " + getSalary() + 
                " Vacation Days: " + getVacationDays() +
                " Vacation Form: " + getVacationForm();
    }
}