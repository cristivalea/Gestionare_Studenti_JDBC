package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import view.Observer;
public class Repository {
    private static ArrayList<Disciplina> discipline  = new ArrayList<Disciplina>();
    private static ArrayList<Student> studenti = new ArrayList<Student>();
    private static ArrayList<Nota> note = new ArrayList<Nota>();
    private static ArrayList<Observer> observers = new ArrayList<Observer>();
    private static Repository instance=null;

    private Repository() throws Exception {
        discipline = loadDiscipline();
        note = loadNote();
        for (Disciplina d : discipline) {
            for (Nota n : note) {
                if (n.getCod_disciplina() == d.getCodDisciplina()) {
                    n.setD(d);
                    n.setNotaFinala();
                    break;
                }
            }
        }
        studenti = loadStudenti();
        for (Student s : studenti) {
            for (Nota n : note) {
                if (s.getNrMatricol().equals(n.numar_matricol)) {
                    s.getNote().add(n);
                }
            }
        }
        Map<String, Student> mapStudenti = new HashMap<>();
        for (Student s : studenti) {
            mapStudenti.put(s.getNrMatricol(), s);
        }
        for (Nota n : note) {
            Student s = mapStudenti.get(n.getNumar_matricol());
            if (s != null) {
                n.setStudent(s);
            }
        }
        //update
    }

    public static Repository getInstance() throws Exception {
        if(instance == null)
            instance=new Repository();
        return instance;
    }

    private ArrayList<Disciplina> loadDiscipline(){
        return Disciplina.getDiscipline();
    }

    private ArrayList<Nota> loadNote(){
        ArrayList<Nota> note_aux = new ArrayList<Nota>();
        for(int i = 0; i < NotaNumerica.getNote().size(); i++){
            note_aux.add(NotaNumerica.getNote().get(i));
        }
        for(int i = 0; i < NotaCalificativAR.getNoteCalificativ().size(); i++){
            note_aux.add(NotaCalificativAR.getNoteCalificativ().get(i));
        }
        for(int i = 0; i < NotaCalificativ.getNoteCalificativ().size(); i++){
            note_aux.add(NotaCalificativ.getNoteCalificativ().get(i));
        }
        return note_aux;
    }

    public ArrayList<Student> loadStudenti() throws Exception{
        return Student.getStudenti();
    }

    public static ArrayList<Disciplina> getDiscipline() {
        return discipline;
    }

    public static ArrayList<Student> getStiudenti() {
        return studenti;
    }

    public static ArrayList<Nota> getNote() {
        return note;
    }

    public static void adaugareDisciplina(Disciplina d){
        d.adaugaDisciplina();
        discipline.add(d);
        notifyObservers();
    }

    public static void adugareStudent(Student s){
        s.adaugareStudent();
        studenti.add(s);
        notifyObservers();
    }

    public static void adaugaObservatori(Observer obs){
        observers.add(obs);
    }

    public static void notifyObservers(){
        for(Observer o : observers){
            o.update();
        }
    }

    public static void cautareStudentNume(String nume){
        Student student = null;
        for(Student s : studenti){
            if(s.getNumeFamilie().equals(nume)){
                student = s;
                break;
            }
        }
    }
} // end Repository
