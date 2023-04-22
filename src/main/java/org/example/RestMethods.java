package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RestMethods {

    public String methodGet(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        return "response.toString()";
    }

    public String methodPost(String url, String data) throws IOException {
//        data = "{\"username\":\"root\",\"password\":\"password\"}";
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
//        HttpURLConnection http = (HttpURLConnection) connection;
        connection.setRequestMethod("POST"); // PUT is another valid option
        connection.setDoOutput(true);
        byte[] out = data .getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        connection.setFixedLengthStreamingMode(length);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.connect();
        try(OutputStream os = connection.getOutputStream()) {
            os.write(out);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println("++++++++++++++++++++");
        System.out.println(response.toString());
        System.out.println("++++++++++++++++++++");
        return "response.toString()";
    }


    public String methodPost1(String url) throws IOException {
//        String urlParameters  = "param1=a&param2=b&param3=c";
//        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
//        int    postDataLength = postData.length;
        String request        = url;
        URL    obj            = new URL( request );
        HttpURLConnection conn= (HttpURLConnection) obj.openConnection();
        conn.setDoOutput( true );
        conn.setInstanceFollowRedirects( false );
        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-Type", "text/plain");
//        conn.setRequestProperty( "charset", "utf-8");
//        conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        conn.setUseCaches( false );

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer response = new StringBuffer();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println(response);
        System.out.println("+++++++++++++++++++++++++++++++");
        return "response.toString()";
    }

}
