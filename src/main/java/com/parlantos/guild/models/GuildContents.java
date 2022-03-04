package com.parlantos.guild.models;

import lombok.Data;

import java.util.List;

@Data
public class GuildContents {
    List<MemberEntity> members;

    List<TextChannelEntity> textChannels;

    List<VoiceChannelEntity> voiceChannels;

    int currentTextChannelIndex;

    List<MessageEntity> messagesForCurrentChannel;
}
