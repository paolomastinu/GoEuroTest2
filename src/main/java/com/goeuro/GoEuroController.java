package com.goeuro;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GoEuroController {

    @RequestMapping("/findCity")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    	
        return "findCity";
    }
    
    @RequestMapping(method = RequestMethod.POST,value = "/cityInformation")
    public String cityInformation(@RequestParam(value="cityName", required=false, defaultValue="Berlin") String cityName, Model model) {
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Information>> rateResponse =
                restTemplate.exchange("http://api.goeuro.com/api/v2/position/suggest/en/"+cityName,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Information>>() {
                    });
        List<Information> informations = rateResponse.getBody();
        
    	model.addAttribute("informations", informations);
        return "cityDetails";
    }

}
