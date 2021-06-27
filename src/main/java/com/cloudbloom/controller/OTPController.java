package com.cloudbloom.controller;

import com.cloudbloom.model.EmailDTO;
import com.cloudbloom.service.EmailService;
import com.cloudbloom.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class OTPController {

    @Autowired
    private EmailService emailService;
    @Autowired
    OTPService otpService;

    @RequestMapping(value = "gnerateOtp/{user}", method = RequestMethod.GET)
    public ResponseEntity<String> generateOtp(@PathVariable(value = "user") String user) {
        int otp = otpService.generateOTP(user);
        return ResponseEntity.ok().body("your otp is :->" + otp);
    }

    @RequestMapping(value = "sendEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<String> sendEmail(@PathVariable(value = "email") String email) {
        sendEmailToUser(email);
        return ResponseEntity.ok().body("Email sent Successfully");
    }

    private void sendEmailToUser(String user) {
        System.out.println("###### Email sending initiated ######");

        EmailDTO email = new EmailDTO();

        email.setTo(user);
        email.setSubject("Welcome to Cloudbloom");

        // Populate the template data
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", "Atul Rai");
        List<String> teamMembers = Arrays.asList("Tendulkar", "Manish", "Dhirendra");
        templateData.put("teamMembers", teamMembers);
        templateData.put("location", "India");
        email.setEmailData(templateData);

        // Calling email service
        emailService.sendWelcomeEmail(email);
    }


    @RequestMapping(value = "/validateOtp/{user}", method = RequestMethod.GET)
    public @ResponseBody
    String validateOtp(@RequestParam("otpnum") int otpnum, @PathVariable(value = "user") String username) {

        final String SUCCESS = "Entered Otp is valid";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";

        //Validate the Otp
        if (otpnum >= 0) {

            int serverOtp = otpService.getOtp(username);
            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(username);

                    return (SUCCESS);
                } else {
                    return FAIL;
                }
            } else {
                return FAIL;
            }
        } else {
            return FAIL;
        }
    }
}


