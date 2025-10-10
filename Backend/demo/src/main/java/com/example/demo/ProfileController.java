package com.example.demo;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") // allow frontend HTML to call backend
@RestController
@RequestMapping("/api")
public class ProfileController {

    @PostMapping("/register")
    public String register(@RequestBody Profile profile) {
        return "Profile registered for " + profile.getName() + " (Age: " + profile.getAge() + ")";
    }

    @PostMapping("/login")
    public String login(@RequestBody Profile profile) {
        return "Welcome back, " + profile.getName();
    }
}


class Profile {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
