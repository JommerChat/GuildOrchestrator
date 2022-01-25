package com.parlantos.guild.models;

import java.math.BigInteger;
import java.util.List;

/**
 * Guild info object that contains the basic guild info for initially providing the display of all of the guilds
 */
public class BasicGuildInfo {

    List<TextChannelEntity> textChannelEntity;
    List<VoiceChannelEntity> voiceChannelEntity;
    int currentTextChannelIndex;
    String name;
    String icon;
    String description;
    BigInteger id;
}
