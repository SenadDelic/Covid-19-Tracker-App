package tracker.covid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tracker.covid.service.CovidDataService;

@Controller
public class IndexController {
    CovidDataService covidDataService;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }
}
