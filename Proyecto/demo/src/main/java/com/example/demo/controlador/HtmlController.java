package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
   public HtmlController() {
   }

   @GetMapping({"/"})
   public String landing() {
      return "landing.html";
   }
}
