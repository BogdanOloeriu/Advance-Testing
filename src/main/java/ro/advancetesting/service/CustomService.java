package ro.advancetesting.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class CustomService {


    private String name;
    private String privateMessage(){
        return "private Message";
    }


}
