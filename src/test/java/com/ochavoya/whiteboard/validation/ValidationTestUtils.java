package com.ochavoya.whiteboard.validation;

import com.ochavoya.whiteboard.dto.UserLoginDTO;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationTestUtils <T> {



    public static final Set<String> buildSet(String... args) {
        Set<String> set = new HashSet<>();
        for (String field : args) {
            set.add(field);
        }
        return set;
    }

    public static final Set<String> emptySet = buildSet();

    public static <E> Set<String> getInvalidFields(Set<ConstraintViolation<E>> violations) {
        return violations.stream().map (violation->violation.getPropertyPath().toString()).collect(Collectors.toSet());
    }

}
