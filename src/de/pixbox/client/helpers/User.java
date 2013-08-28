package de.pixbox.client.helpers;

import com.google.gson.Gson;


/**
 * @author Max Batt
 * POJO Class for Users.
 * An object of the class can be created out of JSON representation
 */
public class User {
	private String id;
	private String username;
	

	// Getter
	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	
	public String getJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}
}