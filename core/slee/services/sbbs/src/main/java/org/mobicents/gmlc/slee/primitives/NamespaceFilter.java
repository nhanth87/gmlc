package org.mobicents.gmlc.slee.primitives;

import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.Attributes;

/**
 * @author <a href="mailto:enmanuelcalero61@gmail.com"> Enmanuel Calero </a>
 */
public class NamespaceFilter extends XMLFilterImpl {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws org.xml.sax.SAXException {
        super.startElement("", localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws org.xml.sax.SAXException {
        super.endElement("", localName, qName);
    }

    @Override
    public void startPrefixMapping(String prefix, String uri)
            throws org.xml.sax.SAXException {
    }
}
