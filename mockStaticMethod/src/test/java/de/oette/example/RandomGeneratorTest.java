package de.oette.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest( RandomGenerator.class)
public class RandomGeneratorTest {

    @Test
    public void testRandomInt()
    {
        PowerMockito.mockStatic(Math.class);
        Mockito.when(Math.random()).thenReturn(0.512792);
        int result = RandomGenerator.generateRandomFromZeroToHundred();
        assertEquals(51, result);
    }


}
