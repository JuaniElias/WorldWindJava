/*
 * Copyright 2006-2009, 2017, 2020 United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All rights reserved.
 * 
 * The NASA World Wind Java (WWJ) platform is licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * NASA World Wind Java (WWJ) also contains the following 3rd party Open Source
 * software:
 * 
 *     Jackson Parser – Licensed under Apache 2.0
 *     GDAL – Licensed under MIT
 *     JOGL – Licensed under  Berkeley Software Distribution (BSD)
 *     Gluegen – Licensed under Berkeley Software Distribution (BSD)
 * 
 * A complete listing of 3rd Party software notices and licenses included in
 * NASA World Wind Java (WWJ)  can be found in the WorldWindJava-v2.2 3rd-party
 * notices and licenses PDF found in code directory.
 */

package gov.nasa.worldwind.ogc.wms;

import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwind.util.xml.XMLEventParserContext;

import javax.xml.stream.events.*;
import java.util.Iterator;

/**
 * Parses a WMS layer LogoURL element. Also used for WMS layer LegendURL elements.
 *
 * @author tag
 * @version $Id: WMSLogoURL.java 1171 2013-02-11 21:45:02Z dcollins $
 */
public class WMSLogoURL extends WMSLayerInfoURL
{
    protected Integer width;
    protected Integer height;

    public WMSLogoURL(String namespaceURI)  {
        super(namespaceURI);

    }

    @Override
    protected void doParseEventAttributes(XMLEventParserContext ctx, XMLEvent event, Object... args)
    {
        super.doParseEventAttributes(ctx, event, args);

        Iterator iter = event.asStartElement().getAttributes();
        if (iter == null)
            return;

        while (iter.hasNext())
        {
            Attribute attr = (Attribute) iter.next();

            if (attr.getName().getLocalPart().equals("width") && attr.getValue() != null)
            {
                Integer i = WWUtil.convertStringToInteger(attr.getValue());
                if (i != null)
                    this.setWidth(i);
            }

            if (attr.getName().getLocalPart().equals("height") && attr.getValue() != null)
            {
                Integer i = WWUtil.convertStringToInteger(attr.getValue());
                if (i != null) {
                	this.setHeight(i);
                } else {
                	System.out.println("fallo parsear la altura para el logo de "+this.namespaceURI);
                }
            }
        }
    }

    public Integer getWidth()
    {
        return width;
    }

    protected void setWidth(Integer width)
    {
        this.width = width;
    }

    public Integer getHeight()
    {
        return height;
    }

    protected void setHeight(Integer height)
    {
        this.height = height;
    }
}
