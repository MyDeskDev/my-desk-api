package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.DeskConcept;
import com.mydesk.api.user.domain.Age;
import com.mydesk.api.user.domain.BloodType;
import com.mydesk.api.user.domain.Gender;
import com.mydesk.api.user.domain.MBTI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TypeResponseDto {

    public static Map<String, List<String>> getTypes() {
        Map<String, List<String>> typeMap = new HashMap<>();

        typeMap.put("gender", Arrays.stream(Gender.values()).map(Gender::name).collect(Collectors.toList()));

        typeMap.put("mbti", Arrays.stream(MBTI.values()).map(MBTI::name).collect(Collectors.toList()));

        typeMap.put("bloodType", Arrays.stream(BloodType.values()).map(BloodType::name).collect(Collectors.toList()));

        typeMap.put("age", Arrays.stream(Age.values()).map(Age::name).collect(Collectors.toList()));

        typeMap.put("deskConcept", Arrays.stream(DeskConcept.values()).map(DeskConcept::name).collect(Collectors.toList()));

        return typeMap;
    }
}
