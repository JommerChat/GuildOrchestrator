package com.parlantos.guild.dto;

import com.parlantos.guild.models.GuildEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class GuildDataDto {

    @Value("${guildDataService.baseUrl:https://api.parlantos.com/guild/data}")
    private String guildDataServiceBaseUrl;

    WebClient webClient = WebClient.create();

    public List<GuildEntity> fetchAllGuildsForUser(String memberId) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path(guildDataServiceBaseUrl + "/guilds")
                    .queryParam(memberId)
                    .build())
                .retrieve()
                .bodyToFlux(GuildEntity.class)
                .collectList()
                .block();
    }

}
