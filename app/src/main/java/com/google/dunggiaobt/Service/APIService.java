package com.google.dunggiaobt.Service;

public class APIService {
    private static final String base_url="https://apnpghenhac.000webhostapp.com/Server/";
   public  static  Dataservice getService(){
       return  APIRetrofitClient.getClient(base_url).create(Dataservice.class);
   }

}
