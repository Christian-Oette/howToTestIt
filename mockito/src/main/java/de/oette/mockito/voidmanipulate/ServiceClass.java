package de.oette.mockito.voidmanipulate;

import javax.inject.Inject;

public class ServiceClass {

    @Inject
    private EntityManager entityManager;

    public Entity storeEntity() {
        Entity entity = new Entity();
        entityManager.persist(entity);
        return entity;
    }
}
