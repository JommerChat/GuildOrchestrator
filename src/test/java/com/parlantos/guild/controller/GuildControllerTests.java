package com.parlantos.guild.controller;

import com.parlantos.guild.models.*;
import com.parlantos.guild.service.GuildService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class GuildControllerTests {

    @Autowired
    GuildController guildController;

    @MockBean
    GuildService guildService;

    @Test
    public void fetchAllGuildsForUserTest() {
        List<BasicGuildInfo> basicGuildInfos = new LinkedList<>();
        BasicGuildInfo basicGuildInfo = new BasicGuildInfo();
        basicGuildInfo.setName("TestGuild");
        basicGuildInfo.setId(new BigInteger("1234"));
        basicGuildInfo.setIcon("iconUrl");
        basicGuildInfos.add(basicGuildInfo);
        Mockito.when(guildService.fetchAllGuildsForUser(anyString())).thenReturn(basicGuildInfos);
        assertEquals(new ResponseEntity<>(basicGuildInfos, HttpStatus.OK), guildController.fetchAllGuildsForUser("1234"));
    }

    @Test
    public void fetchInitialInfoForGuildTest() throws ExecutionException, InterruptedException {
        GuildContents guildContents = new GuildContents();
        TextChannelEntity textChannelEntity = new TextChannelEntity();
        textChannelEntity.setId(new BigInteger("1234"));
        textChannelEntity.setTitle("general");
        List<TextChannelEntity> textChannelEntities = new LinkedList<>();
        textChannelEntities.add(textChannelEntity);
        VoiceChannelEntity voiceChannelEntity = new VoiceChannelEntity();
        voiceChannelEntity.setId(new BigInteger("1234"));
        voiceChannelEntity.setTitle("general");
        List<VoiceChannelEntity> voiceChannelEntities = new LinkedList<>();
        voiceChannelEntities.add(voiceChannelEntity);
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(new BigInteger("1234"));
        memberEntity.setDisplayName("John");
        memberEntity.setUsername("John#1234");
        List<MemberEntity> memberEntities = new LinkedList<>();
        memberEntities.add(memberEntity);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(new BigInteger("1234"));
        messageEntity.setContent("Test Message");
        messageEntity.setMemberEntity(memberEntity);
        messageEntity.setTextChannelEntity(textChannelEntity);
        List<MessageEntity> messageEntities = new LinkedList<>();
        messageEntities.add(messageEntity);
        guildContents.setMembers(memberEntities);
        guildContents.setCurrentTextChannelIndex(0);
        guildContents.setMessagesForCurrentChannel(messageEntities);
        guildContents.setTextChannels(textChannelEntities);
        guildContents.setVoiceChannels(voiceChannelEntities);
        Mockito.when(guildService.fetchInitialContentForGuild(anyString())).thenReturn(guildContents);
        assertEquals(new ResponseEntity<>(guildContents, HttpStatus.OK), guildController.fetchInitialInfoForGuild("1234"));
    }
}
