package model;

import org.apache.poi.ss.formula.functions.T;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main2(String[] args) {
        Student.getStudenti();
        String[] prenume = new String[]{"Ionut", "Mihai"};
        Student s1 = new Student("CTI023431", "Paulescu", prenume, LocalDate.of(1992, 10, 11), LocalDate.of(2024, 07, 21));
        s1.adaugareStudent();
        Student.stergereStudent("CTI023431");
        Student.updateNumefamilie("Popescu", "CTI022106");
        Student.dataNastere(LocalDate.of(2002, 10, 02), "CTI022105");
    } // testare functionalitati studenti

    public static void main3(String[] args) {
        Disciplina disc1 = new Disciplina(501, "Logica2", 0.5, 0.5, 0, 0, 0, 0, 0, 0, 5);
        //disc1.adaugaDisciplina();
        Disciplina.stergereDisciplina(500);
        Disciplina.modificareCoeficientCurs(0.5, 100);
        Disciplina.modificareCoeficientLab(0.5, 100);
        Disciplina.modificareCoeficientProiect(0.1, 100);
        Disciplina.modificareCoeficientSeminar(0.2, 100);
        Disciplina.modificareCoeficientPrezentaCurs(0.3, 100);
        Disciplina.modificareCoeficientPrezentaLab(0.4, 100);
        Disciplina.modificareCoeficientPrezentaProiect(0.5, 100);
        Disciplina.modificareCoeficientPrezentaSeminar(0.6, 100);
        Disciplina.modificareNrCredite(6, 100);
        for (Disciplina d : Disciplina.getDiscipline()) {
            System.out.println(d);
        }
    } // testare functionalitati disciplina

    public static void main4(String[] args) {
        ArrayList<Nota> nn = NotaCalificativ.getNoteCalificativ();
        for (Nota n : nn) {
            System.out.println(n);
        }
    } // testare incarcare note calificativ

    public static void main5(String[] args) {
        NotaNumerica nn = new NotaNumerica(TipNota.N, "CTI024873", 999, LocalDate.of(2025, 01, 10), 9, 1);
        System.out.println(nn);
        //nn.adaugaNota();
        //NotaNumerica.stergeNota("CTI024873", 999);
        NotaNumerica.updateNota(3, 100, "CTI024873");
    } // testare nota numerica

    public static void main6(String[] args) {
        NotaCalificativ nc = new NotaCalificativ(TipNota.C, "CTI024873", 999, LocalDate.of(2024, 12, 18), TipCalificativ.BINE, 1);
        //nc.adaugaNota();
        //NotaCalificativ.stergereNota(999, "CTI024873");
        NotaCalificativ.updateNota(TipCalificativ.BINE, 105, "CTI024873");
    } // testare nota calificativ

    public static void main7(String[] args) {
        NotaCalificativAR na = new NotaCalificativAR(TipNota.A, "CTI024873", 999, LocalDate.of(2024, 12, 18), TipCalificativAR.ADMIS, 1);
        //na.adaugaNota();
        //NotaCalificativ.stergereNota(999, "CTI024873");
        NotaCalificativAR.updateNota(TipCalificativAR.RESPINS, 107, "CTI024873");
    } // end main nota califictiv a/r
}
