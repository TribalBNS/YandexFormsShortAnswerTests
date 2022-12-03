package guru.qa.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestData {
    static final String LOGIN = "TribalBNS";
    static final String PASSWORD = "TribalForTest1";
    static final String formNameAppender = RandomStringUtils.randomAlphanumeric(5);

public static String formName;
    static Stream<Arguments> commentFormValidationWithDifferentTextLengthTest() {
        return Stream.of(
                Arguments.of(RandomStringUtils.randomAlphanumeric(1)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(2)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(50)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(500))
        );
    }

    static Stream<Arguments> questionIdValidationWithDifferentValidSymbolsLengthTest() {
        return Stream.of(
                Arguments.of(RandomStringUtils.randomAlphanumeric(1)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(2)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(15)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(49)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(50))
        );
    }

    static Stream<Arguments> questionIdValidationWithDifferentInvalidSymbolsLengthTest() {
        return Stream.of(
                Arguments.of(RandomStringUtils.randomAlphanumeric(51)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(52)),
                Arguments.of(RandomStringUtils.randomAlphanumeric(100))
        );
    }
}
