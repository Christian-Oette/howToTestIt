package de.oette.mockito.deepinject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * Sometimes you might want to test without mocks and use multiple spy objects instead
 * It is tricky to inject. Here is an example.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestDeepInjection {

    @InjectMocks
    private ClassUnderTest classUnderTest;

    @Spy
    @InjectMocks
    private SubClass subClass = new SubClass(); //combination of @Spy and @InjectMocks needs to be created with new

    @Spy
    private SubSubClass subSubClass;

    @Test
    public void test()
    {
        String value = classUnderTest.getValue();
        assertEquals("Value of SubSubClass", value);
    }
}
