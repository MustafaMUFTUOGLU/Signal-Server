package org.whispersystems.textsecuregcm.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.whispersystems.textsecuregcm.util.SystemMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class FixerClient {

  private final String     apiKey;
  private final HttpClient client;

  public FixerClient(HttpClient client, String apiKey) {
    this.apiKey = apiKey;
    this.client = client;
  }

  public Map<String, BigDecimal> getConversionsForBase(String base) throws FixerException {
    try {
      URI uri = URI.create("https://api.apilayer.com/fixer/latest?symbols=" + base + "&base=" + base);

      HttpResponse<String> response =  client.send(HttpRequest.newBuilder()
      .GET()
                                                              .uri(uri)
                                                              .header("apikey",apiKey)
                                                              .build(),
                                                   HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() < 200 || response.statusCode() >= 300) {
        throw new FixerException("Bad response: " + response.statusCode() + " " + response.toString());
      }

      FixerResponse parsedResponse = SystemMapper.getMapper().readValue(response.body(), FixerResponse.class);

      if (parsedResponse.success) return parsedResponse.rates;
      else                        throw new FixerException("Got failed response!");
    } catch (IOException | InterruptedException e) {
      throw new FixerException(e);
    }
  }

  private static class FixerResponse {

    @JsonProperty
    private boolean success;

    @JsonProperty
    private long timestamp;

    @JsonProperty
    private String base;

    @JsonProperty
    private String date;

    @JsonProperty
    private Map<String, BigDecimal> rates;

  }

  public static class FixerException extends IOException {
    public FixerException(String message) {
      super(message);
    }

    public FixerException(Exception exception) {
      super(exception);
    }
  }

}
