package ru.dimall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dimall.interfaces.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GisController {

    private IGisService gisService;

    public IGisService getGisService() {
        return gisService;
    }

    @Autowired
    public void setGisService(IGisService gisService) {
        this.gisService = gisService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody String getInfo(@RequestParam Map<String,String> requestParameters) {
        return this.getGisService().getInfo(requestParameters);

    }

}
