package com.parlantos.guild.models;

import java.util.List;

public class GuildContents {

    GuildEntity guild;

    List<MemberEntity> members;

    List<TextChannelEntity> textChannels;

    List<VoiceChannelEntity> voiceChannels;

    int currentTextChannelIndex;

    List<MessageEntity> messagesForCurrentChannel;

}
