package de.oette.mockito.voidmanipulate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;
import javax.persistence.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class EntityManagerTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ServiceClass serviceClass;

    @Test
    public void testVoid() {

        Mockito.doAnswer(invocation -> {
            Entity entity = invocation.getArgumentAt(0, Entity.class);
            entity.setId(12L);
            return entity;
        }).when(entityManager).persist(any(Entity.class));

        Entity result = serviceClass.storeEntity();
        assertEquals(result.getId(), 12L);
    }

}