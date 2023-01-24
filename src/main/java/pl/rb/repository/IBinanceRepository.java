package pl.rb.repository;

import pl.rb.model.OrderBook;

import java.util.Collection;
import java.util.Optional;

public interface IBinanceRepository {

    Optional<OrderBook> getOrderBook(String symbol);
    Collection<OrderBook> getOrderBooks();
    void addOrUpdateOrderBook(String symbol, OrderBook orderBook);
}
