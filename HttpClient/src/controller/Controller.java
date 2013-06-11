/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Observer;
import model.HttpClient;

/**
 *
 * @author Quentin
 */
public class Controller {

    private HttpClient pages;

    public void add(String text, int port, Observer o) throws IOException {
        if(pages != null)
            pages.close();
        pages = new HttpClient(text, port);
        pages.addObserver(o);

        Thread t = new Thread(pages);
        t.start();
    }

    public void close() throws IOException {
        pages.close();
    }
}
