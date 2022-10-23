package com.mydesk.api.post.dto;

import com.mydesk.api.user.domain.Age;
import com.mydesk.api.user.domain.BloodType;
import com.mydesk.api.user.domain.Gender;
import com.mydesk.api.user.domain.MBTI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TypeResponseDto {

    public static Map<String, List<Map<String, String>>> getTypes() {
        Map<String, List<Map<String, String>>> typeMap = new HashMap<>();

        List<Map<String, String>> genderTypes = new ArrayList<>();
        for (Gender gender: Gender.values()) {
            genderTypes.add(Map.of(gender.name(), gender.getKorean()));
        }
        typeMap.put("gender", genderTypes);

        List<Map<String, String>> MBTITypes = new ArrayList<>();
        for (MBTI mbti: MBTI.values()) {
            MBTITypes.add(Map.of(mbti.name(), mbti.name()));
        }
        typeMap.put("mbti", MBTITypes);

        List<Map<String, String>> bloodTypes = new ArrayList<>();
        for (BloodType bloodType: BloodType.values()) {
            bloodTypes.add(Map.of(bloodType.name(), bloodType.getKorean()));
        }
        typeMap.put("bloodType", bloodTypes);

        List<Map<String, String>> ages = new ArrayList<>();
        for (Age age: Age.values()) {
            ages.add(Map.of(age.name(), age.getKorean()));
        }
        typeMap.put("age", ages);

        return typeMap;
    }
}
