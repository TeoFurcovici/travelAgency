package service;


import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterLogInValidator  {

    private static final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    private static final String PASS_PATTERN = //Minimum eight characters, at least one uppercase letter, one lowercase letter and one number:
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    private static final String DATE_PATTERN =
            "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    private static final String NUMBER_PATTERN =
            "^(?:[1-9]|\\d{2,3}|[1-4]\\d{3}|500)$";


    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern patternPas = Pattern.compile(PASS_PATTERN);
    private static final Pattern patternDate = Pattern.compile(DATE_PATTERN);
    private static final Pattern patternNr = Pattern.compile(NUMBER_PATTERN);

    public  boolean isValidUsername(final String username) {
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public  boolean isValidPass(final String pass) {
        Matcher matcher = patternPas.matcher(pass);
        return matcher.matches();
    }
    public  boolean isValidNr(final String nr) {
        Matcher matcher = patternNr.matcher(nr);
        return matcher.matches();
    }
    public  boolean isValidDate(final String date) {
        Matcher matcher = patternDate.matcher(date);
        return matcher.matches();
    }
    public  boolean endDateAfterStartDate(LocalDate endDate, LocalDate startDate) {
        return endDate.isAfter(startDate);
    }

}
