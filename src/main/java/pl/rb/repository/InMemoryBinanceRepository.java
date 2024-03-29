package pl.rb.repository;

import org.springframework.stereotype.Repository;
import pl.rb.model.OrderBook;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBinanceRepository implements IBinanceRepository {

    private final Map<String, OrderBook> orderBooks;

    public InMemoryBinanceRepository() {
        orderBooks = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<OrderBook> getOrderBook(String symbol) {
        OrderBook orderBook = orderBooks.get(symbol);
        if (orderBook == null) {
            return Optional.empty();
        }
        return Optional.of(orderBook);
    }

    @Override
    public Collection<OrderBook> getOrderBooks() {
        return orderBooks.values();
    }

    @Override
    public void addOrUpdateOrderBook(String symbol, OrderBook orderBook) {
        orderBooks.put(symbol, orderBook);
    }

}