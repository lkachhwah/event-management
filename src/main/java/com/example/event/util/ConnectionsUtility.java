package com.example.event.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.event.pojo.Data;
import com.google.gson.Gson;

public class ConnectionsUtility {
	private static Gson gson = new Gson();
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0";

	public static Data loadData() throws Exception {

		String url = Constant.BASE_URL + Constant.FACLET_URL;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		System.out.println("\nSending 'GET' request to URL : " + url);
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :  " + responseCode);
		System.out.println("POST Response Message : " + con.getResponseMessage());
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println(response.toString());
			return gson.fromJson(response.toString().trim(), Data.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
