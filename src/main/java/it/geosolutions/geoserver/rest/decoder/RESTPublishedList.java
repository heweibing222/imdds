/*
 *  GeoServer-Manager - Simple Manager Library for GeoServer
 *  
 *  Copyright (C) 2013 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package it.geosolutions.geoserver.rest.decoder;

import it.geosolutions.geoserver.rest.decoder.utils.JDOMBuilder;
import org.jdom.Element;


/**
 * Parse <TT>published</TT>s returned as XML REST objects.
 * 
 * This is the XML REST representation:
 * <pre>{@code
  <publishables>
    <published type="layer">
      <name>sfdem</name>
      <atom:link xmlns:atom="http://www.w3.org/2005/Atom" rel="alternate" href="http://localhost:8080/geoserver/rest/layers/sfdem.xml" type="application/xml"/>
    </published>
    <published type="layer">
      <name>bugsites</name>
      <atom:link xmlns:atom="http://www.w3.org/2005/Atom" rel="alternate" href="http://localhost:8080/geoserver/rest/layers/bugsites.xml" type="application/xml"/>
    </published>
  </publishables>
 * }</pre>
 * 
 * @author Davide Savazzi (geo-solutions.it)
 */
public class RESTPublishedList extends RESTAbstractList<RESTPublished> {

    public static RESTPublishedList build(String response) {
        Element elem = JDOMBuilder.buildElement(response);
        return elem == null ? null : new RESTPublishedList(elem);
    }

    protected RESTPublishedList(Element list) {
        super(list);
    }
    
    protected RESTPublished createElement(Element el) {
        return new RESTPublished(el);
    }
}