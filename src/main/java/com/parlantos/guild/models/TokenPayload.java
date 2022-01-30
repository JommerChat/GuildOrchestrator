package com.parlantos.guild.models;

import java.util.Arrays;

public class TokenPayload {

    String ver;
    String jti;
    String iss;
    String aud;
    String sub;
    String iat;
    String exp;
    String cid;
    String uid;
    String[] scp;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String[] getScp() {
        return scp;
    }

    public void setScp(String[] scp) {
        this.scp = scp;
    }

    @Override
    public String toString() {
        return "TokenPayload{" +
                "ver='" + ver + '\'' +
                ", jti='" + jti + '\'' +
                ", iss='" + iss + '\'' +
                ", aud='" + aud + '\'' +
                ", sub='" + sub + '\'' +
                ", iat='" + iat + '\'' +
                ", exp='" + exp + '\'' +
                ", cid='" + cid + '\'' +
                ", uid='" + uid + '\'' +
                ", scp=" + Arrays.toString(scp) +
                '}';
    }
}
