package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    // From the instructions: "Add another results handler method to SearchController, overloading the existing method."
    // What?  I don't see an existing method called "results."  Anyway, here we go.

    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String results(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {

        ArrayList<HashMap<String, String>> Jobs = new ArrayList<>();

        if (!searchType.equals("all")) {
            Jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        } else if (searchType.equals("all")) {
            Jobs = JobData.findByValue(searchTerm);
        }

        model.addAttribute("Jobs", Jobs);
        model.addAttribute("columns", ListController.columnChoices);

        return "search";
    }

}
