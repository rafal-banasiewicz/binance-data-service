package pl.rb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rb.model.OrderBook;
import pl.rb.service.BinanceSocketService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/orderBook")
public class BinanceController {

    private final BinanceSocketService socketService;

    public BinanceController(BinanceSocketService socketService) {
        this.socketService = socketService;
    }

    @GetMapping
    public Collection<OrderBook> getOrderBook() {
        return socketService.getOrderBookCollection();
    }

    @GetMapping(value = "/{symbol}")
    public ResponseEntity<OrderBook> getOrderBookBySymbol(@PathVariable String symbol) {
        return ResponseEntity.of(socketService.getOrderBook(symbol));
    }
}
