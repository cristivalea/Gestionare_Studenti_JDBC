package model;

import exceptii.FormatException;
import exceptii.StudentNeadecvat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
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
    private ArrayList<Nota> note = new ArrayList<Nota>();
    public static ArrayList<String> listaNrMatricole = new ArrayList<String>();

    public Student(String nrMatricol, String numeFamilie, String[] prenume, LocalDate dataNAstere, LocalDate dataInmatriculare) throws Exception{
        if(nrMatricol == null || numeFamilie == null || prenume.length == 0 || dataNAstere == null || dataInmatriculare == null){
            return;
        }
        if(RegularExpresion.RegularExpresionNrMatricol(nrMatricol) == false){
            FormatException fnm = new FormatException(nrMatricol,FormatException.NR_MATRICOL_FORMAT);
            throw fnm;
        }
        this.nrMatricol = nrMatricol;
        listaNrMatricole.add(this.nrMatricol);
        if(!RegularExpresion.RegularExpresionNumePrenume(numeFamilie)){
            FormatException fnf = new FormatException(numeFamilie,FormatException.NUME_PRENUME_FORMAT);
            throw fnf;
        }
        this.numeFamilie = numeFamilie;
        for(int i = 0; i < prenume.length; i++){
            if(!RegularExpresion.RegularExpresionNumePrenume(prenume[i])){
                FormatException fnf = new FormatException(prenume[i],FormatException.NUME_PRENUME_FORMAT);
                throw fnf;
            }
        }
        this.prenume = prenume;
        this.dataNAstere = dataNAstere;
        this.dataInmatriculare = dataInmatriculare;
        this.cale_poza = "Cale poza";
        this.setVarsta();
    }

    public Student(String nume, LocalDate dataNastere, LocalDate dataInmatriculare, String[] array) {
        this.numeFamilie = nume;
        this.dataNAstere = dataNastere;
        this.dataInmatriculare = dataInmatriculare;
        this.prenume = array;
    }

    public static ArrayList<Student> getStudenti() throws Exception{
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
        return "Student{" +
                "nrMatricol='" + nrMatricol + '\'' +
                ", numeFamilie='" + numeFamilie + '\'' +
                ", prenume=" + Arrays.toString(prenume) +
                ", dataNAstere=" + dataNAstere +
                ", dataInmatriculare=" + dataInmatriculare +
                ", varsta=" + varsta +
                ", cale_poza='" + cale_poza + '\'' +
                ", note=" + note +
                '}';
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

    public String getNrMatricol() {
        return nrMatricol;
    }

    public String getNumeFamilie() {
        return numeFamilie;
    }

    public String[] getPrenume() {
        return prenume;
    }

    public LocalDate getDataNAstere() {
        return dataNAstere;
    }

    public LocalDate getDataInmatriculare() {
        return dataInmatriculare;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getCale_poza() {
        return cale_poza;
    }

    public void setCale_poza(String cale_poza) {
        this.cale_poza = cale_poza;
    }


    public void setVarsta(){
        Period dif = Period.between(this.dataNAstere, this.dataInmatriculare);
//        System.out.println(dif);
//        int nrAniBisecti = (this.dataInmatriculare.getYear() - this.dataNAstere.getYear()) / 4;
//        int age = dif.getYears() - nrAniBisecti;
//        System.out.println(age);
        this.varsta = dif.getYears() + 1;
    }

    public ArrayList<Nota> getNote() {
        return note;
    }

    public static ArrayList<String> getListaNrMatricole() {
        return listaNrMatricole;
    }
}


