package com.parlantos.guild.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parlantos.guild.models.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class GuildDtoTests {

    public static MockWebServer mockWebServer;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static GuildDataDto guildDataDto;

    @BeforeAll
    static void setUp() throws IOException {
        objectMapper.findAndRegisterModules();
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        int port = mockWebServer.getPort();
        guildDataDto =
                new GuildDataDto("http://localhost:" + mockWebServer.getPort() + "/guild/data");
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void fetchAllGuildsForUser() throws JsonProcessingException {
        BasicGuildInfo basicGuildInfo = new BasicGuildInfo();
        basicGuildInfo.setName("testServer");
        basicGuildInfo.setId(new BigInteger("1234"));
        basicGuildInfo.setDescription("Test for basic guild info");
        List<TextChannelEntity> textChannelEntities = new LinkedList<>();
        TextChannelEntity textChannelEntity = new TextChannelEntity();
        textChannelEntity.setId(new BigInteger("1234"));
        textChannelEntities.add(textChannelEntity);
        List<VoiceChannelEntity> voiceChannelEntities = new LinkedList<>();
        VoiceChannelEntity voiceChannelEntity = new VoiceChannelEntity();
        voiceChannelEntity.setId(new BigInteger("1234"));
        voiceChannelEntities.add(voiceChannelEntity);
        basicGuildInfo.setTextChannelEntity(textChannelEntities);
        basicGuildInfo.setVoiceChannelEntity(voiceChannelEntities);
        List<BasicGuildInfo> basicGuildInfos = new LinkedList<>();
        basicGuildInfos.add(basicGuildInfo);
        basicGuildInfos.add(basicGuildInfo);
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(basicGuildInfos))
                .addHeader("Content-Type", "application/json"));
        List<BasicGuildInfo> result = guildDataDto.fetchAllGuildsForUser("1234");
        assertEquals(result, basicGuildInfos);
    }

    @Test
    public void fetchAllGuildsForUserServerError() throws JsonProcessingException {
        ResponseEntity<List<BasicGuildInfo>> response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(response))
                .setResponseCode(500)
                .addHeader("Content-Type", "application/json"));
        List<BasicGuildInfo> result = guildDataDto.fetchAllGuildsForUser("1234");
        assertNull(response.getBody());
    }

    @Test
    public void fetchAllGuildsForUserNoContent() throws JsonProcessingException {
        ResponseEntity<List<BasicGuildInfo>> response = new ResponseEntity<>(new LinkedList<>(), HttpStatus.NO_CONTENT);
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(response))
                .addHeader("Content-Type", "application/json"));
        List<BasicGuildInfo> result = guildDataDto.fetchAllGuildsForUser("1234");
        assertEquals(new LinkedList<>(), response.getBody());
    }

    @Test
    public void fetchAllGuildsForUserHttpTimeout() {
        mockWebServer.enqueue(new MockResponse().throttleBody(0, 100, TimeUnit.SECONDS));
        List<BasicGuildInfo> result = guildDataDto.fetchAllGuildsForUser("1234");
        assertEquals(new LinkedList<>(), result);
    }

    @Test
    public void fetchIdForOktaIdSuccess() {
        mockWebServer.enqueue(new MockResponse().setBody(new BigInteger("12345").toString())
                .addHeader("Content-Type", "application/json"));
        BigInteger userId = guildDataDto.fetchIdForOktaId("1234");
        assertEquals(new BigInteger("12345"), userId);
    }

    @Test
    public void fetchIdForOktaIdNotFound() throws JsonProcessingException {
        ResponseEntity<BigInteger> notFoundResponse = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        mockWebServer.enqueue(new MockResponse().setResponseCode(404)
                .setBody(objectMapper.writeValueAsString(notFoundResponse)));
        assertThrows(WebClientResponseException.class, () -> guildDataDto.fetchIdForOktaId("1234"));
    }

    @Test
    public void fetchTextChannelsForGuildSuccess() throws JsonProcessingException, ExecutionException, InterruptedException {
        LocalDateTime testDateTime = LocalDateTime.now();
        List<TextChannelEntity> textChannelEntities = new LinkedList<>();
        TextChannelEntity textChannelEntity = new TextChannelEntity();
        textChannelEntity.setTitle("general");
        textChannelEntity.setId(new BigInteger("1234"));
        textChannelEntity.setCreatedAt(testDateTime);
        GuildEntity guildEntity = new GuildEntity();
        guildEntity.setId(new BigInteger("1234"));
        guildEntity.setName("Test Server");
        guildEntity.setCreatedAt(testDateTime);
        textChannelEntity.setGuildEntity(guildEntity);
        textChannelEntities.add(textChannelEntity);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setBody(objectMapper.writeValueAsString(textChannelEntities))
                .addHeader("Content-Type", "application/json"));
        CompletableFuture<List<TextChannelEntity>> textChannelsFuture = guildDataDto.fetchTextChannelsForGuild("1234");
        textChannelEntities.get(0).setGuildEntity(null); // set guild entity to null as this property is not serialized
        assertEquals(textChannelEntities, textChannelsFuture.get());
    }

    @Test
    public void fetchMembersForGuildSuccess() throws JsonProcessingException, ExecutionException, InterruptedException {
        LocalDateTime testDateTime = LocalDateTime.now();
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(new BigInteger("1234"));
        memberEntity.setUsername("TestName");
        memberEntity.setNameTag("1234");
        memberEntity.setCreatedAt(testDateTime);
        List<MemberEntity> memberEntities = new LinkedList<>();
        memberEntities.add(memberEntity);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setBody(objectMapper.writeValueAsString(memberEntities))
                .addHeader("Content-Type", "application/json"));
        CompletableFuture<List<MemberEntity>> resultMembers = guildDataDto.fetchMembersForGuild("1234");
        assertEquals(memberEntities, resultMembers.get());
    }

    @Test
    public void fetchVoiceChannelsForGuildSuccess() throws JsonProcessingException, ExecutionException, InterruptedException {
        LocalDateTime testDateTime = LocalDateTime.now();
        VoiceChannelEntity voiceChannelEntity = new VoiceChannelEntity();
        GuildEntity guildEntity = new GuildEntity();
        guildEntity.setId(new BigInteger("1234"));
        guildEntity.setName("Test Server");
        guildEntity.setCreatedAt(testDateTime);
        voiceChannelEntity.setId(new BigInteger("1234"));
        voiceChannelEntity.setTitle("TestVoiceChannel");
        voiceChannelEntity.setCreatedAt(testDateTime);
        voiceChannelEntity.setGuildEntity(guildEntity);
        List<VoiceChannelEntity> voiceChannelEntities = new LinkedList<>();
        voiceChannelEntities.add(voiceChannelEntity);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setBody(objectMapper.writeValueAsString(voiceChannelEntities))
                .addHeader("Content-Type", "application/json"));
        voiceChannelEntities.get(0).setGuildEntity(null); // set guild entity to null as this property is not serialized
        CompletableFuture<List<VoiceChannelEntity>> result = guildDataDto.fetchVoiceChannelsForGuild("1234");
        assertEquals(voiceChannelEntities, result.get());
    }

    @Test
    public void fetchMessagesForTextChannelSuccess() throws JsonProcessingException, ExecutionException, InterruptedException {
        LocalDateTime testDateTime = LocalDateTime.now();
        List<MessageEntity> messageEntities = new LinkedList<>();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(new BigInteger("1234"));
        messageEntity.setCreatedAt(testDateTime);
        messageEntity.setContent("Test Message");
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(new BigInteger("1234"));
        memberEntity.setUsername("TestName");
        memberEntity.setNameTag("1234");
        memberEntity.setCreatedAt(testDateTime);
        messageEntity.setMemberEntity(memberEntity);
        messageEntities.add(messageEntity);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setBody(objectMapper.writeValueAsString(messageEntities))
                .addHeader("Content-Type", "application/json"));
        CompletableFuture<List<MessageEntity>> result = guildDataDto.fetchMessagesForTextChannel("1234", "50");
        assertEquals(messageEntities, result.get());
    }
}
