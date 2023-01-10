package com.project.CovidTracker.controllers;

import com.project.CovidTracker.model.Coronavirusdata;
import com.project.CovidTracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Homecontroller {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String add(Model model)
    {
        List<Coronavirusdata> lData = coronaVirusDataService.getAllList();
        int totalcases= lData.stream().mapToInt(sint->sint.getLatestTotalCases()).sum();
        model.addAttribute("subtitle","Total Cases reported across globe =");
        model.addAttribute("Title","Corona Virus Tracker");
        model.addAttribute("ListData",lData);
        model.addAttribute("Totalcases",totalcases);
        return "home";
    }
}
