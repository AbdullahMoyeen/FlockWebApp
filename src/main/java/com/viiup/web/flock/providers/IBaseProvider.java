package com.viiup.web.flock.providers;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
 */
public interface IBaseProvider {

    String generateTempPassword();
    boolean emailAddressExists(String emailAddress);
}
