package com.parlantos.guild.models;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class GuildEntity {
  private BigInteger id;

  private LocalDateTime createdAt;

  private String name;

  private String description;

  private String image;

  private BigInteger discordGuildId;

  public BigInteger getDiscordGuildId() {
    return discordGuildId;
  }

  public void setDiscordGuildId(BigInteger discordGuildId) {
    this.discordGuildId = discordGuildId;
  }

  public Boolean getPublicFlag() {
    return publicFlag;
  }

  public void setPublicFlag(Boolean publicFlag) {
    this.publicFlag = publicFlag;
  }

  private Boolean publicFlag;
}
