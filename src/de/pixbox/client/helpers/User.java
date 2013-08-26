package de.pixbox.client.helpers;

import com.google.gson.Gson;


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