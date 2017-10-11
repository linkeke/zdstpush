package cn.zdst.fireAlarm.usercenter.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * @author tangqing
 *
 */
@SpringBootApplication
public class pushServer extends SpringBootServletInitializer {
    
	
    public static void main(String[] args) {
        SpringApplication.run(pushServer.class, args);
    }
	

}
