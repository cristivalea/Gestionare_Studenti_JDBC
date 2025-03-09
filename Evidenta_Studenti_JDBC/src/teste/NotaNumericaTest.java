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
        nota_numerica = new NotaNumerica(TipNota.N, "CTI024873", 100, LocalDate.of(2025, 01, 10), 1, 9);
    }

    @Test
    void notaFinalaTest(){
        ArrayList<Nota> note = NotaNumerica.getNote();
        for(Nota n : note){
            if(n.getNumar_matricol() == "CTI024873"){
                assertEquals(9, nota_numerica);
            }
        }
    }
}
