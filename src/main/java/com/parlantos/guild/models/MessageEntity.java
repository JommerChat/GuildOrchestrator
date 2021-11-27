package com.parlantos.guild.models;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

public class MessageEntity {
  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MessageEntity that = (MessageEntity) o;
    return id.equals(that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(content, that.content) && Objects.equals(memberEntity, that.memberEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, content, memberEntity);
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public MemberEntity getMemberEntity() {
    return memberEntity;
  }

  public void setMemberEntity(MemberEntity memberEntity) {
    this.memberEntity = memberEntity;
  }

  public TextChannelEntity getTextChannelEntity() {
    return textChannelEntity;
  }

  public void setTextChannelEntity(TextChannelEntity textChannelEntity) {
    this.textChannelEntity = textChannelEntity;
  }

  private BigInteger id;

  private LocalDateTime createdAt;

  private String content;

  private MemberEntity memberEntity;

  private TextChannelEntity textChannelEntity;
}
