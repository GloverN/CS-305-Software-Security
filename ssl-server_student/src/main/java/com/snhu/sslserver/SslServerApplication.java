package com.snhu.sslserver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController{  
    @RequestMapping("/hash")
    public String myHash(){
    	String data = "Hello Nicholas Glover!";
    	
    	MessageDigest algo;
    	byte[] hash;
    	String hashInHex = "";
    	
    	try {
			algo = MessageDigest.getInstance("SHA3-512");
			
			hash = algo.digest(data.getBytes());
			
			hashInHex = bytesToHex(hash);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String result = "<p>Data: " + data + "<p>Algorithm used: SHA3-512"
    				+ "<p>Checksum: " + hashInHex;
    	
        return result;
    }
    
    public String bytesToHex(byte[] bytes) {
    	StringBuilder builder = new StringBuilder();
    	
    	for (byte b : bytes) {
    		builder.append(String.format("%02x", b));
    	}
    	return builder.toString();
    }
}