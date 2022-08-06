package pl.rb.repository;

import pl.rb.model.OrderBook;

import java.util.Collection;
import java.util.Optional;

public interface IBinanceRepository {

    Optional<OrderBook> getOrderBook(String instrument);
    Collection<OrderBook> getOrderBooks();
    void addOrUpdateOrderBook(String instrument, OrderBook orderBook);
}
