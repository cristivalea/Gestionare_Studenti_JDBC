package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class NotaCalificativAR extends Nota{

    private TipCalificativAR calificativ;

    public NotaCalificativAR(TipNota tip_nota, String numar_matricol, int cod_disciplina, LocalDate data_examen, TipCalificativAR calificativ, int promovat) {
        super(tip_nota, numar_matricol, cod_disciplina, data_examen, promovat);
        this.calificativ = calificativ;
    }

    public  static ArrayList<Nota> getNoteCalificativ(){
        ArrayList<Nota> noteNumerice = new ArrayList<Nota>();
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rezultat = st.executeQuery("SELECT * FROM note WHERE Tip_nota='A'");
            while (rezultat.next()){
                String nrMat = rezultat.getString("Numar_matricol_Student");
                int codDsiciplina = rezultat.getInt("Cod_Disciplina_Nota");
                Date dataExamen = rezultat.getDate("Data_Examen");
                LocalDate data_exemen_final = ((java.sql.Date) dataExamen).toLocalDate();
                String nota_finala =  rezultat.getString("Valoare_Nota");
                TipCalificativAR cal = TipCalificativAR.ADMIS;
                if(nota_finala.equals(TipCalificativAR.RESPINS.getDenumire())){
                    cal = TipCalificativAR.RESPINS;
                } else if (nota_finala.equals(TipCalificativAR.ADMIS.getDenumire())) {
                    cal = TipCalificativAR.ADMIS;
                }
                int promovat = rezultat.getInt("Promovat");
                NotaCalificativAR nota = new NotaCalificativAR(TipNota.A, nrMat, codDsiciplina, data_exemen_final, cal, promovat);
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

    public TipCalificativAR getNotaFinala(){
        return calificativ;
    }
    @Override
    public String toString() {
        return "NotaCalificativ{" +
                "calificativ=" + calificativ +
                ", tip_nota=" + tip_nota +
                ", numar_matricol='" + numar_matricol + '\'' +
                ", cod_disciplina=" + cod_disciplina +
                ", data_examen=" + data_examen +
                ", promovat=" + promovat +
                '}';
    }

    public void adaugaNota() {
        String str = "INSERT INTO note (Tip_nota, Numar_Matricol_Student, Cod_Disciplina_Nota, Data_Examen, Valoare_Nota, Promovat)"
                + "VALUES ('" + TipNota.A + "','"  + this.numar_matricol + "'," + this.cod_disciplina + ",'" + this.data_examen + "','" + this.calificativ + "'," + this.promovat + ")";
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    } // end adaugare nota calificativ

    public static void stergereNota(int codDisciplina, String nrMatricol){
        String str = "DELETE FROM note WHERE Cod_Disciplina_Nota=" + codDisciplina + " AND Numar_Matricol_Student='" + nrMatricol + "'";
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    } // end stergere nota calificativ

    public static void updateNota(TipCalificativAR tc, int codDisciplina,String codStudent){
        int promovat = 0;
        if(tc.getDenumire().equals(TipCalificativAR.ADMIS)){
            promovat = 1;
        }
        String str = "UPDATE note SET Valoare_Nota='" + tc + "', Promovat= " + promovat + " WHERE Numar_Matricol_Student='" + codStudent + "' AND Cod_Disciplina_Nota=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    } // end update nota calificativ

    public TipCalificativAR getCalificativ() {
        return calificativ;
    }
}
