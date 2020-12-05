package tracker.covid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tracker.covid.service.CovidDataService;

@Controller
public class IndexController {
    final CovidDataService covidDataService;

    public IndexController(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("covidData", covidDataService.getCovidData());
        return "index";
    }
}
