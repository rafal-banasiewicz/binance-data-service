package pl.rb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rb.model.OrderBook;
import pl.rb.service.BinanceSocketService;

import java.util.Collection;

@RestController
public class BinanceController {

    private final BinanceSocketService socketService;

    public BinanceController(BinanceSocketService socketService) {
        this.socketService = socketService;
    }

    @GetMapping(value = "/getInstruments")
    public Collection<OrderBook> getInstruments() {
        return socketService.getOrderBookCollection();
    }
}
