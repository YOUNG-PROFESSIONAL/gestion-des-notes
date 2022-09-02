package main.java.tp.eni.gsc.config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.NIVEAU;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
    private final String urlHttp = "http://127.0.0.1:3000";
    /***** Create Connexion ********/
    private HttpURLConnection createConnection(String url1, String method) throws IOException {
        URL url = new URL(url1);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        return connection;
    }
    /******* HTTP GET ALL **********/
    public JsonArray httpGetAll(String endpoint, Integer niv, String mc) throws IOException {
        HttpURLConnection connection;
        if ((mc.isEmpty() || mc.isBlank()) && niv != null)
            connection = createConnection(urlHttp +"/"+ endpoint +"/" + niv , "GET");
        else if((mc.isEmpty() || mc.isBlank()) && niv == null)
            connection = createConnection(urlHttp +"/"+ endpoint , "GET");
        else
            connection = createConnection(urlHttp +"/"+ endpoint +"/" + niv  + "/" + mc, "GET");
        System.out.println(connection.getURL());
        connection.setRequestProperty("Accept", "application/json");
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("FAILURE, Http error code : "+ connection.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String data = br.readLine();
        Gson gson = new Gson();
        JsonArray res = gson.fromJson(String.valueOf(data), JsonArray.class);
        connection.disconnect();
        return res;
    }
    /******* HTTP GET ON **********/
    public String httpGetOne(String endpoint) throws IOException {
        HttpURLConnection connection = createConnection(urlHttp+"/"+endpoint, "GET");
        connection.setRequestProperty("Accept", "application/json");
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("FAILURE, Http error code: "+ connection.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return br.readLine();
    }
    /******* HTTP POST **********/
    public Message httpPost(String endpoint, String data) throws IOException {
        HttpURLConnection connection = createConnection(urlHttp+ "/" + endpoint, "POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(data.getBytes());
        outputStream.flush();
        connection.disconnect();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            Message message = new Message();
            message.setStatus("success");
            message.setMessage("Enregistrement reussi!");
            return message;
        } else {
            Message message = new Message();
            message.setStatus("error");
            message.setMessage("Erreur de l'enregistrement!");
            return message;
        }
    }
    /******* HTTP PUT **********/
    public Message httpPut(String endpoint, String data) throws IOException {
        HttpURLConnection connection = createConnection(urlHttp+ "/" + endpoint, "PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(data.getBytes());
        outputStream.flush();
        connection.disconnect();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            Message message = new Message();
            message.setStatus("success");
            message.setMessage("Mis a jour reussi!");
            return message;
        } else {
            Message message = new Message();
            message.setStatus("error");
            message.setMessage("Echec de la mis a jour!");
            return message;
        }
    }
    /******* HTTP DELETE **********/
    public Message httpDelete(String endpoint) throws IOException {
        HttpURLConnection connection = createConnection(urlHttp+ "/" + endpoint, "DELETE");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.disconnect();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            Message message = new Message();
            message.setStatus("success");
            message.setMessage("Suppression reussi!");
            return message;
        } else {
            Message message = new Message();
            message.setStatus("error");
            message.setMessage("Echec de la suppression!");
            return message;
        }
    }
    /******* HTTP GET ALL  **********/
    public JsonArray httpSearch(String endpoint, String str) throws IOException {
        HttpURLConnection connection = createConnection(urlHttp+"/"+endpoint, "GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(str.getBytes());
        outputStream.flush();
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Echec, Http error code: "+ connection.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String data = br.readLine();
        System.out.println(data);
        Gson gson = new Gson();
        JsonArray res = gson.fromJson(data, JsonArray.class);
        connection.disconnect();
        return res;
    }
}
