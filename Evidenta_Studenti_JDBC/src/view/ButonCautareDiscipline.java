package view;

import model.Disciplina;
import model.Repository;

import javax.swing.*;

public class ButonCautareDiscipline extends JButton implements Comand {
    private JTextField txtNumeButon;
    private JLabel labelAfisareDiscipline;
    private Disciplina disciplinaSelectata;


    public ButonCautareDiscipline(JTextField nume, JLabel afisare){
        super("Cautare disiciplina");
        this.txtNumeButon = nume;
        this.labelAfisareDiscipline = afisare;
    }

    public void execute(){
        System.err.println("Buton Cautare Disciplina -----Test------");
        try {
            String numeDisciplina = this.txtNumeButon.getText();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            boolean disciplinaGasit = false;
            Disciplina[] disciplinaGasita = {null};

            for (Disciplina d : Repository.getInstance().getDiscipline()) {
                if (numeDisciplina.equals(d.getNumedisciplina())) {
                    String linie = d.getNumedisciplina() + " " + d.getCodDisciplina();
                    model.addElement(linie);
                    disciplinaGasit = true;
                    disciplinaGasita[0] = d;
                    System.err.println("Disciplina gasita");
                }
            }

            if (!disciplinaGasit) {
                this.labelAfisareDiscipline.setText("Nu au fost găsite discipline cu acest nume.");
                //this.comboBox.setVisible(false);
                return;
            }

            ///this.comboBox.setModel(model);
            //this.comboBox.setVisible(true);

            // Stocăm disciplina selectată
            this.disciplinaSelectata = disciplinaGasita[0];

//            this.comboBox.addActionListener(e -> {
//                String selectedItem = (String) comboBox.getSelectedItem();
//                if (selectedItem != null) {
//                    this.labelAfisareDiscipline.setText("Disciplina selectată: " + selectedItem);
//                    this.disciplinaSelectata = disciplinaGasita[0];
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Disciplina getDisciplinaSelectata() {
        return this.disciplinaSelectata;
    }
}
