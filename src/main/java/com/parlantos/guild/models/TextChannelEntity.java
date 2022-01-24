package com.parlantos.guild.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

public class TextChannelEntity {

  private BigInteger id;

  private LocalDateTime createdAt;

  @JsonIgnore
  private GuildEntity guildEntity;

  private String title;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TextChannelEntity that = (TextChannelEntity) o;
    return id.equals(that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(guildEntity, that.guildEntity) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, guildEntity, title, description);
  }

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public GuildEntity getGuildEntity() {
    return guildEntity;
  }

  public void setGuildEntity(GuildEntity guildEntity) {
    this.guildEntity = guildEntity;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  private String description;
}
