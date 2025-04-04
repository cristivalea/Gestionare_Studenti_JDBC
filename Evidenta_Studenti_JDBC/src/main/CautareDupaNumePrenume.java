package main;
import model.RegularExpresion;
import model.Repository;

import java.util.Arrays;
import java.util.Scanner;

public class CautareDupaNumePrenume implements Comand {

    public void execute() {
        boolean rezultatGasit = false;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduceti numele de familie: ");
            String numeFam = scanner.next();

            if (!RegularExpresion.RegularExpresionNumePrenume(numeFam)) {
                System.err.println("Formatul numelui de familie este gresit!");
                return;
            }

            System.out.println("Introduceti numarul de prenume: ");
            int nrPrenume = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            String[] prenumeIntroduse = new String[nrPrenume];
            for (int i = 0; i < nrPrenume; i++) {
                System.out.println("Introduceti prenumele " + (i + 1) + " : ");
                String auxPren = scanner.next();
                if (!RegularExpresion.RegularExpresionNumePrenume(auxPren)) {
                    System.err.println("Formatul prenumelui este gresit!");
                    return;
                }
                prenumeIntroduse[i] = auxPren;
            }

            if (Repository.getInstance().getStiudenti().isEmpty()) {
                System.out.println("Nu exista studenti in baza de date.");
                return;
            }

            for (int i = 0; i < Repository.getInstance().getStiudenti().size(); i++) {
                boolean c1 = numeFam.equals(Repository.getInstance().getStiudenti().get(i).getNumeFamilie());
                String[] prenumeStudent = Repository.getInstance().getStiudenti().get(i).getPrenume();

                // Verifică dacă cele două array-uri au aceleași prenume
                boolean c2 = Arrays.equals(prenumeIntroduse, prenumeStudent);

                if (c1 && c2) {
                    System.out.println("Student gasit: " + Repository.getInstance().getStiudenti().get(i));
                    rezultatGasit = true;
                }
            }

            if (!rezultatGasit) {
                System.out.println("Nu a fost gasit niciun student cu acest nume si prenume.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
