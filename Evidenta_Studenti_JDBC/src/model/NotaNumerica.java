package model;


import com.sun.java.swing.plaf.windows.WindowsTabbedPaneUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class NotaNumerica extends Nota{

    private int notaFinala;
    public int valoare;

    public NotaNumerica(TipNota tip_nota, String numar_matricol, int cod_disciplina, LocalDate data_examen, int notaFinala, int promovat) {
        super(tip_nota, numar_matricol, cod_disciplina, data_examen, promovat);
        this.notaFinala = notaFinala;
    }



    public static ArrayList<Nota> getNote(){
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
                NotaNumerica nota = new NotaNumerica(TipNota.N, nrMat, codDsiciplina, data_exemen_final, notaFinala, promovat);
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
    }

    public void adaugaNota(){
        String str = "INSERT INTO note (Tip_nota, Numar_Matricol_Student, Cod_Disciplina_Nota, Data_Examen, Valoare_Nota, Promovat)"
        + "VALUES ('" + TipNota.N + "','"  + this.numar_matricol + "'," + this.cod_disciplina + ",'" + this.data_examen + "'," + this.notaFinala + "," + this.promovat + ")";
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    } // end adaugare nota numerica

    public static void stergeNota(String codMatricol, int codDisciplina){
        String str = "DELETE FROM note WHERE Cod_Disciplina_Nota=" + codDisciplina + " AND Numar_Matricol_Student='" + codMatricol + "'";
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end stergere nota

    public static void updateNota(int notaNoua, int codDisciplina,String codStudent){
        int promovat = 0;
        if(notaNoua > 5){
            promovat = 1;
        }
        String str = "UPDATE note SET Valoare_Nota=" + notaNoua + ", Promovat=" + promovat + " WHERE Numar_Matricol_Student='" + codStudent + "' AND Cod_Disciplina_Nota=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    } // end update nota

    public void setNotaFinala(){
        this.valoare = notaFinala;
    }
}
