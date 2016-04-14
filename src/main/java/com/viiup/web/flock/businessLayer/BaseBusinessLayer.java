package com.viiup.web.flock.businessLayer;

import com.viiup.web.flock.businessLayer.interfaces.IBaseBusinessLayer;
import com.viiup.web.flock.models.UserModel;
import com.viiup.web.flock.models.UserPasswordChangeModel;
import com.viiup.web.flock.models.UserRoleModel;
import com.viiup.web.flock.providers.interfaces.IBaseProvider;
import com.viiup.web.flock.providers.interfaces.IEmailProvider;
import com.viiup.web.flock.providers.interfaces.IUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AbdullahMoyeen on 2/6/16.
 */
@Service
public class BaseBusinessLayer implements IBaseBusinessLayer {

    @Autowired
    IBaseProvider baseProvider;

    @Autowired
    IUserProvider userProvider;

    @Autowired
    IEmailProvider emailProvider;

    public void signUp(UserModel user) throws Exception {

        if (!baseProvider.emailAddressExists(user.getEmailAddress())) {

            String tempPassword = baseProvider.generateTempPassword();

            user.setPassword(tempPassword);
            user.setSalt("");
            user.setPasswordExpired(true);
            user.setAccountStatus("A");

            user = userProvider.insertUser(user);

            UserRoleModel userRole = new UserRoleModel();
            userRole.setUserId(user.getUserId());
            userRole.setRoleName("ROLE_USER");

            userProvider.insertUserRole(userRole);

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            final UserModel finalUser = user;
            final String subject = new String("Welcome to Flock");
            final String messageText = new String("Your temporary password is:<br>" + tempPassword);

            executorService.execute(new Runnable() {
                public void run() {
                    emailProvider.sendEmail(new String[]{finalUser.getEmailAddress()}, subject, messageText);
                }
            });

            executorService.shutdown();
        } else {
            throw new Exception("UserAlreadyExists");
        }
    }

    @Override
    public void resetPassword(final String emailAddress) throws Exception {

        if (baseProvider.emailAddressExists(emailAddress)) {

            UserPasswordChangeModel userPassword = new UserPasswordChangeModel();

            String tempPassword = baseProvider.generateTempPassword();

            userPassword.setEmailAddress(emailAddress);
            userPassword.setNewPassword(tempPassword);

            userProvider.updateUserPassword(userPassword, true);

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            final String subject = new String("Your Flock Account");
            final String messageText = new String("Your temporary password is:<br>" + tempPassword);

            executorService.execute(new Runnable() {
                public void run() {
                    emailProvider.sendEmail(new String[]{emailAddress}, subject, messageText);
                }
            });

            executorService.shutdown();
        } else {
            throw new Exception("UserNotFound");
        }
    }
}
