package com.parlantos.guild.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.parlantos.guild.dto.GuildDataDto;
import com.parlantos.guild.models.TokenPayload;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class MemberIdInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(MemberIdInterceptor.class);

    @Autowired
    GuildDataDto guildDataDto;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException, JsonProcessingException {
        try {
            var requestUrl = request.getRequestURL();
            if ((requestUrl.toString().startsWith("http://") || requestUrl.toString().startsWith("https://"))
                    && requestUrl.substring(StringUtils.ordinalIndexOf(requestUrl, "/", 3)).startsWith("/actuator")) {
                        return true;
            }
            String accessToken = request.getHeader("Authorization");
            this.logger.debug("fetched access token from request: {}", accessToken);
            var jwt = decodeJwt(accessToken);
            this.logger.debug("decoded jwt is: {}", jwt.toString());
            BigInteger id = this.guildDataDto.fetchIdForOktaId(jwt.getUid());
            request.setAttribute("memberId", id.toString());
            return true;
        } catch(Exception e) {
            logger.error("Encountered exception: {}", e.getMessage());
            return false;
        }
    }

    TokenPayload decodeJwt(String jwt) throws UnsupportedEncodingException, JsonProcessingException {
        String payloadB64 = jwt.split("\\.")[1];
        String payloadString = new String(Base64.decodeBase64(payloadB64), "UTF-8");
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.readValue(payloadString, TokenPayload.class);
    }
}
