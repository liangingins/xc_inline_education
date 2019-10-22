package com.xuecheng.test.freemarker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Controller
@RequestMapping("/freemarker")
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/banner")
    private String index_banner(Map<String,Object> map){
        String dataUrl= "http://localhost:31001/cms/config/getModel/5a791725dd573c3574ee333f";
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        Map body = forEntity.getBody();
        map.putAll(body);
        return "index_banner";
    }
}
