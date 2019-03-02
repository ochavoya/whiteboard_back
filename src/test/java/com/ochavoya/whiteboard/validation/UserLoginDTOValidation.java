package com.ochavoya.whiteboard.validation;

import com.ochavoya.whiteboard.dto.UserLoginDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.junit.runners.Parameterized.Parameter;

import static com.ochavoya.whiteboard.validation.ValidationTestUtils.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UserLoginDTOValidation {

    private Validator validator;

    @Parameter(0)
    public String testName;

    @Parameter(1)
    public String username;

    @Parameter(2)
    public String password;

    @Parameter(3)
    public Set<String> fieldSet;

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        Set<String> username = buildSet("username");
        Set<String> password = buildSet("password");
        return Arrays.asList(new Object[][]{
                {"Username is shortest", "0", "1234567", emptySet},
                {"Username is longest", "1234567890123456", "1234567", emptySet},
                {"Username is null", null, "1234567", username},
                {"Username is missing", "", "1234567", username},
                {"Username is missing", "", "1234567", username},
                {"Username is too long", "12345678901234567", "1234567", username},
                {"Password is null", "1234567890123456", "123456", password},
                {"Password is short", "1234567890123456", "123456", password},
        });
    }


    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUserLoginDTO() {
        UserLoginDTO testObject = new UserLoginDTO();
        testObject.setUsername(username);
        testObject.setPassword(password);
        Set<ConstraintViolation<UserLoginDTO>> violations = validator.validate(testObject);
        assertEquals(fieldSet, getInvalidFields(violations));
    }
}
