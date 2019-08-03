package br.com.home.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Classes utilitarias de persistencia.
 */
public class JPAUtils {

    public static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

    public static EntityManager getEntityManager(){
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }
}
