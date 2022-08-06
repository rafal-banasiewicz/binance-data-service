package pl.rb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.rb.service.BinanceSocketService;

import javax.websocket.DeploymentException;
import java.io.IOException;

@SpringBootApplication
public class BinanceApplication {

    public static void main(String[] args) throws DeploymentException, IOException {
        ApplicationContext applicationContext = SpringApplication.run(BinanceApplication.class, args);
        BinanceSocketService socketService = applicationContext.getBean(BinanceSocketService.class);
        socketService.initializeConnection();
    }
}
