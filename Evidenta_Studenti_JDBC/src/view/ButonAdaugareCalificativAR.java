package view;

import model.*;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ButonAdaugareCalificativAR extends JButton implements Comand {
    private JComboBox<TipNota> tipNota;
    private JLabel labelStudent;
    private JLabel labelDisciplina;
    private JComboBox<String> calificativ;
    private JDatePickerImpl dataExamen;

    public ButonAdaugareCalificativAR(JComboBox<TipNota> tipNota, JLabel labelStudent, JLabel labelDisciplina, JComboBox<String> calificativ, JDatePickerImpl dataExamen) {
        super("Adaugare Calificativ A/R");
        this.tipNota = tipNota;
        this.labelStudent = labelStudent;
        this.labelDisciplina = labelDisciplina;
        this.calificativ = calificativ;
        this.dataExamen = dataExamen;
    }

    public void execute(){
        String tipDisciplina = tipNota.getSelectedItem().toString();
        String auxCodStudent = labelStudent.getText().trim();
        int pozitieS = auxCodStudent.lastIndexOf(" ");
        String codS = auxCodStudent.substring(pozitieS + 1).trim();
        String codStudent = null;
        if(codS != null && codS.length() > 1){
            codStudent = codS;
        }
        String auxlabelDisciplina = labelDisciplina.getText().trim();
        System.err.println(auxlabelDisciplina);
        int pozitie = auxlabelDisciplina.lastIndexOf(" ");
        int cod = Integer.parseInt(auxlabelDisciplina.substring(pozitie + 1).trim());
        System.err.println(cod);
        System.err.println(cod);
        int codDisciplina = cod;
        String calificativ_corect = calificativ.getSelectedItem().toString();
        String dataEx = dataExamen.toString();

        TipCalificativAR tipCalificativ = null;
        if(calificativ_corect.equalsIgnoreCase(TipCalificativAR.ADMIS.getDenumire())){
            tipCalificativ = TipCalificativAR.ADMIS;
        }
        else if(calificativ_corect.equalsIgnoreCase(TipCalificativAR.RESPINS.getDenumire())){
            tipCalificativ = TipCalificativAR.RESPINS;
        }

        int promovat = calificativ_corect.equals(TipCalificativAR.RESPINS.getDenumire()) ? 0 : 1;
        Date selectedDate = (Date) dataExamen.getModel().getValue();
        if (selectedDate == null) {
            System.err.println("Data examenului nu este selectată.");
            return;
        }
        LocalDate dataE = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        try {
            NotaCalificativAR notaAR = new NotaCalificativAR(TipNota.A, codStudent, codDisciplina, dataE, tipCalificativ, promovat);
            System.out.println(notaAR.toString());
            Repository.adaugareNotaCalificativAr(notaAR, codStudent, codDisciplina);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
