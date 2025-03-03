package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class NotaNumerica extends Nota{

    private int notaFinala;

    public NotaNumerica(TipNota tip_nota, String numar_matricol, int cod_disciplina, LocalDate data_examen, int promovat, int notaFinala) {
        super(tip_nota, numar_matricol, cod_disciplina, data_examen, promovat);
        this.notaFinala = notaFinala;
    }

    public  static ArrayList<Nota> getNote(){
        ArrayList<Nota> noteNumerice = new ArrayList<Nota>();
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rezultat = st.executeQuery("SELECT * FROM note WHERE Tip_nota='N'");
            while (rezultat.next()){
                String nrMat = rezultat.getString("Numar_matricol_Student");
                int codDsiciplina = rezultat.getInt("Cod_Disciplina_Nota");
                Date dataExamen = rezultat.getDate("Data_Examen");
                LocalDate data_exemen_final = ((java.sql.Date) dataExamen).toLocalDate();
                int notaFinala = rezultat.getInt("Valoare_Nota");
                int promovat = rezultat.getInt("Promovat");
                NotaNumerica nota = new NotaNumerica(TipNota.N, nrMat, codDsiciplina, data_exemen_final, promovat, notaFinala);
                noteNumerice.add(nota);
            }
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
        return noteNumerice;
    }

    public  boolean isPromovat(){
        if(promovat == 1){
            return true;
        }
        return false;
    }

    public int getNotaFinala(){
        return notaFinala;
    }


    @Override
    public String toString() {
        return "NotaNumerica{" +
                "notaFinala=" + notaFinala +
                ", tip_nota=" + tip_nota +
                ", numar_matricol='" + numar_matricol + '\'' +
                ", cod_disciplina=" + cod_disciplina +
                ", data_examen=" + data_examen +
                ", promovat=" + promovat +
                '}';
    } // end toString

    public void setNotaFinala(int notaFinala) {
        this.notaFinala = notaFinala;
    }
}
