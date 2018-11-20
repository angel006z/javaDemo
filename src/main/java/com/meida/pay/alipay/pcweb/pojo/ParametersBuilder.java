package com.meida.pay.alipay.pcweb.pojo;

public abstract class ParametersBuilder {

    public abstract boolean Validate();

    public String GetParameters()
    {
         return BuildJson();           
    }

    private String BuildJson()
    {
    	return "";
//        var jss = new JavaScriptSerializer();
//        try
//        {
//            return jss.Serialize(this);
//        }
//        catch (Exception ex)
//        {
//            System.out.println("JSONHelper.ObjectToJSON(): " + ex.getMessage());
//            return "";
//        }
    }
}
