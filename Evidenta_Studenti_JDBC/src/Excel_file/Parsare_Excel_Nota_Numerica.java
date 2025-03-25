package Excel_file;

import model.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parsare_Excel_Nota_Numerica {
    private static final String cale_fisier_nota_numerica = "D:\\PYTHON\\Generare_Studenti_Local\\model\\catalog_examen_numeric.xlsx";

    public static ArrayList<NotaNumerica> getNoteNumerice(String cale_fisier_nota_numerica) throws Exception {
        ArrayList<NotaNumerica> note_numerice = new ArrayList<>();
        File xlsxFile = new File(cale_fisier_nota_numerica);
        final XlsReader reader = new XlsReader();
        Workbook workbook = reader.getWorkbook(xlsxFile);
        Sheet sheet = workbook.getSheetAt(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int index = 0;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            String nume = row.getCell(0).getStringCellValue();
            String prenume = row.getCell(1).getStringCellValue();
            String disciplina = row.getCell(2).getStringCellValue();
            String dataExamen = row.getCell(3).getStringCellValue();
            int notaExamen = (int) row.getCell(4).getNumericCellValue();
            int notaSeminar = (int) row.getCell(5).getNumericCellValue();
            int notaLaborator = (int) row.getCell(6).getNumericCellValue();
            int notaProiect = (int) row.getCell(7).getNumericCellValue();
            double coefPrezentaExamen = row.getCell(8).getNumericCellValue();
            double coefPrezentaSeminar = row.getCell(9).getNumericCellValue();
            double coefPrezentaLaborator = row.getCell(10).getNumericCellValue();
            double coefPrezentaProiect = row.getCell(11).getNumericCellValue();
            int notaFinala = (int)(notaExamen * coefPrezentaExamen + notaLaborator * coefPrezentaLaborator + notaSeminar * coefPrezentaSeminar + notaProiect * coefPrezentaProiect);
            int promovat = 1;
            if(notaFinala < 5){
                promovat = 0;
            }
            LocalDate data_examen = LocalDate.parse(dataExamen, formatter);

            System.out.println(nume + prenume + disciplina + dataExamen + notaExamen + notaSeminar + notaLaborator + notaProiect + coefPrezentaExamen + coefPrezentaLaborator + coefPrezentaSeminar + coefPrezentaProiect);
            index++;

             //Găsirea studentului;
            Student student = null;
            for(int j = 0; j < Repository.getInstance().getStiudenti().size(); j++){
                if(nume.equals(Repository.getInstance().getStiudenti().get(j).getNumeFamilie())){
                    student = Repository.getInstance().getStiudenti().get(j);
                    break;
                }
            }

             //Găsirea disciplinei
            Disciplina disciplina1 = null;
            for(int k = 0; k < Repository.getInstance().getDiscipline().size(); k++){
                if(disciplina.equals(Repository.getInstance().getDiscipline().get(k).getNumedisciplina())){
                    disciplina1 = Repository.getInstance().getDiscipline().get(k);
                    break;
                }
            }
            NotaNumerica nota_numerica = new NotaNumerica(TipNota.N,  student.getNrMatricol(), disciplina1.getCodDisciplina(), data_examen, notaFinala, promovat);
            note_numerice.add(nota_numerica);
        }
        System.out.println(index);
        workbook.close();
       return note_numerice;
    }

    public static void main(String[] args) {
        try {
            ArrayList<NotaNumerica> note = getNoteNumerice(cale_fisier_nota_numerica);
            System.out.println("Notele au fost procesate cu succes.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
