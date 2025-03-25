package main;

import model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class AdaugareNota implements Comand{
    private Student student = null;
    private Disciplina disciplina = null;
    public void execute() throws Exception{
            LocalDate date_curent = LocalDate.now();
            LocalDate data = LocalDate.of(date_curent.getYear(), date_curent.getMonthValue(), date_curent.getDayOfMonth());
            System.out.println("Introduceti numele: ");
            Scanner scanner = new Scanner(System.in);
            String nume = scanner.next();
            if(RegularExpresion.RegularExpresionNumePrenume(nume) == false){
                System.err.println("Formatul numelui de familie este gresit!");
                return;
            }
            System.out.println("Introduceti codul disciplinei la care se adauga nota: ");
            String disciplina = scanner.next();
            for (int i = 0; i < Repository.getInstance().getStiudenti().size(); i++) {
                if(nume.equals(Repository.getInstance().getStiudenti().get(i).getNumeFamilie())){
                    this.student = Repository.getInstance().getStiudenti().get(i);
                    break;
                }
            }
            if(this.student == null){
                Exception e = new Exception("Studentul nu a fost gasit");
                throw e;
            }
            for (int j = 0; j < Repository.getInstance().getDiscipline().size(); j++){
                if(disciplina.equals(Repository.getInstance().getDiscipline().get(j).getCodDisciplina())){
                    this.disciplina = Repository.getInstance().getDiscipline().get(j);
                    break;
                }
            }
            if(this.disciplina == null){
                Exception e = new Exception("Disciplina nu a fost gasita");
                throw e;
            }
            System.out.println("Ce fel de nota doriti sa introduceti? ");
            System.out.println("1. Nota Numerica");
            System.out.println("2. Calificativ");
            System.out.println("3. Calificativ de tipul ADMIS/RESPINS");
            System.out.println("Introduceti optiunea: ");
            int optiune = scanner.nextInt();
            Nota nota = null;
            switch (optiune){
                case 1:
                {
                    System.out.println("Introduceti nota numeica: ");
                    int notaNumerica = scanner.nextInt();
                    System.out.println("Introduceti data examenului: ");
                    System.out.println("Ziua: ");
                    int zi = scanner.nextInt();
                    System.out.println("Luna: ");
                    int luna = scanner.nextInt();
                    System.out.println("An: ");
                    int an = scanner.nextInt();
                    LocalDate dataExamen = LocalDate.of(an, luna, zi);
                    int promovat = 0;
                    if(notaNumerica > 4){
                        promovat = 1;
                    }
                    nota = new NotaNumerica(TipNota.N, this.student.getNrMatricol(), this.disciplina.getCodDisciplina(), dataExamen, notaNumerica, promovat);
                    break;
                }
                case 2:
                {
                    System.out.println("Introduceti calificativul: ");
                    String calificativ = scanner.next();
                    TipCalificativ tipCalificativ = TipCalificativ.valueOf(calificativ);
                    System.out.println("Introduceti data examenului: ");
                    System.out.println("Ziua: ");
                    int zi = scanner.nextInt();
                    System.out.println("Luna: ");
                    int luna = scanner.nextInt();
                    System.out.println("An: ");
                    int an = scanner.nextInt();
                    LocalDate dataExamen = LocalDate.of(an, luna, zi);
                    int promovat = 1;
                    if(calificativ.equals("INSUFUCIENT")){
                        promovat = 0;
                    }
                    nota = new NotaCalificativ(TipNota.N, this.student.getNrMatricol(), this.disciplina.getCodDisciplina(), dataExamen, tipCalificativ, promovat);
                    break;
                }
                case 3:
                {
                    System.out.println("Introduceti calificativul (ADMIS/RESPINS): ");
                    String calificativ = scanner.next();
                    TipCalificativAR tipCalificativ = TipCalificativAR.valueOf(calificativ);
                    System.out.println("Introduceti data examenului: ");
                    System.out.println("Ziua: ");
                    int zi = scanner.nextInt();
                    System.out.println("Luna: ");
                    int luna = scanner.nextInt();
                    System.out.println("An: ");
                    int an = scanner.nextInt();
                    LocalDate dataExamen = LocalDate.of(an, luna, zi);
                    int promovat = 1;
                    if(calificativ.equals("INSUFUCIENT")){
                        promovat = 0;
                    }
                    nota = new NotaCalificativAR(TipNota.N, this.student.getNrMatricol(), this.disciplina.getCodDisciplina(), dataExamen, tipCalificativ, promovat);
                    break;
                }
            } // end switch
       // Repository.getInstance().adaugareNota(this.student, nota);
    } // end execute
}
