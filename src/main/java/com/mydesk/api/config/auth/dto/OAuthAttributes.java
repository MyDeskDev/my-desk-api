package com.mydesk.api.config.auth.dto;

import com.mydesk.api.user.domain.Role;
import com.mydesk.api.user.domain.SnsChannel;
import com.mydesk.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private SnsChannel snsChannel;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name,
                           String email, String picture, SnsChannel snsChannel) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.snsChannel = snsChannel;
    }

    public static OAuthAttributes of(String registeredId, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        if ("naver".equals(registeredId)) {
            return ofNaver("id", attributes);
        }
        if("kakao".equals(registeredId)) {
            return ofKakao("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .snsChannel(SnsChannel.GOOGLE)
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .snsChannel(SnsChannel.NAVER)
                .build();
    }

    public static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> accountAttributes = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profileAttributes = (Map<String, Object>) accountAttributes.get("profile");
        return OAuthAttributes.builder()
                .email((String) accountAttributes.get("email"))
                .name((String) profileAttributes.get("nickname"))
                .picture((String) profileAttributes.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .snsChannel(SnsChannel.KAKAO)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .snsChannel(snsChannel)
                .build();
    }
}
