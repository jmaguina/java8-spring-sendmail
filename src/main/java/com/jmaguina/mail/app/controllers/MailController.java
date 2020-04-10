package com.jmaguina.mail.app.controllers;

import com.jmaguina.mail.app.model.MailDto;
import com.jmaguina.mail.app.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import ContactService;

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
    public ResponseEntity<String> post(@RequestBody MailDto request){

        try {

            mailService.sendEmail(request);

        }catch (Exception ex){
            // Si hay algun error en el envio, pass o user
            return new ResponseEntity<String>("Error envio : " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Exito", HttpStatus.OK);

    }

}
