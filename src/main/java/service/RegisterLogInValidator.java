package service;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterLogInValidator  {

    private static final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    private static final String PASS_PATTERN =
            "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern patternPas = Pattern.compile(PASS_PATTERN);

    public static boolean isValidUsername(final String username) {
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public static boolean isValidPass(final String pass) {
        Matcher matcher = patternPas.matcher(pass);
        return matcher.matches();
    }

}
