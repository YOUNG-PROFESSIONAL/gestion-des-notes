package main.java.tp.eni.gsc.ui.bodies.contents.dashboard;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import main.java.tp.eni.gsc.config.HttpConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashBoardService {
    private final HttpConnection httpConnection = new HttpConnection();

    public List<DashBoard> getDashBoard(Integer niv, String matiereId) throws IOException {
        List<DashBoard> list = new ArrayList<>(){};
        JsonArray dash = httpConnection.httpGetAll("classement",niv,matiereId);
        for (JsonElement object: dash){
            DashBoard convObject = new Gson().fromJson(String.valueOf(object),DashBoard.class);
            list.add(convObject);
        }
        return list;
    }
}
