package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class NotaCalificativ extends Nota{
    private TipCalificativ calificativ;

    public NotaCalificativ(TipNota tip_nota, String numar_matricol, int cod_disciplina, LocalDate data_examen, int promovat, TipCalificativ calificativ) {
        super(tip_nota, numar_matricol, cod_disciplina, data_examen, promovat);
        this.calificativ = calificativ;
    }

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
                NotaCalificativ nota = new NotaCalificativ(TipNota.C, nrMat, codDsiciplina, data_exemen_final, promovat, cal);
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
}
