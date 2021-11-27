package com.parlantos.guild.models;

import java.util.List;

public class GuildContents {


    List<MemberEntity> members;

    List<TextChannelEntity> textChannels;

    List<VoiceChannelEntity> voiceChannels;

    int currentTextChannelIndex;

    List<MessageEntity> messagesForCurrentChannel;


    public List<MemberEntity> getMembers() {
        return members;
    }

    public void setMembers(List<MemberEntity> members) {
        this.members = members;
    }

    public List<TextChannelEntity> getTextChannels() {
        return textChannels;
    }

    public void setTextChannels(List<TextChannelEntity> textChannels) {
        this.textChannels = textChannels;
    }

    public List<VoiceChannelEntity> getVoiceChannels() {
        return voiceChannels;
    }

    public void setVoiceChannels(List<VoiceChannelEntity> voiceChannels) {
        this.voiceChannels = voiceChannels;
    }

    public int getCurrentTextChannelIndex() {
        return currentTextChannelIndex;
    }

    public void setCurrentTextChannelIndex(int currentTextChannelIndex) {
        this.currentTextChannelIndex = currentTextChannelIndex;
    }

    public List<MessageEntity> getMessagesForCurrentChannel() {
        return messagesForCurrentChannel;
    }

    public void setMessagesForCurrentChannel(List<MessageEntity> messagesForCurrentChannel) {
        this.messagesForCurrentChannel = messagesForCurrentChannel;
    }
}
