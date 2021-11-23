package com.parlantos.guild.service;

import com.parlantos.guild.dto.GuildDataDto;
import com.parlantos.guild.models.GuildEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GuildService {

    GuildDataDto guildDataDto;

    GuildService(GuildDataDto guildDataDto) {
        this.guildDataDto = guildDataDto;
    }

    public List<GuildEntity> fetchAllGuildsForUser(String memberId) {
        return this.guildDataDto.fetchAllGuildsForUser(memberId);
    }
}
