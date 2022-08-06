package pl.rb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pl.rb.model.BinanceBookTicker;
import pl.rb.model.OrderBook;
import pl.rb.repository.IBinanceRepository;

import javax.websocket.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ClientEndpoint
@Service
public class BinanceClientEndpoint {

    private final IBinanceRepository binanceRepository;
    private final static ObjectMapper mapper = new ObjectMapper();

    public BinanceClientEndpoint(IBinanceRepository binanceRepository) {
        this.binanceRepository = binanceRepository;
    }

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText(getJsonFromFile());
        } catch (IOException ex) {
            Logger.getLogger(BinanceClientEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getJsonFromFile() throws IOException {
        InputStream inputStream = new ClassPathResource("data/subscribe.json").getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    @OnMessage
    public void processMessage(String message) throws JsonProcessingException {
        try {
            BinanceBookTicker bookTicker = mapper.readValue(message, BinanceBookTicker.class);
            OrderBook orderBook = OrderBook.builder()
                    .instrument(bookTicker.getSymbol())
                    .bid(bookTicker.getBid())
                    .ask(bookTicker.getAsk())
                    .build();
            binanceRepository.addOrUpdateOrderBook(orderBook.getInstrument(), orderBook);
        } catch (UnrecognizedPropertyException e) {
            Logger.getLogger("Skipped unknown object");
        }
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

}
