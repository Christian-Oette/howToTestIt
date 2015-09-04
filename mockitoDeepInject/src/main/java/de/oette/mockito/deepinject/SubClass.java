package de.oette.mockito.deepinject;

import javax.inject.Inject;

public class SubClass {

    @Inject
    private SubSubClass subSubClass;

    public String getValue()
    {
        return subSubClass.getValue();
    }
}
