package com.apixandru.casestudy;

import com.apixandru.casestudy.weblogic.securedservice.Opinionator;
import com.apixandru.casestudy.weblogic.securedservice.OpinionatorService;
import org.springframework.stereotype.Service;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @author Alexandru-Constantin Bledea
 * @since Sep 03, 2016
 */
@Service
public class OpinionatorClientImpl implements OpinionatorClient {

    private static final String SERVICE_URL = "http://127.0.1.1:7001/securedservice/OpinionatorService?wsdl";
    private static final String USERNAME = "weblogic";
    private static final String PASSWORD = "weblogic1";

    @Override
    public String askForOpinionAboutName(String name) throws MalformedURLException {
        OpinionatorService opinionatorService = new OpinionatorService(new URL(SERVICE_URL));
        Opinionator opinionatorPort = opinionatorService.getOpinionatorPort();

        Map<String, Object> requestContext = ((BindingProvider) opinionatorPort).getRequestContext();
        requestContext.put("ws-security.username", USERNAME);
        requestContext.put("ws-security.password", PASSWORD);

        return opinionatorPort.askForOpinionAboutName(name);
    }

}
