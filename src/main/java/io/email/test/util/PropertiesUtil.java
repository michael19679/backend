package io.email.test.util;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

public class PropertiesUtil {

    public static Store getStore(String userName, String password, String pop3Host) throws MessagingException {
        Properties properties = new Properties();
        Session emailSession = Session.getDefaultInstance(properties);
        emailSession.setDebug(true);

        Store store = emailSession.getStore("pop3s");
        store.connect(pop3Host, userName, password);
        return store;
    }
}
