/**
 * Copyright 2014 MusicRecital Project
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
 * Code developed and licensed here for org.musicrecital packaging was 
 * formulated, copied, written, drafted, or otherwise created by 
 * the MusicRecital project at http://musicrecital.org. This license is 
 * freely available to the general public and is used here because 
 * it provides the foundation for the use, duplication, and sharing 
 * of open source software code. The cummulative code base under this 
 * packaging was not written, maintained, or otherwise deployed by 
 * the Apache Group. This code does not reflect the technological 
 * growth at apache.org or the Apache Software Foundation community 
 * collective.
 *
 */


package org.musicrecital.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * This class is used to represent an address with address,
 * city, province and postal-code information.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version $Id$
 */
@Embeddable
@Indexed
public class Address extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3617859655330969141L;
    private String address;
    private String city;
    private String province;
    private String country;
    private String postalCode;

    @Column(length = 150)
    @Field
    public String getAddress() {
        return address;
    }

    @Column(length = 50)
    @Field
    public String getCity() {
        return city;
    }

    @Column(length = 100)
    @Field
    public String getProvince() {
        return province;
    }

    @Column(length = 100)
    @Field
    public String getCountry() {
        return country;
    }

    @Column(name = "postal_code", length = 15)
    @Field(analyze= Analyze.NO)
    public String getPostalCode() {
        return postalCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Overridden equals method for object comparison. Compares based on hashCode.
     *
     * @param o Object to compare
     * @return true/false based on hashCode
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        final Address address1 = (Address) o;

        return this.hashCode() == address1.hashCode();
    }

    /**
     * Overridden hashCode method - compares on address, city, province, country and postal code.
     *
     * @return hashCode
     */
    public int hashCode() {
        int result;
        result = (address != null ? address.hashCode() : 0);
        result = 29 * result + (city != null ? city.hashCode() : 0);
        result = 29 * result + (province != null ? province.hashCode() : 0);
        result = 29 * result + (country != null ? country.hashCode() : 0);
        result = 29 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }

    /**
     * Returns a multi-line String with key=value pairs.
     *
     * @return a String representation of this class.
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("country", this.country)
                .append("address", this.address)
                .append("province", this.province)
                .append("postalCode", this.postalCode)
                .append("city", this.city).toString();
    }
}
