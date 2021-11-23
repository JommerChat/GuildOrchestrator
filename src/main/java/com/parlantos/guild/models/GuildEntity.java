package com.parlantos.guild.models;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GuildEntity that = (GuildEntity) o;
    return id.equals(that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(image, that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, name, description, image);
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}
