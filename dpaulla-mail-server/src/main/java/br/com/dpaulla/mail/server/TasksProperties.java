package br.com.dpaulla.mail.server;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tasks")
public class TasksProperties {

	 	private String location = "src/main/";

	    public String getLocation() {
	        return location;
	    }
	
	    public void setLocation(String location) {
	        this.location = location;
	    }
}
