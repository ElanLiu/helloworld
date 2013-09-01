package com.mycompany.practice;

public class MyTypeConverter implements Converter {
	private boolean useDefault = false;

    private Object defaultValue = null;

    public MyConverter(Object defaultValue) {
        useDefault = false;
        if (defaultValue == null) {
           this.defaultValue  = null;
        } else {
           this.defaultValue  = defaultValue;
        }
        useDefault = true;
    }

    public Object convert(Class type, Object value) {
        if(value == null){
            if(useDefault){
                   return defaultValue;
           }else{
                   Throw new ConvertException(…);
           }               
        }
        if(value.getClass().equals(type))
               return value;
        else 
               return value.toString();
    }
}
