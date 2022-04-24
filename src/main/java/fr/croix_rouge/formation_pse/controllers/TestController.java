package fr.croix_rouge.formation_pse.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
@CrossOrigin(origins = "*")
public class TestController {

  @GetMapping({"", "/"})
  public String test() {
    return "Heyy !";
  }
}
