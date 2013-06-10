/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpclient;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;

public class HttpClient {

	private Socket _socket;

	public HttpClient() {
	}

	public String getPage(URL URL) throws UnknownHostException, IOException {

		_socket = new Socket(InetAddress.getByName(URL.getHost()), 80);

		BufferedOutputStream out = new BufferedOutputStream(_socket.getOutputStream());

		out.write(URL.getFile().getBytes());



		return null;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws MalformedURLException, IOException {

		URL url = new URL("http://www.google.com/test?id=4");

		Socket socket;
		BufferedReader in;
		OutputStreamWriter out;

		try {

			socket = new Socket(InetAddress.getByName("www.siteduzero.com"), 80);
			System.out.println("Demande de connexion");

			// in = new BufferedReader (new InputStreamReader (socket.getInputStream()));

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new OutputStreamWriter(socket.getOutputStream());


			String req = "GET /index.html HTTP/1.1\n"
					+ "Host: www.siteduzero.com\n"
					+ "User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:5.0.1) Gecko/20100101 Firefox/5.0.1\n"
					+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n"
					+ "Accept-Language: fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3\n"
					+ "Accept-Encoding: gzip, deflate\n"
					+ "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\n"
					+ "Connection: keep-alive";
			
			out.write(req);
			out.flush();
			System.out.println("ici");
			String msg = in.readLine();

			System.out.println(msg);

			socket.close();

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
