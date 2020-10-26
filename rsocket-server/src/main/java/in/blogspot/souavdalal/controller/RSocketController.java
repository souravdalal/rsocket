package in.blogspot.souavdalal.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import in.blogspot.souavdalal.model.Message;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class RSocketController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	  @MessageMapping("request-response")
	    Message requestResponse(Message request) {
		  
		  log.info("inside requestResponse method");
		 String ts= jdbcTemplate.queryForObject("select sysdate() ", String.class);
		  
	            log.info("Received request-response request: {}", request);
	            // create a single Message and return it
	            return new Message("Server", "Response from Server @ "+ts);
	    }
	  
	  @MessageMapping("fire-and-forget")
	    public void fireAndForget(Message request) throws InterruptedException {
		  
		  	TimeUnit.SECONDS.sleep(2);
		  
	        log.info("Received fire-and-forget request: {}", request);
	    }

}
