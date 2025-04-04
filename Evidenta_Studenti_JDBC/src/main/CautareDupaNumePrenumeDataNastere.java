package main;
import model.RegularExpresion;
import model.Repository;
import model.Student;

import java.time.LocalDate;
import java.util.Scanner;

public class CautareDupaNumePrenumeDataNastere implements Comand {
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);

            // Citire nume familie
            System.out.println("Introduceti numele de familie: ");
            String numeFam = scanner.next();
            if (!RegularExpresion.RegularExpresionNumePrenume(numeFam)) {
                System.err.println("Formatul numelui de familie este gresit!");
                return;
            }

            // Citire numar prenume
            System.out.println("Introduceti numarul de prenume: ");
            int nrPrenume = scanner.nextInt();
            scanner.nextLine(); // Golește bufferul

            // Citire prenume într-un vector
            String[] prenume1 = new String[nrPrenume];
            for (int i = 0; i < nrPrenume; i++) {
                System.out.println("Introduceti prenumele " + (i + 1) + ": ");
                prenume1[i] = scanner.next();
                if (!RegularExpresion.RegularExpresionNumePrenume(prenume1[i])) {
                    System.err.println("Formatul prenumelui este gresit!");
                    return;
                }
            }

            // Citire data nașterii
            System.out.println("Introduceti ziua nasterii: ");
            int ziNastere = scanner.nextInt();
            System.out.println("Introduceti luna nasterii: ");
            int lunaNastere = scanner.nextInt();
            System.out.println("Introduceti anul nastere: ");
            int anNastere = scanner.nextInt();
            scanner.nextLine(); // Golește bufferul

            LocalDate data = LocalDate.of(anNastere, lunaNastere, ziNastere);

            // Căutare în repo
            for (Student student : Repository.getInstance().getStiudenti()) {
                boolean c1 = numeFam.equals(student.getNumeFamilie());

                // Comparare directă a vectorului de stringuri
                boolean c2 = java.util.Arrays.equals(student.getPrenume(), prenume1);

                boolean c3 = data.equals(student.getDataNAstere());

                if (c1 && c2 && c3) {
                    System.out.println(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
