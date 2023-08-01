package Validators;

import java.util.regex.Pattern;
import Model.Client;
public class NameValidator implements Validator<Client> {
    private static final String NAME_PATTERN = "^[A-Z][a-z]*( [A-Z][a-z]*)*$";
    public void validate(Client t) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
    }
}
