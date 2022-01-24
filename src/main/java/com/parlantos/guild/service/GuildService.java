package com.parlantos.guild.service;

import com.parlantos.guild.dto.GuildDataDto;
import com.parlantos.guild.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
public class GuildService {

    Logger logger = LoggerFactory.getLogger(GuildService.class);

    GuildDataDto guildDataDto;

    GuildService(GuildDataDto guildDataDto) {
        this.guildDataDto = guildDataDto;
    }

    public List<BasicGuildInfo> fetchAllGuildsForUser(String memberId) {
        return this.guildDataDto.fetchAllGuildsForUser(memberId);
    }

    public GuildContents fetchInitialContentForGuild(String guildId) throws ExecutionException, InterruptedException {
        try {
            CompletableFuture<List<MemberEntity>> memberFuture = this.guildDataDto.fetchMembersForGuild(guildId);
            CompletableFuture<List<TextChannelEntity>> textChannelFuture = this.guildDataDto.fetchTextChannelsForGuild(guildId);
            CompletableFuture<List<VoiceChannelEntity>> voiceChannelFuture = this.guildDataDto.fetchVoiceChannelsForGuild(guildId);
            List<TextChannelEntity> textChannels = textChannelFuture.get();
            CompletableFuture<List<MessageEntity>> messages = new CompletableFuture<>();
            if (!textChannels.isEmpty()) {
                messages = this.guildDataDto.fetchMessagesForTextChannel(textChannels.get(0).getId().toString(), "100");
            }
            List<MemberEntity> members = memberFuture.get();
            List<VoiceChannelEntity> voiceChannels = voiceChannelFuture.get();
            GuildContents guildContents = new GuildContents();
            guildContents.setMembers(members);
            guildContents.setCurrentTextChannelIndex(0);
            guildContents.setMessagesForCurrentChannel(messages.get());
            guildContents.setTextChannels(textChannels);
            guildContents.setVoiceChannels(voiceChannels);
            return guildContents;
        } catch(Exception e) {
            this.logger.error("Encountered exception: {}", e.getMessage());
        }
        return null;
    }
}
