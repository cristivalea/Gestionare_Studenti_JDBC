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
       Disciplina.stergereDisciplina(999);
       disciplina = new Disciplina(999, "Disciplina test", 0.5, 0.4, 0.1, 0, 0.2, 0.3, 0.3, 0.2, 5);
       disciplina.adaugaDisciplina();
   }

   @Test
   public void codDisciplinTest(){
       ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
       boolean verificare = false;
       for(Disciplina d : discipline){
           if(d.getCodDisciplina() == 999){
               if(d.getCoefCurs() >= 0 && d.getCoefCurs() <= 1){
                   verificare = true;
                   break;
               }
           }
       }
       assertTrue(verificare, "Coeficeintul nu indeplineste comditia sa fie >= 0 si <= 1");
   }// end test cod Disciplina

   @Test
   public void numeDisciplinaTest() {
       ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
       boolean verificare = false;
       for(Disciplina d : discipline){
           if(d.getCodDisciplina() == 999){
               if(d.getNumedisciplina() != null){
                   verificare = true;
                   break;
               }
           }
       }
       assertTrue(verificare, "Numele disciplinei este null");
   } // end test numeDisciplina;

    @Test
    public void testCoefCurs(){
       ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
       boolean c1 = false;
       boolean c2 = false;
       boolean c3 = false;
       boolean c4 = false;
       boolean c5 = false;
       for(Disciplina d : discipline){
           if(d.getCodDisciplina() == 999){
               if(d.getCoefCurs() >= 0 && d.getCoefCurs() <= 1){
                   c1 = true;
               }
               if(d.getCoefLab() >= 0 && d.getCoefLab() <= 1){
                   c2 = true;
               }
               if(d.getCoefProiect() >= 0 && d.getCoefProiect() <= 1){
                   c3 = true;
               }
               if(d.getCoefSeminar() >= 0 && d.getCoefSeminar() <= 1){
                   c4 = true;
               }
               if((d.getCoefCurs() + d.getCoefLab() + d.getCoefProiect() + d.getCoefSeminar()) == 1){
                   c5 = true;
               }
           }
       }
       assertTrue(c1, "Coeficient curs incorect");
       assertTrue(c2, "Coeficieent Laborator incorect");
       assertTrue(c3, "Coeficeint Proiect incorect");
       assertTrue(c4, "Coeficient Seminar incorect");
       assertTrue(c5, "Suma coeficientulor nu depaseste 1");
    } // end testare coeficienti

    @Test
    public void numarCrediteTest(){
       ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
       boolean verificare = false;
       for(Disciplina d : discipline){
           if(d.getCodDisciplina() == 999){
               if(d.getNumarCredite() > 0 && d.getNumarCredite() <= 10){
                   verificare = true;
                   break;
               }
           }
       }
       assertTrue(verificare, "Numarul de credite este in exteriorul intervalui [1, 10]");
    } // end test numar credite

    @Test
    public void coeficientiPrezentaTest(){
        ArrayList<Disciplina> discipline = Disciplina.getDiscipline();
        boolean c1 = false;
        boolean c2 = false;
        boolean c3 = false;
        boolean c4 = false;
        for(Disciplina d : discipline){
            if(d.getCodDisciplina() == 999){
                if(d.getCoefPrezentaCurs() >= 0 && d.getCoefPrezentaCurs() <= 1){
                    c1 = true;
                }
                if(d.getCoefPrezentaLab() >= 0 && d.getCoefPrezentaLab() <= 1){
                    c2 = true;
                }
                if(d.getCoefPrezentaProiect() >= 0 && d.getCoefPrezentaProiect() <= 1){
                    c3 = true;
                }
                if(d.getCoefPrezentaSeminar() >= 0 && d.getCoefPrezentaSeminar() <= 1){
                    c4 = true;
                }
            }
        }
        assertTrue(c1, "Coeficient prezenta curs incorect");
        assertTrue(c2, "Coeficieent prezenta Laborator incorect");
        assertTrue(c3, "Coeficeint prezenta Proiect incorect");
        assertTrue(c4, "Coeficient prezenta Seminar incorect");
    } // end test coef prezenta
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
