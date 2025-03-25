package model;

import org.apache.poi.ss.formula.functions.T;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class NotaCalificativ extends Nota{
    private TipCalificativ calificativ;
    public TipCalificativ valoarea = TipCalificativ.SUFICIENT;

    public NotaCalificativ(TipNota tip_nota, String numar_matricol, int cod_disciplina, LocalDate data_examen, TipCalificativ calificativ, int promovat) {
        super(tip_nota, numar_matricol, cod_disciplina, data_examen, promovat);
        this.calificativ = calificativ;
    };


        public  static ArrayList<Nota> getNoteCalificativ(){
        ArrayList<Nota> noteNumerice = new ArrayList<Nota>();
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rezultat = st.executeQuery("SELECT * FROM note WHERE Tip_nota='C'");
            while (rezultat.next()){
                String nrMat = rezultat.getString("Numar_matricol_Student");
                int codDsiciplina = rezultat.getInt("Cod_Disciplina_Nota");
                Date dataExamen = rezultat.getDate("Data_Examen");
                LocalDate data_exemen_final = ((java.sql.Date) dataExamen).toLocalDate();
                String nota_finala = rezultat.getString("Valoare_Nota");
                TipCalificativ cal = TipCalificativ.INSUFICIENT;
                if (nota_finala != null) {
                    nota_finala = nota_finala.trim();  // Elimină spațiile nedorite

                    for (TipCalificativ tip : TipCalificativ.values()) {
                        if (nota_finala.equalsIgnoreCase(tip.getDenumire())) {
                            cal = tip;
                            break;
                        }
                    }
                }

                int promovat = rezultat.getInt("Promovat");
                NotaCalificativ nota = new NotaCalificativ(TipNota.C, nrMat, codDsiciplina, data_exemen_final, cal, promovat);
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

    public TipCalificativ getNotaFinala(){
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

    @Override
    public void adaugaNota() {
        String str = "INSERT INTO note (Tip_nota, Numar_Matricol_Student, Cod_Disciplina_Nota, Data_Examen, Valoare_Nota, Promovat)"
                + "VALUES ('" + TipNota.C + "','"  + this.numar_matricol + "'," + this.cod_disciplina + ",'" + this.data_examen + "','" + this.calificativ + "'," + this.promovat + ")";
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

    public static void updateNota( TipCalificativ tc, int codDisciplina,String codStudent){
        int promovat = 0;
        boolean c1 = tc.getDenumire().equals(TipCalificativ.EXCELENT.getDenumire());
        boolean c2 = tc.getDenumire().equals(TipCalificativ.FOARTE_BINE.getDenumire());
        boolean c3 = tc.getDenumire().equals(TipCalificativ.BINE.getDenumire());
        boolean c4 = tc.getDenumire().equals(TipCalificativ.SUFICIENT.getDenumire());
        if(c1 || c2 || c3 || c4){
            promovat = 1;
        }
        String str = "UPDATE note SET Valoare_Nota='" + tc + "', Promovat=" + promovat +" WHERE Numar_Matricol_Student='" + codStudent + "' AND Cod_Disciplina_Nota=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    } // end update nota calificativ

    public String getCalificativ() {
        return calificativ.getDenumire();
    }

    public void setNotaFinala(){
        this.valoarea = calificativ;
    }
}
