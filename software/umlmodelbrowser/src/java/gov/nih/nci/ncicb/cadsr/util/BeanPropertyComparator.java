package gov.nih.nci.ncicb.cadsr.util;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * This Class allows to sort List of Java Beans by their properties. 
 * It handles property type of String, Float and integer property
 * Uses String "CompareIgnoreCase" to Compare attributes so
 * only string attributes of the Object should be used for sorting.
 * A Primary, Secondary, and Tertiary field can be set. 
 */
public class BeanPropertyComparator implements Comparator,SortableColumnHeader
{

  private String primaryProperty;
  private String secondaryProperty;
  private String tertiaryProperty;
  private Class comparingClass;
  private int order=ASCENDING;
  private boolean defaultOrder;

  public BeanPropertyComparator(Class newComparingClass)
  {
    comparingClass = newComparingClass;
  }
  /**
   * Depending on the order set compares both primary and secondary fields.
   * For order=ASCENDING
   * -ve,0, +ve int value is returned depending on the if Obj1 is greater, equal
   * or lesser than obj2 respectively
   * For order=DESCENDING
   * -ve,0, +ve int value is returned depending on the if Obj1 is lesser, equal
   * or greater than obj2 respectively
   * If the order
   *
   */
  public int compare(Object obj1, Object obj2)
  {
      if(primaryProperty==null)
        return 0;
         
      Object obj1PrimaryFieldValue = getPropertyValue(obj1, primaryProperty);
      Object obj2PrimaryFieldValue = getPropertyValue(obj2,primaryProperty);

      Object obj1SecondaryFieldValue = null;
      Object obj2SecondaryFieldValue = null;

      if (secondaryProperty==null 
      || compare(obj1PrimaryFieldValue, obj2PrimaryFieldValue) !=0) {
        return compareTo(obj1PrimaryFieldValue, obj2PrimaryFieldValue, order);
      }
      
       obj1SecondaryFieldValue = getPropertyValue(obj1,secondaryProperty);
       obj2SecondaryFieldValue = getPropertyValue(obj2,secondaryProperty);

      if (tertiaryProperty == null
         || compare(obj1SecondaryFieldValue, obj2SecondaryFieldValue) !=0) {
        return compareTo(obj1SecondaryFieldValue, obj2SecondaryFieldValue, order);
      }
    
       Object obj1TertiaryFieldValue = getPropertyValue(obj1,tertiaryProperty);
       Object obj2TertiaryFieldValue = getPropertyValue(obj2,tertiaryProperty);
       return compareTo(obj1TertiaryFieldValue, obj2TertiaryFieldValue);
    

  }

  public void setPrimary(String primary)
  {
     primaryProperty=primary;
  }


  public String getPrimary()
  {
      return primaryProperty;
  }

  public void setSecondary(String secondary)
  {
     secondaryProperty=secondary;
  }

  public String getTertiary()
  {
    return tertiaryProperty;
  }
  public void setTertiary(String tertiary)
  {
    tertiaryProperty=tertiary;
  }
  public void setRelativePrimary(String primary)
  {

    if(primaryProperty!=null&& primary.equalsIgnoreCase(primaryProperty))
     {
       return;
     }
     tertiaryProperty=secondaryProperty;
     tertiaryProperty=secondaryProperty;
     primaryProperty=primary;
     if(primaryProperty.equalsIgnoreCase(tertiaryProperty))
     {
       tertiaryProperty=null;
     }
     if(primaryProperty.equalsIgnoreCase(tertiaryProperty))
      {
       tertiaryProperty=null;
      }

  }
  public String getSecondary()
  {
    return this.secondaryProperty;
  }


  public void setOrder(int order)
  {
    this.order = order;
  }


  public int getOrder()
  {
    return order;
  }

  public boolean isDefaultOrder()
  {
    return defaultOrder;
  }

  public void setDefaultOrder(boolean defaultOrder)
  {
    this.defaultOrder = defaultOrder;
  }
  
  private int compareTo(Object o1, Object o2){
  
   if (o1 instanceof String)
       return ((String) o1).compareToIgnoreCase((String)o2);
   else if (o1 instanceof Float && o2 instanceof Float) {
      return  Float.compare((Float) o1, (Float)o2);
   }
   else 
      return (Integer.parseInt(o1.toString()) - Integer.parseInt(o2.toString()));
  }
  
  
  private int compareTo(Object o1, Object o2, int order){
      if (o1 == null && o2 == null) return 0;
      
      if (o1 == null) return 1;
      if (o2 == null) return -1;
      
      if(order==this.ASCENDING)
         return this.compareTo(o1, o2);
      else
         return this.compareTo(o2, o1);
  }
  
  private Object getPropertyValue (Object bean, String property){
      Object returnValue = null;
      try {
         returnValue = PropertyUtils.getProperty(bean, property);
     
     } catch (java.lang.IllegalArgumentException e) {
         
        returnValue = "";
     }
      catch (InvocationTargetException e)
      {
         throw new RuntimeException("Comparator Excception "+e);
      }
      catch (IllegalAccessException e)
      {
        throw new RuntimeException("Comparator Excception "+e);
      }
      catch (java.lang.NoSuchMethodException e) {
         //this could happen for nested properties
         returnValue = "";
         
      }
     return returnValue;
  }
}