package main;

import model.RegularExpresion;
import model.Repository;
import model.Student;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ModificarePrenume implements Comand{

    public void execute(){
        Student student = null;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduceti numarul de prenume: ");
            int nrPren = scanner.nextInt();
            String[] prenumeNoi = new String[nrPren];
            for(int i = 0; i < nrPren; i++){
                System.out.println("Introduceti prenumele " + i + " : ");
                String auxPren = scanner.next();
                if(RegularExpresion.RegularExpresionNumePrenume(auxPren) == false){
                    System.err.println("Formatul prenumelui introdus este gresit!");
                    return;
                }
                prenumeNoi[i] = auxPren;
            }
            System.out.println("Introduceti numarul matricol al studentului al carui prenume va fi schimbat: ");
            String nrMat = scanner.next();
            if(RegularExpresion.RegularExpresionNrMatricol(nrMat) == false){
                System.err.println("Formatul numarului matricol este gresit!");
                return;
            }
            for(int i = 0; i < Repository.getInstance().getStiudenti().size(); i++){
                if(nrMat.equals(Repository.getInstance().getStiudenti().get(i).getNrMatricol())){
                    student = Repository.getInstance().getStiudenti().get(i);
                    break;
                }
            }// end for parcuregere lista studenti Repository
            if(student == null){
                System.err.println("Studentul nu a fost gasit!");
                return;
            }
            Repository.updatePrenume(prenumeNoi, student.getNrMatricol());
            Student.updatePrenume(prenumeNoi, student.getNrMatricol());
        }catch (Exception e){
            e.printStackTrace();
        }
    }// end function
}// end class
