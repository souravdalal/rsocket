package in.blogspot.souavdalal.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import in.blogspot.souavdalal.model.Message;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@ShellComponent
public class RSocketShellClient {

	
	 @Autowired
	 private Mono<RSocketRequester> requesterMono;
	


	@ShellMethod("Send one request. One response will be printed.")
	public void requestResponse() throws InterruptedException {
		log.info("\nSending one request. Waiting for one response...");
		
		Message message =  this.requesterMono.flatMap(requester -> requester.route("request-response")
                .data(new Message("CLIENT", "REQUEST"))
                .retrieveMono(Message.class)).block();
		
		
		log.info("\nResponse was: {}", message);
	}

	@ShellMethod("Send one request. No response will be returned.")
	public void fireAndForget() throws InterruptedException {
		log.info("\nFire-And-Forget. Sending one request. Expect no response (check server log)...");
		this.requesterMono.flatMap(requester -> requester.route("fire-and-forget")
                .data(new Message("CLIENT", "FIRE_AND_FORGET"))
                .send()).block();
	
	
	}
}
