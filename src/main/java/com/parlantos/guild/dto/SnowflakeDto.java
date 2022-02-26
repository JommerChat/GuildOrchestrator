package com.parlantos.guild.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Component
public class SnowflakeDto {

  private final Logger logger = LoggerFactory.getLogger(SnowflakeDto.class);

  private final String snowflakeUrl;

  SnowflakeDto(@Value("${snowflake.base.url:https://api.jommer.chat}") String snowflakeUrl) {
    this.snowflakeUrl = snowflakeUrl;
  }

  private final WebClient webClient = WebClient.create();

  public Mono<BigInteger> getSnowflakeId() {
    logger.info("Snowflake base url is: {}", snowflakeUrl);
    return webClient.get()
      .uri(snowflakeUrl + "/snowflake")
      .retrieve()
      .bodyToMono(BigInteger.class);
  }
}
