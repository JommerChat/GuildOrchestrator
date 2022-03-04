package com.parlantos.guild.models;

import lombok.Data;

@Data
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
}
