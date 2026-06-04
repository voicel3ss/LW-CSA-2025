public class LegalSecretary extends Secretary{
    @Override
    public double getSalary() {
        return 45_000.0;
    }

    public void fileLegalBriefs(){
        System.out.println("Filing legal briefs.");
    }
}
