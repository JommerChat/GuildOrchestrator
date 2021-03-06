package com.parlantos.guild.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class MessageEntity {
  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  private BigInteger id;

  private LocalDateTime createdAt;

  private String content;

  private MemberEntity memberEntity;

  @JsonIgnore
  private TextChannelEntity textChannelEntity;
}
