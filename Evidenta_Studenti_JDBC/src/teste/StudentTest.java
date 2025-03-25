package teste;

import exceptii.FormatException;
import model.RegularExpresion;
import model.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class StudentTest {
    private Student student = null;

    @BeforeEach
    public void initTest() throws Exception{
        Student.stergereStudent("CTI024806");
        String[] prenume = new String[]{"Ion"};
        student = new Student("CTI024806", "Mihailescu", prenume, LocalDate.of(1994, 10, 11), LocalDate.of(2024, 7, 21));
        student.adaugareStudent();
    }

    @Test
    public void formatNrMatricolTest() throws Exception{
        boolean verificare1 = false;
        if(student.getNrMatricol() != null){
            verificare1 = true;
        }
        boolean verificare2 = false;
        if(RegularExpresion.RegularExpresionNrMatricol(student.getNrMatricol())){
            verificare2 = true;
        }
        boolean verificare = false;
        if(verificare1 && verificare2){
            verificare = true;
        }
        assertTrue(verificare, "Numarul matricol nu indeplineste conditiile");
    } // end test numar matricol;

    @Test
    public void numeFamilieTest(){
        boolean verificare = false;
        if(RegularExpresion.RegularExpresionNumePrenume(student.getNumeFamilie())){
            verificare = true;
        }
        assertTrue(verificare, "Numele de familie nu are formatul corespunzatro");
    } // end test format nume de familie

    @Test
    public void prenumeTest(){
        String[] prenume = student.getPrenume();
        boolean verificare = false;
        for(String p : prenume){
            if(RegularExpresion.RegularExpresionNumePrenume(p)){
                verificare = true;
                assertTrue(verificare, "Prenumele nu are formatul corespunzator");
            }
        }
    } // end test format prenume

    @Test
    public void varstaTest(){
        boolean verificare = false;
        if(student.getVarsta() >= 18){
            verificare = true;
        }
        assertTrue(verificare, "Varsta este sub 18 ani");
    } // end test varsta student
    @Test
    public void calePozaTest(){
        boolean verificqre = false;
        if(student.getCale_poza() != null){
            verificqre = true;
        }
        assertTrue(verificqre, "Atribut null");
    } // end test cale poza;

    @Test
    public void arrayNoteTest(){
        boolean verificare = false;
        if(!student.getNote().isEmpty()){
            verificare = true;
        }
        assertTrue(verificare, "Studentul nu are note");
    } // end test array note

    @Test
    public void getStudentiTest() throws Exception{
        ArrayList<Student> studenti = Student.getStudenti();
        for(Student s : studenti){
            System.out.println(s);
        }
        boolean verificare = false;
        for(Student s : studenti){
            if(s.getNrMatricol().equals("CTI022106")){
                verificare = true;
            }
        }
        assertTrue(verificare, "Studentul nu a fost gasit");
    } // end test incarcare studenti


    @Test
    public void updateTest() throws Exception{
        Student.updateNumefamilie("Pop", "CTI024805");
        ArrayList<Student> studenti = Student.getStudenti();
        boolean verificare = false;
        for(Student s : studenti){
            if(s.getNrMatricol().equals("CTI024805")){
                verificare = true;
            }
        }
        assertTrue(verificare, "Nu s-a modificat numele");
    } // end test update nume student

    @Test
    public void updateDataNastereTest() throws Exception{
        Student.dataNastere(LocalDate.of(1996, 12, 12), "CTI024806");
        ArrayList<Student> studenti = Student.getStudenti();
        boolean verificare = false;
        for(Student s : studenti){
            if(s.getNrMatricol().equals(student.getNrMatricol())){
                verificare = true;
            }
        }
        assertTrue(verificare, "Nu s-a modificat data nasterii");
    } // end test update data nastere
}
