package com.example.base.controller;


import com.example.base.entity.Member;
import com.example.base.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FirebaseController {


    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/doctor")
    public String index(){
        return "index";
    }

    @GetMapping("/insertMember")
    public String insertMember(@RequestParam Member member) throws Exception {

        return firebaseService.insertMember(member);

    }


    @GetMapping("/getMemberDetail")
    @ResponseBody
    public Member getMemberDetail(@RequestParam String id) throws Exception {

        return firebaseService.getMemberDetail(id);

    }


    @GetMapping("/updateMember")

    public String updateMember(@RequestParam Member member) throws Exception {

        return firebaseService.updateMember(member);

    }


    @GetMapping("/deleteMember")

    public String deleteMember(@RequestParam String id) throws Exception {

        return firebaseService.deleteMember(id);

    }

}