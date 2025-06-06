package Excel_file;

import model.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parsare_Excel_calificativ_AR {
    private static final String cale_fisier_nota_calificativ_ar = "D:\\PYTHON\\Generare_Studenti_Local\\model\\catalog_examen_calificativar.xlsx";

    public static ArrayList<NotaCalificativAR> getNoteCalificativeAR(String cale_fisier_nota_calificativ) throws Exception {
        ArrayList<NotaCalificativAR> note_calificative_ar = new ArrayList<NotaCalificativAR>();
        File xlsxFile = new File(cale_fisier_nota_calificativ);
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
            String calificativ = row.getCell(4).getStringCellValue();
            double coefPrezentaExamen = row.getCell(5).getNumericCellValue();
            double coefPrezentaSeminar = row.getCell(6).getNumericCellValue();
            double coefPrezentaLaborator = row.getCell(7).getNumericCellValue();
            double coefPrezentaProiect = row.getCell(8).getNumericCellValue();
            TipCalificativAR tipCalificativ = TipCalificativAR.valueOf(calificativ);
            index++;

            int promovat = 1;
            if(calificativ.equals(TipCalificativAR.RESPINS.getDenumire())){
                promovat = 0;
            }
            LocalDate data_examen = LocalDate.parse(dataExamen, formatter);
            System.out.println(nume + prenume + disciplina + dataExamen + calificativ + coefPrezentaExamen + coefPrezentaLaborator + coefPrezentaSeminar + coefPrezentaProiect);

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
            NotaCalificativAR nota_calificativ_ar = new NotaCalificativAR(TipNota.A, student.getNrMatricol(), disciplina1.getCodDisciplina(), data_examen, tipCalificativ, promovat);
            note_calificative_ar.add(nota_calificativ_ar);
        }
        System.out.println(index);
        workbook.close();
        return note_calificative_ar;
    }
    public static void main(String[] args){
        try {
            ArrayList<NotaCalificativAR> note = getNoteCalificativeAR(cale_fisier_nota_calificativ_ar);
            System.out.println("Notele au fost procesate cu succes.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
