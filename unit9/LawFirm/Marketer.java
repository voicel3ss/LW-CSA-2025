public class Marketer extends Employee{
    @Override
    public double getSalary() {
        return 50000.0;
    }

    public void advertise(){
        System.out.println("Buy our product, it's great!");
    }
}
