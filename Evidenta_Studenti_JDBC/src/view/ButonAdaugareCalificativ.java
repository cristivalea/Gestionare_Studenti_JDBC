package view;

import model.*;
import org.apache.poi.ss.formula.functions.T;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ButonAdaugareCalificativ extends JButton implements Comand {
    private JComboBox<TipNota> tipNota;
    private JLabel labelStudent;
    private JLabel labelDisciplina;
    private JComboBox<String> calificativ;
    private JDatePickerImpl dataExamen;

    public ButonAdaugareCalificativ(JComboBox<TipNota> tipNota, JLabel labelStudent, JLabel labelDisciplina, JComboBox<String> calificativ, JDatePickerImpl dataExamen) {
        super("Adaugare Calificativ");
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
        int codDisciplina = cod;
        String calificativ_corect = calificativ.getSelectedItem().toString();
        String dataEx = dataExamen.toString();

        TipCalificativ tipCalificativ = null;
        if(calificativ_corect.equalsIgnoreCase(TipCalificativ.INSUFICIENT.getDenumire())){
            tipCalificativ = TipCalificativ.INSUFICIENT;
        }
        else if(calificativ_corect.equalsIgnoreCase(TipCalificativ.SUFICIENT.getDenumire())){
            tipCalificativ = TipCalificativ.SUFICIENT;
        }
        else if(calificativ_corect.equalsIgnoreCase(TipCalificativ.BINE.getDenumire())){
            tipCalificativ = TipCalificativ.BINE;
        }
        else if(calificativ_corect.equalsIgnoreCase(TipCalificativ.FOARTE_BINE.getDenumire())){
            tipCalificativ = TipCalificativ.FOARTE_BINE;
        }
        else if(calificativ_corect.equalsIgnoreCase(TipCalificativ.EXCELENT.getDenumire())){
            tipCalificativ = TipCalificativ.EXCELENT;
        }

        int promovat = calificativ_corect.equals(TipCalificativ.SUFICIENT.getDenumire()) ? 0 : 1;
        Date selectedDate = (Date) dataExamen.getModel().getValue();
        if (selectedDate == null) {
            System.err.println("Data examenului nu este selectată.");
            return;
        }
        LocalDate dataE = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        try {
            NotaCalificativ notaCalificativ = new NotaCalificativ(TipNota.C, codStudent, codDisciplina, dataE, tipCalificativ, promovat);
            System.out.println(notaCalificativ.toString());
            Repository.adaugareNotaCalificativ(notaCalificativ, codStudent, codDisciplina);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
