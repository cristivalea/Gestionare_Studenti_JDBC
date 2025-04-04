package main;

import model.RegularExpresion;
import model.Repository;
import model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;

public class AdaugareStudent implements Comand {

    /**
     *
     */
    public void execute(){
        Scanner input = new Scanner(System.in);
        String numeFam = null;
        do{
            System.out.println("Introduceti numele de familie al studentului: ");
            numeFam = input.next();
            numeFam = numeFam.trim();
        }while(!RegularExpresion.RegularExpresionNumePrenume(numeFam));

        Vector<String> prenumeStud = new Vector<String>();
        System.out.println("Introduceti numarul de prenume ale studentului: ");
        int nr = input.nextInt();
        for(int i = 0 ; i < nr; i++){
            String str = null;
            do{
                System.out.println("Introduceti preumele " + i + ": ");
                str = input.next();
            }while (!RegularExpresion.RegularExpresionNumePrenume(str));
            prenumeStud.add(str);
        }
        String[] v = new String[prenumeStud.size()];
        prenumeStud.copyInto(v);

        LocalDate dataNastere = null;
        LocalDate dataInmatriculare = null;
        Exception e = null;

            do{
                try {
                    System.out.println("Introduceti data nasterii");
                    System.out.println("Introduceti an nastere: ");
                    int anNastere = input.nextInt();
                    System.out.println("Introduceti luna nasterii: ");
                    int lunaNAstere = input.nextInt();
                    System.out.println("Introduceti zi nastere: ");
                    int ziNastere = input.nextInt();
                    dataNastere = LocalDate.of(anNastere, lunaNAstere, ziNastere);
                }catch (Exception ee){
                    e = ee;
                }
                finally {
                    System.out.println((e == null) ? "model.Data nasterii este corecta" : "model.Data nasterii este gresita");
                }
            }while ((e != null));


            e = null;
            do{
                try{
                    System.out.println("Introduceti data inmatricularii");
                    System.out.println("Introduceti an de inmatriculare: ");
                    int anInmatriculare = input.nextInt();
                    System.out.println("Introduceti luna inmatricularii: ");
                    int lunaInmatricularii = input.nextInt();
                    System.out.println("Introduceti ziua inmatricularii: ");
                    int ziInmatriculare = input.nextInt();
                    dataInmatriculare = LocalDate.of(anInmatriculare, lunaInmatricularii, ziInmatriculare);
                }catch (Exception ee){
                    e = ee;
                }
                finally {
                    System.out.println((e == null) ? "model.Data inmatricularii este corecta" : "model.Data inmatricularii este gresita");
                }
            }while (e == null);
        //}while (dataInmatriculare.diferenta(dataNastere) > (18 * 365));


        Student student = null;
        try{
            student = new Student(numeFam, dataNastere, dataInmatriculare, v);

        }catch (Exception e1){
            e1.printStackTrace();
        }

        //Verificare existenta student
        if(Predicates.studentExistent.test(student)){
            return;
        }

        //adaugare student file
        try{
            student.adaugareStudent();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }// end function
}// end class
