package com.xteam.xteamApi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * X-Team WebFlux View Controller.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@Controller
public class ViewController {

  @GetMapping("/")
  public String welcome() {
    return "index";
  }

}
