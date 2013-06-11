/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Quentin
 */
public class Url {

    private String _URL;
    private String _baseURL;

    public Url(String _URL) {
        this._URL = _URL;
        this._baseURL = _URL.split("/")[0];
    }

    public int getStatusCode(String text) {
        int code = 0;
        String firstline = text.split("\n")[0];
        Pattern datePatt = Pattern.compile("(.*) ([0-9]{3}) (.*)");
        Matcher match = datePatt.matcher(firstline);
        if (match.matches()) {
            code = Integer.parseInt(match.group(2));
        }
        return code;
    }

    public String generateReq() {
        String temp;
        if (this._URL.equals(this._baseURL)) {
            temp = "/";
        } else {
            temp = this._URL.replace(this._baseURL, "").replace("//", "");
        }
        String result = "GET " + temp + " HTTP/1.1\r\n";;
        result += "Host: " + this._baseURL + "\r\n";
        result += "Connection: Keep-Alive" + "\r\n";
        result += "\r\n";
        System.out.println("#####REQUEST#####\n"+result);
        return result;
    }

    public String getURL() {
        return _URL;
    }

    public void setURL(String _URL) {
        this._URL = _URL;
    }

    public String getBaseURL() {
        return _baseURL;
    }

    public void setBaseURL(String _baseURL) {
        this._baseURL = _baseURL;
    }
}
