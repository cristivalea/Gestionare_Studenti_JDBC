package main;

import model.Repository;
import model.Student;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class ModifcareDataNastere implements Comand{

    public void execute(){
        Student student = null;
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduceti noua data de nastere: ");
            System.out.println("Introduceti ziua: ");
            int zi = scanner.nextInt();
            System.out.println("Introduceti luna: ");
            int luna = scanner.nextInt();
            System.out.println("Introduceti anul: ");
            int an = scanner.nextInt();
            LocalDate data = LocalDate.of(an, luna, zi);
            System.out.println("Introduceti numarul matricol al carui student va fi schimbata data nasterii: ");
            String nrMat = scanner.next();
            for(int i = 0; i < Repository.getInstance().getStiudenti().size(); i++){
                if(nrMat.equals(Repository.getInstance().getStiudenti().get(i).getNrMatricol())){
                    student = Repository.getInstance().getStiudenti().get(i);
                    break;
                }
            }
            if(student == null){
                System.err.println("Studentul nu s fost gasit!");
                return;
            }
            if(data == null){
                System.err.println("Nu s-s creat noua data");
                return;
            }
            Repository.updateDataNastere(data, student.getNrMatricol());
            Student.dataNastere(data, student.getNrMatricol());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
