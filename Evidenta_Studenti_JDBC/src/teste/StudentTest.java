package teste;

import model.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

public class StudentTest {
    private Student student = null;

    @BeforeEach
    public void initTest() throws Exception{
        Student.stergereStudent("CTI024567");
        String[] prenume = new String[]{"Alin"};
        student = new Student("CTI024567", "Mihailescu", prenume, LocalDate.of(1994, 10, 11), LocalDate.of(2024, 07, 21));
        student.adaugareStudent();
    }


}
