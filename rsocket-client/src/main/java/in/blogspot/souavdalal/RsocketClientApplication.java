package in.blogspot.souavdalal;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@SpringBootApplication
public class RsocketClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsocketClientApplication.class, args);
	}
	
	@Bean
	Mono<RSocketRequester> rSocketRequester(RSocketRequester.Builder rSocketRequesterBuilder) {
	    return rSocketRequesterBuilder
	            .rsocketConnector(connector -> connector
	                    .reconnect(Retry.fixedDelay(Integer.MAX_VALUE, Duration.ofSeconds(1))))
	            .connectTcp("localhost", 7000);
	}

}




