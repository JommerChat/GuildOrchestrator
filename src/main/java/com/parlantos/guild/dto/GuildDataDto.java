package com.parlantos.guild.dto;

import com.parlantos.guild.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Text;

import java.lang.reflect.Member;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class GuildDataDto {

    @Value("${guildDataService.baseUrl:https://api.jommer.chat/guild/data}")
    private String guildDataServiceBaseUrl;

    Logger logger = LoggerFactory.getLogger(GuildDataDto.class);

    WebClient webClient = WebClient.create();

    public List<BasicGuildInfo> fetchAllGuildsForUser(String memberId) {
        try {
            return this.webClient.get()
                    .uri(guildDataServiceBaseUrl + "/guilds", uriBuilder -> uriBuilder
                            .queryParam("memberId", memberId)
                            .build())
                    .retrieve()
                    .bodyToFlux(BasicGuildInfo.class)
                    .collectList()
                    .block();
        } catch(Exception e) {
            this.logger.error("Encountered an error requesting the guilds that a member is in: {}", e.getMessage());
        }
        return null;
    }

    public BigInteger fetchIdForOktaId(String oktaId) {
        try {
            return this.webClient.get()
                    .uri(guildDataServiceBaseUrl + "/member", uriBuilder -> uriBuilder
                            .queryParam("oktaId", oktaId)
                            .build())
                    .retrieve()
                    .bodyToMono(BigInteger.class)
                    .block();
        } catch(Exception e) {
            this.logger.error("Encountered an error requesting the guilds that a member is in: {}", e.getMessage());
        }
        return null;
    }

    @Async
    public CompletableFuture<List<MemberEntity>> fetchMembersForGuild(String guildId) {
        List<MemberEntity> memberList = this.webClient.get()
                .uri(guildDataServiceBaseUrl + "/members", uriBuilder -> uriBuilder.
                        queryParam("id", guildId)
                        .build())
                .retrieve()
                .bodyToFlux(MemberEntity.class)
                .collectList()
                .block();
        return new AsyncResult<>(memberList).completable();
    }

    public CompletableFuture<List<TextChannelEntity>> fetchTextChannelsForGuild(String guildId) {
        List<TextChannelEntity> textChannels = this.webClient.get()
                .uri(guildDataServiceBaseUrl + "/" + guildId + "/textChannels")
                .retrieve()
                .bodyToFlux(TextChannelEntity.class)
                .collectList()
                .block();
        return new AsyncResult<>(textChannels).completable();
    }

    public CompletableFuture<List<VoiceChannelEntity>> fetchVoiceChannelsForGuild(String guildId) {
        List<VoiceChannelEntity> voiceChannels = this.webClient.get()
                .uri(guildDataServiceBaseUrl + "/" + guildId + "/voiceChannels")
                .retrieve()
                .bodyToFlux(VoiceChannelEntity.class)
                .collectList()
                .block();
        return new AsyncResult<>(voiceChannels).completable();
    }

    public CompletableFuture<List<MessageEntity>> fetchMessagesForTextChannel(String textChannelId, String amount) {
        List<MessageEntity> messages = this.webClient.get()
                .uri(guildDataServiceBaseUrl + "/" + textChannelId + "/" + "messages", uriBuilder ->
                        uriBuilder.queryParam("amount", amount)
                .build())
                .retrieve()
                .bodyToFlux(MessageEntity.class)
                .collectList()
                .block();
        return new AsyncResult<>(messages).completable();
    }

}
