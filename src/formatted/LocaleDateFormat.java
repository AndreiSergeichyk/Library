package formatted;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.StringUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocaleDateFormat {

    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDate format(String value) {
        LocalDate localDate = null;
        if (!StringUtil.isEmpty(value)) {
            try {
                localDate = LocalDate.parse(value, FORMATER);
            } catch (DateTimeParseException e) {

            }
        }
        return localDate;
    }

    public static String format(LocalDate localDate) {
        String formattedDate = null;
        if (localDate != null) {
            formattedDate = FORMATER.format(localDate);
        }

        return formattedDate;
    }
}
