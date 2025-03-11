package teste;

import model.Nota;
import model.NotaNumerica;
import model.TipNota;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.ArrayList;

public class NotaNumericaTest {
    private NotaNumerica nota_numerica;

    @BeforeEach
    public void initTest(){
        NotaNumerica.stergeNota("CTI024873", 999);
        nota_numerica = new NotaNumerica(TipNota.N, "CTI024873", 999, LocalDate.of(2025, 01, 10), 9, 1);
        nota_numerica.adaugaNota();
    }

    @Test
    public void notaFinalaTest(){
        ArrayList<Nota> note = NotaNumerica.getNote();
        for(Nota n : note){
            if(n.getNumar_matricol() == "CTI024873"){
                assertEquals(9, nota_numerica);
            }
        }
    }

    @Test
    public void isPromovatTest(){
        ArrayList<Nota> n = NotaNumerica.getNote();
        for(Nota nota : n){
                assertEquals(1, nota.getPromovat());
        }
    }// end test isPromovat

    @Test
    public void updateTest(){
        ArrayList<Nota> n = NotaNumerica.getNote();
        NotaNumerica.updateNota(4, 999, "CTI024873");
        for(Nota nota : n){
            assertEquals(0, nota.getPromovat());
        }
    }// end update test
}
