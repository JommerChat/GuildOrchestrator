package com.parlantos.guild.dto;

import com.parlantos.guild.models.GuildEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class GuildDataDto {

    @Value("${guildDataService.baseUrl:https://api.parlantos.com/guild/data}")
    private String guildDataServiceBaseUrl;

    Logger logger = LoggerFactory.getLogger(GuildDataDto.class);

    WebClient webClient = WebClient.create();

    public List<GuildEntity> fetchAllGuildsForUser(String memberId) {
        try {
            return this.webClient.get()
                    .uri(guildDataServiceBaseUrl + "/guilds", uriBuilder -> uriBuilder
                            .queryParam("memberId", memberId)
                            .build())
                    .retrieve()
                    .bodyToFlux(GuildEntity.class)
                    .collectList()
                    .block();
        } catch(Exception e) {
            this.logger.error("Encountered an error requesting the guilds that a member is in: {}", e.getMessage());
        }
        return null;
    }

}
