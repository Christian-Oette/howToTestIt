package de.oette.mockito.deepinject;

import org.junit.Test;

import javax.inject.Inject;

public class ClassUnderTest {

    @Inject
    private SubClass subClass;

    @Test
    public String getValue()
    {
        return subClass.getValue();
    }
}
