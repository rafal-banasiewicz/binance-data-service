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
public class BinanceStreamData {

    @JsonProperty("stream")
    String stream;

    @JsonProperty("data")
    BinanceBookTicker data;

}
