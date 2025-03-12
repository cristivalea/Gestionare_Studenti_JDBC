package teste;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NotaCalificativTest {
    private NotaCalificativ nota_c;

    @BeforeEach
    public void initTest(){
        NotaCalificativ.stergereNota(10, "CTI024873");
        nota_c = new NotaCalificativ(TipNota.C, "CTI024873", 108, LocalDate.of(2024, 12, 13), TipCalificativ.INSUFICIENT, 0);
        nota_c.adaugaNota();
    }

    @Test
    public void testTipCalificativ(){
        boolean verificare = false;
        boolean c1 = nota_c.getCalificativ().equals(TipCalificativ.INSUFICIENT.getDenumire());
        boolean c2 = nota_c.getCalificativ().equals(TipCalificativ.SUFICIENT.getDenumire());
        boolean c3 = nota_c.getCalificativ().equals(TipCalificativ.BINE.getDenumire());
        boolean c4 = nota_c.getCalificativ().equals(TipCalificativ.FOARTE_BINE.getDenumire());
        boolean c5 = nota_c.getCalificativ().equals(TipCalificativ.EXCELENT.getDenumire());
        if(c1 || c2 || c3 || c4 || c5){
            verificare = true;
        }
        assertTrue(verificare, "Califcativ necorespunzator");
    } // end test tip calificativ

    @Test
    public void getNoteCalificativTest(){
        ArrayList<Nota> note = NotaCalificativ.getNoteCalificativ();
        boolean verificare= false;
        for(Nota n : note){
            if(n.getCod_disciplina() == 108){
                verificare = true;
                break;
            }
        }
        assertTrue(verificare, "Nota nu a fost gasita");
    } // end test incarcare note calificativ

    @Test
    public void isPromovatTest(){
        assertEquals(0, nota_c.getPromovat());
    }

    @Test
    public void updateNotaTest(){
        NotaCalificativ.updateNota(TipCalificativ.SUFICIENT, 108, "CTI024873");
        boolean verificare = false;
        ArrayList<Nota> note = NotaCalificativ.getNoteCalificativ();
        for(Nota n : note){
            if(n.getCod_disciplina() == 108) {
                if (n.getPromovat() == 1) {
                    verificare = true;
                }
            }
        }
        assertTrue(verificare, "Nota nu a fost modificata");
    } // end test update nota
}
