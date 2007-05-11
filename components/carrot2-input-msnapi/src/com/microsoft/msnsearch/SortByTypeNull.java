
// Generated file. Do not edit by hand.

package com.microsoft.msnsearch;

public class SortByTypeNull implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SortByTypeNull(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Default = "Default";
    public static final java.lang.String _Relevance = "Relevance";
    public static final java.lang.String _Distance = "Distance";
    public static final SortByTypeNull Default = new SortByTypeNull(_Default);
    public static final SortByTypeNull Relevance = new SortByTypeNull(_Relevance);
    public static final SortByTypeNull Distance = new SortByTypeNull(_Distance);
    public java.lang.String getValue() { return _value_;}
    public static SortByTypeNull fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        SortByTypeNull enumeration = (SortByTypeNull)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static SortByTypeNull fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SortByTypeNull.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/MSNSearch/2005/09/fex", "SortByType>null"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}