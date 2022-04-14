package com.parlantos.guild.service;

import com.parlantos.guild.dto.GuildDataDto;
import com.parlantos.guild.models.BasicGuildInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class GuildServiceTests {

    @Autowired
    GuildService guildService;

    @MockBean
    GuildDataDto guildDataDto;

    @Test
    public void fetchAllGuildsForUser() {
        List<BasicGuildInfo> guilds = new LinkedList<>();
        Mockito.when(guildDataDto.fetchAllGuildsForUser(anyString())).thenReturn(guilds);
        assertEquals(guilds, guildDataDto.fetchAllGuildsForUser("1234"));
    }
}
