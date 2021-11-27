package com.parlantos.guild.models;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

public class MemberEntity {

  private BigInteger id;

  private String username;

  private String displayName;

  private String nameTag;

  private LocalDateTime createdAt;

  private String email;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MemberEntity that = (MemberEntity) o;
    return id.equals(that.id) && Objects.equals(username, that.username) && Objects.equals(displayName, that.displayName) && Objects.equals(nameTag, that.nameTag) && Objects.equals(createdAt, that.createdAt) && Objects.equals(email, that.email) && Objects.equals(oktaId, that.oktaId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, displayName, nameTag, createdAt, email, oktaId);
  }

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getNameTag() {
    return nameTag;
  }

  public void setNameTag(String nameTag) {
    this.nameTag = nameTag;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getOktaId() {
    return oktaId;
  }

  public void setOktaId(String oktaId) {
    this.oktaId = oktaId;
  }

  private String oktaId;

  private String avatar;

  BigInteger discordId;

  Boolean discordFlag;
}
