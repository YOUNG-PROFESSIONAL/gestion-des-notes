package main.java.tp.eni.gsc.ui.bodies.contents.etudiant;


public class Etudiant {
    private String personId;
    private String personName;
    private String personMatricule;
    private String studentLevel;

    public Etudiant() {
    }

    public Etudiant(String personId, String personName, String personMatricule, String studentLevel) {
        this.personId = personId;
        this.personName = personName;
        this.personMatricule = personMatricule;
        this.studentLevel = studentLevel;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonMatricule() {
        return personMatricule;
    }

    public void setPersonMatricule(String personMatricule) {
        this.personMatricule = personMatricule;
    }

    public String getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(String studentLevel) {
        this.studentLevel = studentLevel;
    }
}
