package Validators;

import Model.Client;
import java.util.regex.Pattern;
public class AddressValidator implements Validator<Client> {
    private static final String ADDRESS_PATTERN = "^[a-zA-Z\\s]+ nr\\.\\s*\\d+$";
    public void validate(Client t) {
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
    }
}
