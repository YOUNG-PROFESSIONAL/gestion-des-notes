package main.java.tp.eni.gsc.ui.bodies.contents.notes;

import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.Etudiant;
import main.java.tp.eni.gsc.ui.bodies.contents.matieres.Matiere;

public class Notes {
    private String noteId;
    private Double note;
    private Etudiant noteStudent;
    private Matiere noteMatiere;

    public Notes() {
    }

    public Notes(String noteId, Double note, Etudiant noteStudent, Matiere noteMatiere) {
        this.noteId = noteId;
        this.note = note;
        this.noteStudent = noteStudent;
        this.noteMatiere = noteMatiere;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Etudiant getNoteStudent() {
        return noteStudent;
    }

    public void setNoteStudent(Etudiant noteStudent) {
        this.noteStudent = noteStudent;
    }

    public Matiere getNoteMatiere() {
        return noteMatiere;
    }

    public void setNoteMatiere(Matiere noteMatiere) {
        this.noteMatiere = noteMatiere;
    }
}
