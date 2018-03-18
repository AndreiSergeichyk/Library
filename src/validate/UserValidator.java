package validate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserValidator {

    private static final UserValidator INSTANCE = new UserValidator();
    private static final int DESCRIPTION_MAX_LENGTH = 256;

    public List<String> validate(String... values) {
        List<String> errors = new ArrayList<>();
        for (String value : values) {
            if (StringUtil.isEmpty(value) || value.length() > DESCRIPTION_MAX_LENGTH) {
                errors.add(" is empty or too long");
            }
        }

        return errors;
    }

    public static UserValidator getInstance() {
        return INSTANCE;
    }
}
