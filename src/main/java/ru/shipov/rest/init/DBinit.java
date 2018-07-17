package ru.shipov.rest.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.shipov.rest.dao.ApplicationDAO;
import ru.shipov.rest.dao.ContactDAO;
import ru.shipov.rest.entity.Application;
import ru.shipov.rest.entity.Contact;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Set;

@Component
public class DBinit implements ApplicationRunner {

    private ContactDAO contactDAO;
    private ApplicationDAO applicationDAO;

    @Autowired
    public DBinit(ContactDAO contactDAO, ApplicationDAO applicationDAO) {
        this.contactDAO = contactDAO;
        this.applicationDAO = applicationDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Contact contact = new Contact();
            Set<Application> applications = contact.getApplications();
            for (int j = 0; j < Math.random() * 5; j++) {
                String productName = new BigInteger(130, new SecureRandom()).toString(36);
                Date dtCreated = new Date((long) (Math.random() * new Date().getTime()));
                Application application = new Application(contact, dtCreated, productName);
                applications.add(application);
            }
            contactDAO.save(contact);
            contact.getApplications().forEach(application -> applicationDAO.save(application));
        }
    }
}
