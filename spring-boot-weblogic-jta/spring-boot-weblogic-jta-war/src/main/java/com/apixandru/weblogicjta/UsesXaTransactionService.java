package com.apixandru.weblogicjta;

public interface UsesXaTransactionService {

    String doJmsAndDb();

    String doDb();

    String doJms();

}
