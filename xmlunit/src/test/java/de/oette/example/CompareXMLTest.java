package de.oette.example;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class CompareXMLTest {

    private static final String XML_HEADER_AND_WHITESPACE = "<hello>\n <world> </world> </hello>\n";
    private static final String XML_CONTENT_ONLY = "<hello><world></world></hello>";

    @Test
    public void testEquals() throws IOException, SAXException {
        Document document1 = XMLUnit.buildTestDocument(XML_HEADER_AND_WHITESPACE);
        Document document2 = XMLUnit.buildTestDocument(XML_CONTENT_ONLY);

        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setNormalize(true);

        Diff diff = XMLUnit.compareXML(document1, document2);
        assertTrue(diff.identical());
    }

}
