public class Lawyer extends Employee{
    @Override
    public String getVacationForm() {
        return "pink";
    }

    @Override
    public double getVacationDays() {
        return 15.0;
    }

    public void sue(){
        System.out.println("I'll see you in court!");
    }
}
