package main.java.tp.eni.gsc.ui.bodies.contents.matieres;

import main.java.tp.eni.gsc.ui.bodies.contents.notes.Notes;

import java.util.List;

public class Matiere {
    private String matiereId;
    private String matiereCode;
    private String matiereLibelle;
    private  Integer matiereCoeff;
    private List<Notes> matiereNote;

    public Matiere() {}

    public Matiere(String matiereId, String matiereCode, String matiereLibelle, Integer matiereCoeff, List<Notes> matiereNote) {
        this.matiereId = matiereId;
        this.matiereCode = matiereCode;
        this.matiereLibelle = matiereLibelle;
        this.matiereCoeff = matiereCoeff;
        this.matiereNote = matiereNote;
    }

    public String getMatiereId() {
        return matiereId;
    }

    public void setMatiereId(String matiereId) {
        this.matiereId = matiereId;
    }

    public String getMatiereCode() {
        return matiereCode;
    }

    public void setMatiereCode(String matiereCode) {
        this.matiereCode = matiereCode;
    }

    public String getMatiereLibelle() {
        return matiereLibelle;
    }

    public void setMatiereLibelle(String matiereLibelle) {
        this.matiereLibelle = matiereLibelle;
    }

    public Integer getMatiereCoeff() {
        return matiereCoeff;
    }

    public void setMatiereCoeff(Integer matiereCoeff) {
        this.matiereCoeff = matiereCoeff;
    }

    public List<Notes> getMatiereNote() {
        return matiereNote;
    }

    public void setMatiereNote(List<Notes> matiereNote) {
        this.matiereNote = matiereNote;
    }
}
