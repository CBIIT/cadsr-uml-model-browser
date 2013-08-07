/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.util;

import gov.nih.nci.cadsr.domain.AdministeredComponentContact;

import java.util.Comparator;

public class AdministeredComponentContactComparator implements Comparator{
    public AdministeredComponentContactComparator() {
    }

    public int compare(Object o1, Object o2) {
        if (o1 == null && o2 == null) return 0;
        
        if (o1 == null) return 1;
        if (o2 == null) return -1;  
        if ((o1 instanceof Integer)&&(o2 instanceof Integer)) {
            return ((Integer)o1).compareTo((Integer)o2);
        }
        if ((o1 instanceof String)&&(o2 instanceof String)) {
            return ((String)o1).compareTo((String)o2);
        }        
        if ((o1 instanceof AdministeredComponentContact)&&(o2 instanceof AdministeredComponentContact))
        {
           AdministeredComponentContact a1= (AdministeredComponentContact)o1;
           AdministeredComponentContact a2= (AdministeredComponentContact)o2;
           //Compare Rank
           int rankC= compare(a1.rank, a2.rank);
           if (rankC != 0) return rankC;
           String name1 = null;
           String name2 = null;
           if (a1.getPerson() != null) {
               name1 = a1.getPerson().getLastName();
           }
           if (a1.getOrganization()!= null) {
               name1= a1.getOrganization().getName();
           }
           if (a2.getPerson() != null) {
                name2 = a2.getPerson().getLastName();
           }
           if (a2.getOrganization()!= null) {
                name2= a2.getOrganization().getName();
           }
           return compare(name1, name2);
        }
        return 0;
    }
}
