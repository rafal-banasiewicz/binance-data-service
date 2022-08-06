package pl.rb.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.rb.model.OrderBook;
import pl.rb.repository.IBinanceRepository;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Objects;

@Service
public class BinanceSocketService implements IBinanceSocketService {

    private final Environment env;
    private final IBinanceRepository binanceRepository;

    public BinanceSocketService(Environment env, IBinanceRepository binanceRepository) {
        this.env = env;
        this.binanceRepository = binanceRepository;
    }

    @Override
    public void initializeConnection() throws IOException, DeploymentException {
        ContainerProvider.getWebSocketContainer().connectToServer(
                new BinanceClientEndpoint(binanceRepository),
                URI.create(Objects.requireNonNull(env.getProperty("address")))
        );
    }

    @Override
    public Collection<OrderBook> getOrderBookCollection() {
        return binanceRepository.getOrderBooks();
    }
}
