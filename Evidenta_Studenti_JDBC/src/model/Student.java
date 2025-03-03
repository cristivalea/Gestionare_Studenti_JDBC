package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

public class Student {
    private String nrMatricol;
    private String numeFamilie;
    private String[] prenume;
    private LocalDate dataNAstere;
    private LocalDate dataInmatriculare;
    private int varsta;
    private String cale_poza;

    public Student(String nrMatricol, String numeFamilie, String[] prenume, LocalDate dataNAstere, LocalDate dataInmatriculare) {
        this.nrMatricol = nrMatricol;
        this.numeFamilie = numeFamilie;
        this.prenume = prenume;
        this.dataNAstere = dataNAstere;
        this.dataInmatriculare = dataInmatriculare;
    }

    public static ArrayList<Student> getStudenti(){
        ArrayList<Student> studenti = new ArrayList<Student>();
        try {
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rezultat = st.executeQuery("SELECT * FROM `studenti`");
             while (rezultat.next()){
                 String nrMat = rezultat.getString("Numar_Matricol");
                 //model.DBConnection.logger.info(nrMat);
                 String numeFam = rezultat.getString("Nume_Familie");
                 String[] prenume = rezultat.getString("Prenume").split(" ");
                 Date sqlDate = rezultat.getDate("Data_Nastere");
                 LocalDate dataN = sqlDate.toLocalDate();
                 sqlDate = rezultat.getDate("Data_Inmatriculare");
                 LocalDate dataI = sqlDate.toLocalDate();
                 Student student = new Student(nrMat, numeFam, prenume, dataN, dataI);
                 studenti.add(student);
             }
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
        return studenti;
    }

    @Override
    public String toString() {
        return "model.Student{" +
                "nrMatricol='" + nrMatricol + '\'' +
                ", numeFamilie='" + numeFamilie + '\'' +
                ", prenume=" + Arrays.toString(prenume) +
                ", dataNAstere=" + dataNAstere +
                ", dataInmatriculare=" + dataInmatriculare
                + "}";
    }

    public static void stergereStudent(String nrMatricol){
        String str = "DELETE FROM studenti WHERE Numar_Matricol=" + "'" + nrMatricol + "'";
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            sql.getSQLState();
        }
    }// stergere student

    public void adaugareStudent(){
        String str = "INSERT INTO studenti VALUES ('"+ this.nrMatricol +"','" + this.numeFamilie + "','";
        String prenumeStr = "";
        for(int i = 0 ; i < this.prenume.length; i++){
            prenumeStr += this.prenume[i];
            prenumeStr += " ";
        }
        str += prenumeStr + "','" + this.dataNAstere + "','" + this.dataInmatriculare + "')";
        try {
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end adaugare

    public static void updateNumefamilie(String numeNou, String numarMatricol){
        String str = "UPDATE studenti SET Nume_Familie=" + "'" + numeNou + "'" + "WHERE Numar_Matricol=" + "'" + numarMatricol + "'";
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    } // end update nume familie

    public static void dataNastere(LocalDate dataN, String nrMatricol){
        String str = "UPDATE studenti SET Data_Nastere=" + "'" + dataN + "'" + "WHERE Numar_Matricol=" + "'" + nrMatricol + "'";
        try{
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            st.execute(str);
        }catch (SQLException sql){
            DBConnection.logger.info(sql.getSQLState());
        }
    }// end update data nastere
}


