package com.parlantos.guild.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class TextChannelEntity {

  private BigInteger id;

  private LocalDateTime createdAt;

  @JsonIgnore
  private GuildEntity guildEntity;

  private String title;

  private String description;
}
