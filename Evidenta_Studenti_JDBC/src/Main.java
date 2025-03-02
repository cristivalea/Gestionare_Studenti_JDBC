import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        Student.getStudenti();
        String[] prenume = new String[]{"Ionut", "Mihai"};
        Student s1 = new Student("CTI023431", "Paulescu", prenume, LocalDate.of(1992, 10, 11), LocalDate.of(2024, 07, 21));
        s1.adaugareStudent();
        Student.stergereStudent("CTI023431");
        Student.updateNumefamilie("Popescu", "CTI022106");
        Student.dataNastere(LocalDate.of(2002, 10, 02), "CTI022105");
    }
}
