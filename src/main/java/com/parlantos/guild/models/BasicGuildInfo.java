package com.parlantos.guild.models;

import java.math.BigInteger;

/**
 * Guild info object that contains the basic guild info for initially providing the display of all of the guilds
 */
public class BasicGuildInfo {

    TextChannelEntity textChannelEntity;
    VoiceChannelEntity voiceChannelEntity;
    int currentTextChannelIndex;
    String name;
    String icon;
    String description;
    BigInteger id;
}
