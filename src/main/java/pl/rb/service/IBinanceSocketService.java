package pl.rb.service;

import pl.rb.model.OrderBook;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.util.Collection;

public interface IBinanceSocketService {

    void initializeConnection() throws IOException, DeploymentException;
    Collection<OrderBook> getOrderBookCollection();
}
