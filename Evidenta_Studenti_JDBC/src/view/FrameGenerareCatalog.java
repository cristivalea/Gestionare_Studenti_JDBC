package view;

import model.Disciplina;
import model.Repository;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

public class FrameGenerareCatalog extends JFrame implements ActionListener {
    // panel an studiu
    private JPanel panelAnStudiu;
    private JLabel labelAnStudiu;
    private JComboBox<Integer> comboAnStudiu;

    // panel Disciplina
    private JPanel panelDisciplina;
    private JLabel labelDisciplina;
    private JComboBox<String> comboDisciplina;

    // panel Profesori Titulari
    private JPanel panelProfesoriTitulari;
    private JLabel labelProfesoriTitulari;
    private JComboBox<String> comboProfesoriTitulari;

    //panel Specializare
    private JPanel panelSpecializare;
    private JLabel labelSpecializare;
    private JComboBox<String> comboSpecializare;

    // panel Data Examen
    private JPanel panelDataExamen;
    private JLabel labelDataExamen;
    private UtilDateModel modelDataExame;
    private JDatePickerImpl dataExamen;

    // main panel
    private JPanel mainPanel;

    private JDatePickerImpl createDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Astăzi");
        p.put("text.month", "Lună");
        p.put("text.year", "An");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        return new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    public FrameGenerareCatalog() throws Exception{
        // creare main panel
        this.mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
        this.mainPanel.setLayout(layout);

        // creare si adaugare panel an studiu
        this.panelAnStudiu = new JPanel();
        Integer anStudiu[] = {1, 2, 3, 4};
        this.comboAnStudiu = new JComboBox<Integer>(anStudiu);
        this.labelAnStudiu = new JLabel("An Studiu");
        this.panelAnStudiu.add(this.labelAnStudiu);
        this.panelAnStudiu.add(this.comboAnStudiu);
        this.mainPanel.add(this.panelAnStudiu);

        //creare si adaugare panel disciplina
        this.panelDisciplina = new JPanel();
        String[] numeDiscipline = new String[Repository.getInstance().getDiscipline().size()];
        for(int i = 0; i < Repository.getInstance().getDiscipline().size(); i++){
            numeDiscipline[i] = Repository.getInstance().getDiscipline().get(i).getNumedisciplina();
        }
        this.comboDisciplina = new JComboBox<String>(numeDiscipline);
        this.labelDisciplina = new JLabel("Disciplina");
        this.panelDisciplina.add(this.labelDisciplina);
        this.panelDisciplina.add(this.comboDisciplina);
        this.mainPanel.add(this.panelDisciplina);

        // creare si adaugare panel profesori titulari
        this.panelProfesoriTitulari = new JPanel();
        ArrayList<String> listaProfesori = Repository.getInstance().loadProfesoriTitulari();
        String[] profesori = new String[listaProfesori.size() + 1];
        for(int i = 0; i < listaProfesori.size(); i++) {
            profesori[i] = listaProfesori.get(i);
        }
        this.comboProfesoriTitulari = new JComboBox<String>(profesori);
        this.labelProfesoriTitulari = new JLabel("Profesor");
        this.panelProfesoriTitulari.add(this.labelProfesoriTitulari);
        this.panelProfesoriTitulari.add(this.comboProfesoriTitulari);
        this.mainPanel.add(this.panelProfesoriTitulari);

        // creare si adaugare panel specializare
        this.panelSpecializare = new JPanel();
        ArrayList<String> listaSpecializari = Repository.getInstance().loadSpecializari();
        String[] specializari = new String[listaSpecializari.size() + 1];
        for(int i = 0; i < listaSpecializari.size(); i++){
            specializari[i] = listaSpecializari.get(i);
        }
        this.comboSpecializare = new JComboBox<String>(specializari);
        this.labelSpecializare = new JLabel("Specializare");
        this.panelSpecializare.add(this.labelSpecializare);
        this.panelSpecializare.add(this.comboSpecializare);
        this.mainPanel.add(this.panelSpecializare);

        //creare si adaugare panel data examen
        this.panelDataExamen = new JPanel();
        this.dataExamen = createDatePicker();
        this.labelDataExamen = new JLabel("Data Examen");
        this.panelDataExamen.add(this.labelDataExamen);
        this.panelDataExamen.add(this.dataExamen);
        this.mainPanel.add(this.panelDataExamen);

        //adaugare main panel
        this.add(mainPanel);
        this.setVisible(true);
        this.pack();
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent actionEvent) {
//        ((Comand) actionEvent.getSource()).execute();

    }


    public static void main(String[] args) throws Exception{
        new FrameGenerareCatalog();
    }
}
