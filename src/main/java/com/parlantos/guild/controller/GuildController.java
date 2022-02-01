package com.parlantos.guild.controller;

import com.parlantos.guild.models.BasicGuildInfo;
import com.parlantos.guild.models.GuildContents;
import com.parlantos.guild.models.GuildEntity;
import com.parlantos.guild.service.GuildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/guild")
@CrossOrigin
public class GuildController {

    private GuildService guildService;

    GuildController(GuildService guildService) {
        this.guildService = guildService;
    }

    @GetMapping("fetchGuilds")
    public ResponseEntity<List<BasicGuildInfo>> fetchAllGuildsForUser(@RequestAttribute("memberId") String memberId) {
        return new ResponseEntity<>(this.guildService.fetchAllGuildsForUser(memberId), HttpStatus.OK);
    }

    @GetMapping("/initialInfo")
    public ResponseEntity<GuildContents> fetchInitialInfoForGuild(@RequestParam String guildId) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(this.guildService.fetchInitialContentForGuild(guildId), HttpStatus.OK);
    }

}
