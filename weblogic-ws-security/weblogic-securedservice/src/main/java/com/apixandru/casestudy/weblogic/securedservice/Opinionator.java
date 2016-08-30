package com.apixandru.casestudy.weblogic.securedservice;

import weblogic.jws.Policies;
import weblogic.jws.Policy;

import javax.jws.WebService;

/**
 * @author Alexandru-Constantin Bledea
 * @since Sep 03, 2016
 */
@WebService
@Policies({@Policy(uri = "policy:usernametoken.xml")})
public class Opinionator {

    public String askForOpinionAboutName(String name) {
        return name + "? That's one fine name!";
    }

}
