package com.jmaguina.mail.app.controllers;

import com.jmaguina.mail.app.DTO.MailDTO;
import com.jmaguina.mail.app.services.MailService;
//import ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jorge Maguina on 28/10/2017.
 */

@Controller
@RequestMapping("jmaguina/api/mail")
public class MailController {


    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return "Testing service method GET";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> post(@RequestBody MailDTO request){
        try{
            if(mailService.SendContactRequest(request)){
                return new ResponseEntity<String>("Exitoso", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>("Fallo",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception ex){
            return new ResponseEntity<String>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
