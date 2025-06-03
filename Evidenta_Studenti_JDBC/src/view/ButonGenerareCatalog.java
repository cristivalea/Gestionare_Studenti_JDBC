package view;

import model.Repository;
import model.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ButonGenerareCatalog extends JButton implements Comand {
    private JLabel labelAnStudiu;
    private JLabel labelDisciplina;
    private JLabel labelProfesorTitular;
    private JLabel labelSpecializare;
    private JDatePickerImpl dataExamen;

    public ButonGenerareCatalog(JLabel labelAnStudiu, JLabel labelDisciplina, JLabel labelProfesorTitular, JLabel labelSpecializare, JDatePickerImpl dataExamen) {
        super("Generare Catalog");
        this.labelAnStudiu = labelAnStudiu;
        this.labelDisciplina = labelDisciplina;
        this.labelProfesorTitular = labelProfesorTitular;
        this.labelSpecializare = labelSpecializare;
        this.dataExamen = dataExamen;
    }

    public void execute() {
        Workbook workbook = new XSSFWorkbook();

        CellStyle boldStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldStyle.setFont(boldFont);

        Sheet sheet = workbook.createSheet("Catalog - Info");

        int rowNum = 0;

        sheet.createRow(rowNum++).createCell(0).setCellValue("UNIVERSITATEA POLITEHNICA TIMISOARA");
        sheet.createRow(rowNum++).createCell(0).setCellValue("FACULTATEA: AUTOMATICA SI CALCULATOARE");
        sheet.createRow(rowNum++).createCell(0).setCellValue("SPECIALIZAREA:  TEHNOLOGIA  INFORMAŢIEI");

        sheet.createRow(rowNum++).createCell(0).setCellValue("FORMA DE ÎNVĂŢĂMÂNT: ZI");

        Row row5 = sheet.createRow(rowNum++);
        row5.createCell(0).setCellValue("ANUL DE STUDIU:  ");
        row5.createCell(1).setCellValue(labelAnStudiu.getText());
        row5.createCell(2).setCellValue("SEMESTRUL:  I");

        Row row6 = sheet.createRow(rowNum++);
        row6.createCell(0).setCellValue("ANUL UNIVERSITAR:    ");
        row6.createCell(1).setCellValue("2024-2025");

        Row row7 = sheet.createRow(rowNum++);
        Cell titleCell = row7.createCell(0);
        titleCell.setCellValue("CATALOG (PROCES VERBAL)");
        titleCell.setCellStyle(boldStyle);

        Row row8 = sheet.createRow(rowNum++);
        row8.createCell(0).setCellValue("Disciplina: ");
        row8.createCell(1).setCellValue(labelDisciplina.getText());


        Row row9 = sheet.createRow(rowNum++);
        row9.createCell(0).setCellValue("Mod de examinare: ");
        row9.createCell(1).setCellValue("Examen");


        Row row10 = sheet.createRow(rowNum++);
        row10.createCell(0).setCellValue("Numele titularului de disciplină: ");
        row10.createCell(1).setCellValue(labelProfesorTitular.getText());

        sheet.createRow(rowNum++).createCell(0).setCellValue("Pondere curs\t0,5");
        sheet.createRow(rowNum++).createCell(0).setCellValue("Pondere laborator\t0,5");
        sheet.createRow(rowNum++).createCell(0).setCellValue("Pondere seminar\t0");
        sheet.createRow(rowNum++).createCell(0).setCellValue("Pondere proiect\t0");


        Row examDateRow = sheet.createRow(rowNum++);
        examDateRow.createCell(0).setCellValue("Data examen");
        Date selectedDate = (Date) dataExamen.getModel().getValue();
        if (selectedDate != null) {
            LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            examDateRow.createCell(1).setCellValue(localDate.toString());
        } else {
            examDateRow.createCell(1).setCellValue("Nespecificată");
        }


        sheet.createRow(rowNum++).createCell(0).setCellValue("Numar maxim prezente curs\t14");
        sheet.createRow(rowNum++).createCell(0).setCellValue("Numar maxim prezente laborator\t14");
        sheet.createRow(rowNum++).createCell(0).setCellValue("Numar maxim prezente proiect\t0");
        sheet.createRow(rowNum++).createCell(0).setCellValue("Numar maxim prezente seminar\t0");


        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }


        // Adăugare a doua foaie cu tabelul pentru studenți
        Sheet sheet2 = workbook.createSheet("Catalog - Note Studenti");

        Row headerRow = sheet2.createRow(0);
        String[] headers = {
                "Numar matricol", "Nume si prenume student", "Nota Curs", "Nota Laborator",
                "Nota Proiect", "Nota Seminar", "Nota Finala",
                "Prezenta Curs", "Prezenta Laborator", "Prezenta Proiect", "Prezenta Seminar"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(boldStyle);
        }
        int studentRowNum = 1;
        try {
            for (Student s : Repository.getInstance().getStiudenti()) {
                Row row = sheet2.createRow(studentRowNum++);
                row.createCell(0).setCellValue(s.getNrMatricol());
                row.createCell(1).setCellValue(s.getNumeFamilie() + " " + s.getPrenume()[0]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        for (int i = 0; i < headers.length; i++) {
            sheet2.autoSizeColumn(i);
        }


        try (FileOutputStream out = new FileOutputStream("catalog.xlsx")) {
            workbook.write(out);
            workbook.close();
            JOptionPane.showMessageDialog(null, "Catalogul a fost generat cu succes (prima pagină)!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la generarea catalogului: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
