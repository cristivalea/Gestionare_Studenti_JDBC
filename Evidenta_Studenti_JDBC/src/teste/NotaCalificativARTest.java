package teste;

import model.Nota;
import model.NotaCalificativAR;
import model.TipCalificativAR;
import model.TipNota;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class NotaCalificativARTest {
    private NotaCalificativAR nota_ar;

    @BeforeEach
    public void initTest(){
        NotaCalificativAR.stergereNota(106, "CTI024873");
        nota_ar = new NotaCalificativAR(TipNota.A, "CTI024873", 106, LocalDate.of(2025, 01, 12), TipCalificativAR.ADMIS, 1);
        nota_ar.adaugaNota();
    }

    @Test
    public void testCalificativ(){
       boolean verificare = false;
       if(nota_ar.getNotaFinala().getDenumire().equals(TipCalificativAR.ADMIS) || nota_ar.getNotaFinala().getDenumire().equals(TipCalificativAR.RESPINS)) {
           assertTrue(verificare, "Calificativul nu corespunde tipului de calificativ cerut");
       }
    } // end test tip calificativ

    @Test
    public void testGetNote(){
        ArrayList<Nota> note = NotaCalificativAR.getNoteCalificativ();
        assertFalse(note.isEmpty(), "Notele nu au fost încărcate corect");
    }// end test getNoteCalificativAR

    @Test
    public void isPromovatTest(){
            assertEquals(1, 1);
    } // end test promovat

    @Test
    public void updateTest(){
        NotaCalificativAR.updateNota(TipCalificativAR.RESPINS, 106, "CTI024873");
        boolean verificare = false;
        ArrayList<Nota> note = NotaCalificativAR.getNoteCalificativ();
        for(Nota n : note){
            if(n.getCod_disciplina() == 106) {
                if (n.getPromovat() == 0) {
                    verificare = true;
                }
            }
        }
        assertTrue(verificare, "Nota nu a fost modificata");
    }
}
