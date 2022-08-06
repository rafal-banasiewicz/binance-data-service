package pl.rb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BinanceBookTicker {

    @JsonProperty("u")
    String updateId;
    @JsonProperty("s")
    String symbol;
    @JsonProperty("b")
    String bid;
    @JsonProperty("B")
    String bidQuantity;
    @JsonProperty("a")
    String ask;
    @JsonProperty("A")
    String askQuantity;

}
