package main.java.tp.eni.gsc.ui.bodies.contents.notes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import main.java.tp.eni.gsc.config.HttpConnection;
import main.java.tp.eni.gsc.config.Message;
import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.Etudiant;
import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.NIVEAU;
import main.java.tp.eni.gsc.ui.bodies.contents.matieres.Matiere;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotesService {
    private final HttpConnection httpConnection = new HttpConnection();

    public List<Notes> getAllNotes(Integer niv, String matiereId) throws IOException {
        List<Notes> list = new ArrayList<>(){};
        JsonArray notes = httpConnection.httpGetAll("notes",niv,matiereId);
        for (JsonElement object: notes){
            Notes convertedObject = new Gson().fromJson(String.valueOf(object),Notes.class);
            list.add(convertedObject);
        }
        return list;
    }
    public Notes getNotes(Notes notes){
        return null;
    }
    public Notes addNotes(Notes notes) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        Message elements = httpConnection.httpPost("notes",json);
        System.out.println("Message : " + elements.message);
        return null;
    }
    public Notes updateNotes(Notes notes) throws IOException     {
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        Message elements = httpConnection.httpPut("notes",json);
        System.out.println("Message : " + elements.message);
        return null;
    }
    public boolean removeNotes(Notes notes) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        Message elements = httpConnection.httpDelete("notes" + "/" +json);
        System.out.println("Message : " + json);
        System.out.println("Message : " + elements.message);
        return true;
    }
}
