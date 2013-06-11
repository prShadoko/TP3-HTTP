/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

public class HttpClient extends Observable implements Runnable {

    private int port;
    private Socket socket;
    private BufferedReader in;
    private OutputStreamWriter out;
    private Url url;

    public HttpClient(String url, int port) throws IOException {
        this.url = new Url(url);
        System.out.println(this.url.getBaseURL());
        this.port = port;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public void send() throws IOException {
        this.out.write(this.url.generateReq());
        this.out.flush();
    }

    public void receive() throws IOException {
        String msg;

        StringBuffer html = new StringBuffer();
        while ((msg = this.in.readLine()) != null) {
            html.append(msg);
            html.append("\n");
        }
        System.out.println("#####REPONSE#####\n" + html);
        this.setChanged();
        this.notifyObservers(html.toString());
    }

    public void close() throws IOException {
        this.socket.close();
    }

    @Override
    public void run() {
        try {
            this.socket = new Socket(InetAddress.getByName(this.url.getBaseURL()), port);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new OutputStreamWriter(socket.getOutputStream());
            this.ask();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ask() throws IOException{
        this.send();
        this.receive();
    }
}
