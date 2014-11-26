/**
 * Copyright 2014 David L. Whitehurst
 * 
 * Licensed under the Apache License, Version 2.0 
 * (the "License"); You may not use this file except 
 * in compliance with the License. You may obtain a 
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific 
 * language governing permissions and limitations under the 
 * License.
 * 
 */

package org.musicrecital.webapp.taglib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.musicrecital.Constants;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of <code>TagExtraInfo</code> for the <b>constants</b>
 * tag, identifying the scripting object(s) to be made visible.
 *
 * @author Matt Raible
 */
public class ConstantsTei extends TagExtraInfo {
    private final Log log = LogFactory.getLog(ConstantsTei.class);

    /**
     * Return information about the scripting variables to be created.
     * @param data the input data
     * @return VariableInfo array of variable information
     */
    public VariableInfo[] getVariableInfo(TagData data) {
        // loop through and expose all attributes
        List<VariableInfo> vars = new ArrayList<VariableInfo>();

        try {
            String clazz = data.getAttributeString("className");

            if (clazz == null) {
                clazz = Constants.class.getName();
            }

            Class c = Class.forName(clazz);

            // if no var specified, get all
            if (data.getAttributeString("var") == null) {
                Field[] fields = c.getDeclaredFields();

                AccessibleObject.setAccessible(fields, true);

                for (Field field : fields) {
                    String type = field.getType().getName();
                    vars.add(new VariableInfo(field.getName(),
                            ((field.getType().isArray()) ? type.substring(2, type.length() - 1) + "[]" : type),
                            true, VariableInfo.AT_END));
                }
            } else {
                String var = data.getAttributeString("var");
                String type = c.getField(var).getType().getName();
                vars.add(new VariableInfo(c.getField(var).getName(),
                         ((c.getField(var).getType().isArray()) ? type.substring(2, type.length() - 1) + "[]" : type),
                         true, VariableInfo.AT_END));
            }
        } catch (Exception cnf) {
            log.error(cnf.getMessage());
            cnf.printStackTrace();
        }

        return vars.toArray(new VariableInfo[] {});
    }
}
