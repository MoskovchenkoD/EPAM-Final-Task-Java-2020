package ua.nure.moskovchenko.service;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.exception.Messages;

import java.security.NoSuchAlgorithmException;

//unused
public class RegistrationValidator {

    private static final Logger LOG = Logger.getLogger(RegistrationValidator.class);

    public void userRegDataValidation(User user) {
        //TODO: обработать данные
        try {
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            LOG.error(Messages.ERR_NO_PASSWORD_HASHING_ALG, e);
        }
        //
    }
}
