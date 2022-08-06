package pl.rb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.rb.model.OrderBook;
import pl.rb.repository.IBinanceRepository;
import pl.rb.service.IBinanceSocketService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class BinanceApplicationTests {

    @MockBean
    private IBinanceRepository binanceRepository;

    @Autowired
    private IBinanceSocketService socketService;

    private List<OrderBook> orderBooks;

    @BeforeEach
    public void init() {
        OrderBook orderBook = new OrderBook("BTCUSDT", "40000.01", "40010.53");
        orderBooks = new ArrayList<>(List.of(orderBook));
        Mockito.when(binanceRepository.getOrderBooks()).thenReturn(orderBooks);
    }

    @AfterEach
    public void teardown() {
        orderBooks.clear();
    }

    @Test
    public void contextLoads() {
        OrderBook orderBook = new OrderBook("BTCUSDT", "40000.01", "40010.53");
        List<OrderBook> orderBooks = socketService.getOrderBookCollection().stream().toList();
        assertEquals(orderBooks.get(0).getInstrument(), orderBook.getInstrument());
        assertEquals(orderBooks.get(0).getBid(), orderBook.getBid());
        assertEquals(orderBooks.get(0).getAsk(), orderBook.getAsk());
        assertEquals(orderBooks.get(0), orderBook);
    }
}