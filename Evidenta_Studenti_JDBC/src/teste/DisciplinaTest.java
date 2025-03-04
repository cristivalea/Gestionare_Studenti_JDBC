package teste;

import model.Disciplina;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DisciplinaTest {
   private Disciplina disciplina;

   @BeforeEach
    public void initTest(){
       disciplina = new Disciplina(999, "Disciplina test", 0.5, 0.4, 0.1, 0, 0.2, 0.3, 0.3, 0.2, 5);
       disciplina.adaugaDisciplina();
   }

   @Test
    public void getDisciplineTest(){
       ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
       boolean verificare = false;
       for(Disciplina d : discipline){
           if(d.getCodDisciplina() == 999){
               verificare = true;
               break;
           }
       }
       assertTrue(verificare, "Disciplina nu a fost gasita in baza de date");
   }// end getDisciplineTest

    @Test
    public void modificareCoeficientCursTest(){
        Disciplina.modificareCoeficientCurs(0.4, 999);
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        for (Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                assertEquals(0.4, d.getCoefCurs(), 0.01);
                break;
            }
        }
    } // end modificareCoeficientCursTest

    @Test
    public void modificareCoeficientLabTest(){
        Disciplina.modificareCoeficientLab(0.5, 999);
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        for (Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                assertEquals(0.5, d.getCoefLab(), 0.01);
                break;
            }
        }
    } // end modificareCoeficientLab

    @Test
    public void modificareCoeficientProiectTest(){
        Disciplina.modificareCoeficientProiect(0.05, 999);
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        for (Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                assertEquals(0.05, d.getCoefProiect(), 0.01);
                break;
            }
        }
    } // end modificareCoeficientProiectTest


    @Test
    public void modificareCoeficientSeminarTest(){
        Disciplina.modificareCoeficientSeminar(0.05, 999);
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        for (Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                assertEquals(0.05, d.getCoefSeminar(), 0.01);
                break;
            }
        }
    } // end modificareCoeficientSeminarTest

    @Test
    public void modificareCoeficientPrezCurstTest(){
        Disciplina.modificareCoeficientPrezentaCurs(0.5, 999);
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        for (Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                assertEquals(0.5, d.getCoefPrezentaCurs(), 0.01);
                break;
            }
        }
    } // end modificareCoeficientPrezCursTest

    @Test
    public void modificareCoeficientPrezLabTest(){
        Disciplina.modificareCoeficientPrezentaLab(0.3, 999);
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        for (Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                assertEquals(0.3, d.getCoefPrezentaLab(), 0.01);
                break;
            }
        }
    } // end modificareCoeficientPrezLabTest

    @Test
    public void modificareCoeficientPrezProiectTest(){
        Disciplina.modificareCoeficientPrezentaProiect(0.2, 999);
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        for (Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                assertEquals(0.2, d.getCoefPrezentaProiect(), 0.01);
                break;
            }
        }
    } // end modificareCoeficientPrezProiectTest

    @Test
    public void modificareCoeficientPrezSeminarTest(){
        Disciplina.modificareCoeficientPrezentaSeminar(0, 999);
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        for (Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                assertEquals(0, d.getCoefPrezentaSeminar(), 0.01);
                break;
            }
        }
    } // end modificareCoeficientPrezSeminarTest

    @Test
    public void modificareNumarCrediteTest(){
        Disciplina.modificareNrCredite(6, 999);
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        for (Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                assertEquals(6, d.getNumarCredite(), 0.01);
                break;
            }
        }
    } // end modificareNumarCrediteTest

    @Test
    public void stergeDisciplinaTest(){
       Disciplina.stergereDisciplina(999);
       ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
       boolean verificare = false;
       for(Disciplina d : discipline){
           if(d.getCodDisciplina() == 999){
               verificare = true;
               break;
           }
       }
       assertFalse(verificare, "Disciplina nu a fost stearsa");
    }
}
