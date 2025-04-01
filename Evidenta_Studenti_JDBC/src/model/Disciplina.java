package model;

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

    private FisaDisciplinei fisaDisciplinei;

    public Disciplina(int codDisciplina, String numedisciplina, double coefCurs, double coefLab, double coefProiect, double coefSeminar, double coefPrezentaCurs, double coefPrezentaLab, double coefPrezentaProiect, double coefPrezentaSeminar, int numarCredite) {
        if(codDisciplina >= 100 || codDisciplina < 1000) {
            this.codDisciplina = codDisciplina;
        }
        if(numedisciplina != null) {
            this.numedisciplina = numedisciplina;
        }
        boolean c1 = coefCurs >= 0 && coefCurs <= 1;
        boolean c2 = coefLab >= 0 && coefLab <= 1;
        boolean c3 = coefProiect >= 0 && coefProiect <= 1;
        boolean c4 = coefSeminar >= 0 && coefSeminar <= 1;
        boolean c5 = (coefCurs + coefLab + coefProiect + coefSeminar) == 1;
        if(c1 && c5) {
            this.coefCurs = coefCurs;
        }
        if(c2 && c5) {
            this.coefLab = coefLab;
        }
        if(c3 && c5) {
            this.coefProiect = coefProiect;
        }
        if(c4 && c5) {
            this.coefSeminar = coefSeminar;
        }
        if(coefPrezentaCurs >= 0 && coefPrezentaCurs <=1) {
            this.coefPrezentaCurs = coefPrezentaCurs;
        }
        if(coefPrezentaLab >= 0 && coefPrezentaLab <= 1) {
            this.coefPrezentaLab = coefPrezentaLab;
        }
        if(coefPrezentaProiect >= 0 && coefProiect <= 1) {
            this.coefPrezentaProiect = coefPrezentaProiect;
        }
        if(coefPrezentaSeminar >= 0 && coefPrezentaSeminar <= 1) {
            this.coefPrezentaSeminar = coefPrezentaSeminar;
        }
        if(numarCredite > 0 && numarCredite <= 10) {
            this.numarCredite = numarCredite;
        }
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
                ((this.codDisciplina > 0) ? "codDisciplina=" + codDisciplina : "")
                + ((this.numedisciplina != null) ? ", numedisciplina='" + numedisciplina + '\'': "") +
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

    public int getCodDisciplina() {
        return codDisciplina;
    }

    public String getNumedisciplina() {
        return numedisciplina;
    }

    public double getCoefCurs() {
        return coefCurs;
    }

    public double getCoefLab() {
        return coefLab;
    }

    public double getCoefProiect() {
        return coefProiect;
    }

    public double getCoefSeminar() {
        return coefSeminar;
    }

    public double getCoefPrezentaCurs() {
        return coefPrezentaCurs;
    }

    public double getCoefPrezentaLab() {
        return coefPrezentaLab;
    }

    public double getCoefPrezentaProiect() {
        return coefPrezentaProiect;
    }

    public double getCoefPrezentaSeminar() {
        return coefPrezentaSeminar;
    }

    public int getNumarCredite() {
        return numarCredite;
    }

    public void setFisaDisciplinei(FisaDisciplinei fisaDisciplinei) {
        System.out.println("S-a setat disciplina");
        this.fisaDisciplinei = fisaDisciplinei;
    }
}
