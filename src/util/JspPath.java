package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JspPath {

    private static final String JSP_TEMPLATE = "/WEB-INF/jsp/%s.jsp";

    public static String get(String jspPAgeNumber){
        return String.format(JSP_TEMPLATE, jspPAgeNumber);
    }
}
