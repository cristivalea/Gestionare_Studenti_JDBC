package main;

import model.RegularExpresion;
import model.Repository;
import model.Student;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class ModificareNume implements Comand{

    public void execute(){
        Student student = null;
        String numeNou = "";
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduceti noul nume de familie: ");
            numeNou = scanner.next();
            if(RegularExpresion.RegularExpresionNumePrenume(numeNou) == false){
                System.err.println("Formatul numelui introdus este gresit!");
                return;
            }
            System.out.println("Introduceti numarul matricol al studentului al carui nume va fi modificat: ");
            String nrMat = scanner.next();
            for(int i = 0; i < Repository.getInstance().getStiudenti().size(); i++){
                if(nrMat.equals(Repository.getInstance().getStiudenti().get(i).getNrMatricol())){
                    student = Repository.getInstance().getStiudenti().get(i);
                    break;
                }
            }// end for parcurgere lista studenti din Repository
            if(student == null){
                System.err.println("Studentul nu a fost gasit!");
                return;
            }
            Repository.updateNumefamilie(numeNou, student.getNrMatricol());
            Student.updateNumefamilie(numeNou, student.getNrMatricol());
        }catch (Exception e){
            e.printStackTrace();
        }//end try - catch
    }// end function
}//end class
