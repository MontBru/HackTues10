package com.example.backend.Services;

import com.example.backend.DTO.StatsDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class AiService {
    private static HttpURLConnection connection;

    private String getJsonResponse(InputStream inputStream) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    public int AiRequest(double min, double max, int current) throws Exception {
        String url = "http://192.168.161.61:5000/get_evaluation?min_avg=" + (int)min + "&max_avg=" + (int)max + "&curr_bpm=" + current ;
        URL myurl = new URL(url);

        connection = (HttpURLConnection) myurl.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if(responseCode != HttpURLConnection.HTTP_OK)
        {
            throw new IOException();
        }

        String jsonResponse = getJsonResponse(connection.getInputStream());

        String evalString = jsonResponse.replace("{\"evaluation\":", "");
        evalString = evalString.replace("}","");
        int evaluation = Integer.parseInt(evalString);

        return evaluation;
    }

}
