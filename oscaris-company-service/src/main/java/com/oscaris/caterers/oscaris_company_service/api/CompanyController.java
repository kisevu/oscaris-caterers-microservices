package com.oscaris.caterers.oscaris_company_service.api;
/*
*
@author ameda
@project Oscaris-caterers
*
*/


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CompanyController {


    @GetMapping
    public List<String> getCompanies(){
       return List.of(
               "FRONTIER ENERGY",
               "HIVOS AFRICA",
               "SERENGETI ENERGY",
               "ASCENT AFRICA"
       );
    }
}
