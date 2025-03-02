import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Disciplina {
    private int codDisciplina;
    private String numedisciplina;
    private double coefCurs;
    private double coefLab;
    private double coefProiect;
    private double coefSeminar;
    private double coefPrezentaCurs;
    private double coefPrezentaLab;
    private double coefPrezentaProiect;
    private double coefPrezentaSeminar;
    int numarCredite;

    public Disciplina(int codDisciplina, String numedisciplina, double coefCurs, double coefLab, double coefProiect, double coefSeminar, double coefPrezentaCurs, double coefPrezentaLab, double coefPrezentaProiect, double coefPrezentaSeminar, int numarCredite) {
        this.codDisciplina = codDisciplina;
        this.numedisciplina = numedisciplina;
        this.coefCurs = coefCurs;
        this.coefLab = coefLab;
        this.coefProiect = coefProiect;
        this.coefSeminar = coefSeminar;
        this.coefPrezentaCurs = coefPrezentaCurs;
        this.coefPrezentaLab = coefPrezentaLab;
        this.coefPrezentaProiect = coefPrezentaProiect;
        this.coefPrezentaSeminar = coefPrezentaSeminar;
        this.numarCredite = numarCredite;
    }

    public static ArrayList<Disciplina> getDiscipline(){
        ArrayList<Disciplina> discipline = new ArrayList<Disciplina>();
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rezultat = st.executeQuery("SELECT * FROM discipline");
            while(rezultat.next()){
                int codD = rezultat.getInt("Cod_Disciplina");
                String numeD = rezultat.getString("Nume_disciplina");
                double coefCurs = rezultat.getDouble("Coeficient_Curs");
                double coefLab = rezultat.getDouble("Coeficient_Laborator");
                double coefProiect = rezultat.getDouble("Coeficient_Proiect");
                double coefSeminar = rezultat.getDouble("Coeficient_Seminar");
                double coefPrezCurs = rezultat.getDouble("Coeficient_Prezenta_Curs");
                double coefPrezentaLab = rezultat.getDouble("Coeficient_Prezenta_Laborator");
                double coefPrezentaProiect = rezultat.getDouble("Coeficient_Prezenta_Proiect");
                double coefPrezentaSeminar = rezultat.getDouble("Coeficient_Prezenta_Seminar");
                int numarCredite = rezultat.getInt("Numar_Credite");
                Disciplina disciplina = new Disciplina(codD, numeD, coefCurs, coefLab, coefProiect, coefSeminar, coefPrezCurs, coefPrezentaLab, coefPrezentaProiect, coefPrezentaSeminar, numarCredite);
                discipline.add(disciplina);

            }
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
        return  discipline;
    } // end load discipline

    @Override
    public String toString() {
        return "Disciplina{" +
                "codDisciplina=" + codDisciplina +
                ", numedisciplina='" + numedisciplina + '\'' +
                ", coefCurs=" + coefCurs +
                ", coefLab=" + coefLab +
                ", coefProiect=" + coefProiect +
                ", coefSeminar=" + coefSeminar +
                ", coefPrezentaCurs=" + coefPrezentaCurs +
                ", coefPrezentaLab=" + coefPrezentaLab +
                ", coefPrezentaProiect=" + coefPrezentaProiect +
                ", coefPrezentaSeminar=" + coefPrezentaSeminar +
                ", numarCredite=" + numarCredite +
                '}';
    } // end toString

    public void adaugaDisciplina(){
        String str = "INSERT into discipline VALUES ('" + this.codDisciplina + "','"
                + this.numedisciplina + "','" + this.coefCurs + "','" + this.coefLab + "','"
                + this.coefProiect + "','" + this.coefSeminar + "','" + this.coefPrezentaCurs + "','"
                + this.coefPrezentaLab + "','" + this.coefPrezentaProiect + "','"
                + this.coefPrezentaSeminar + "','" + this.numarCredite + "')";
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end adaugare disciplina

    public static void stergereDisciplina(int codDisciplina){
        String str = "DELETE FROM discipline WHERE Cod_Disciplina=" + codDisciplina;
        try {
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    } // end stergere disciplina

    // Modificare coeficienti
    public static void modificareCoeficientCurs(double coefCursNou, int codDisciplina){
        String str = "UPDATE discipline SET Coeficient_Curs=" + coefCursNou + "WHERE Cod_Disciplina=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end modificare coef curs

    public static void modificareCoeficientLab(double coefLabNou, int codDisciplina){
        String str = "UPDATE discipline SET Coeficient_Laborator=" + coefLabNou + "WHERE Cod_Disciplina=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end modificare coef lab

    public static void modificareCoeficientProiect(double coefProiectNou, int codDisciplina){
        String str = "UPDATE discipline SET Coeficient_Proiect=" + coefProiectNou + "WHERE Cod_Disciplina=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end modificare coef proiect

    public static void modificareCoeficientSeminar(double coefSeminarNou, int codDisciplina){
        String str = "UPDATE discipline SET Coeficient_Seminar=" + coefSeminarNou + "WHERE Cod_Disciplina=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end modificare coef seminar

    public static void modificareCoeficientPrezentaCurs(double coefPrezCursNou, int codDisciplina){
        String str = "UPDATE discipline SET Coeficient_Prezenta_Curs=" + coefPrezCursNou + "WHERE Cod_Disciplina=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end modificare coef prezenta curs

    public static void modificareCoeficientPrezentaLab(double coefPrezLabNou, int codDisciplina){
        String str = "UPDATE discipline SET Coeficient_Prezenta_Laborator=" + coefPrezLabNou + "WHERE Cod_Disciplina=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end modificare coef prezenta laborator

    public static void modificareCoeficientPrezentaProiect(double coefPrezProiectNou, int codDisciplina){
        String str = "UPDATE discipline SET Coeficient_Prezenta_Proiect=" + coefPrezProiectNou + "WHERE Cod_Disciplina=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end modificare coef prezenta proiect

    public static void modificareCoeficientPrezentaSeminar(double coefPrezSeminarNou, int codDisciplina){
        String str = "UPDATE discipline SET Coeficient_Prezenta_Seminar=" + coefPrezSeminarNou + "WHERE Cod_Disciplina=" + codDisciplina;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end modificare coef prezenta seminar

    public static void modificareNrCredite(int nrCrediteNou, int codDisciplina1){
        String str = "UPDATE discipline SET Numar_Credite=" + nrCrediteNou + " WHERE Cod_Disciplina=" + codDisciplina1;
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end modificare numar credite
}
