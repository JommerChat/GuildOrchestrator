package com.parlantos.guild.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

/**
 * Guild info object that contains the basic guild info for initially providing the display of all of the guilds
 */
@Data
public class BasicGuildInfo {
    private List<TextChannelEntity> textChannelEntity;
    private List<VoiceChannelEntity> voiceChannelEntity;
    private int currentTextChannelIndex;
    private String name;
    private String icon;
    private String description;
    private BigInteger id;
}
