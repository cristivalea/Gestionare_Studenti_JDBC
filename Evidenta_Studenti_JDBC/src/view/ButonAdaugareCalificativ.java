package view;

import model.NotaCalificativ;
import model.TipCalificativ;
import model.TipNota;
import org.apache.poi.ss.formula.functions.T;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ButonAdaugareCalificativ extends JButton implements Comand {
    private JComboBox<TipNota> tipNota;
    private ButonCautare butonStudent;
    private ButonCautareDiscipline butonDisciplina;
    private JComboBox<String> calificativ;
    private JDatePickerImpl dataExamen;

    public ButonAdaugareCalificativ(JComboBox<TipNota> tipNota, ButonCautare butonStudent, ButonCautareDiscipline butonDisciplina, JComboBox<String> calificativ, JDatePickerImpl dataExamen) {
        super("Adaugare Calificativ");
        this.tipNota = tipNota;
        this.butonStudent = butonStudent;
        this.butonDisciplina = butonDisciplina;
        this.calificativ = calificativ;
        this.dataExamen = dataExamen;
    }

    public void execute(){
        String tipDisciplina = tipNota.getSelectedItem().toString();
        String codStudent = butonStudent.getText();
        int codDisciplina = Integer.parseInt(butonDisciplina.getText());
        String calificativ_corect = calificativ.getSelectedItem().toString();
        String dataEx = dataExamen.toString();

        TipCalificativ tipCalificativ = null;
        if(calificativ_corect.equals(TipCalificativ.INSUFICIENT)){
            tipCalificativ = TipCalificativ.INSUFICIENT;
        }
        else if(calificativ_corect.equals(TipCalificativ.SUFICIENT)){
            tipCalificativ = TipCalificativ.SUFICIENT;
        }
        else if(calificativ_corect.equals(TipCalificativ.BINE)){
            tipCalificativ = TipCalificativ.BINE;
        }
        else if(calificativ_corect.equals(TipCalificativ.FOARTE_BINE)){
            tipCalificativ = TipCalificativ.FOARTE_BINE;
        }
        else if(calificativ_corect.equals(TipCalificativ.EXCELENT)){
            tipCalificativ = TipCalificativ.EXCELENT;
        }

        int promovat = calificativ_corect.equals(TipCalificativ.SUFICIENT.getDenumire()) ? 0 : 1;
        LocalDate dataE = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            dataE = LocalDate.parse(dataEx, formatter);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            NotaCalificativ notaCalificativ = new NotaCalificativ(TipNota.C, codStudent, codDisciplina, dataE, tipCalificativ, promovat);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
