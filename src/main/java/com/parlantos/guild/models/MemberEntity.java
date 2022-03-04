package com.parlantos.guild.models;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class MemberEntity {

  private BigInteger id;

  private String username;

  private String displayName;

  private String nameTag;

  private LocalDateTime createdAt;

  private String email;

  private String oktaId;

  private String avatar;

  BigInteger discordId;

  Boolean discordFlag;
}
