package com.jcombat.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ProfileController {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @RequestMapping
    public String home() {
        return "index";
    }

    @RequestMapping("/user/profiles")
    public String profileList(Model model) {
        model.addAttribute("profiles", profileRepository.getAllProfiles());
        return "user-profiles";
    }

    @RequestMapping("/user/details/{id}")
    public String profileDetails(@PathVariable("id") String userId, Model model) {
        model.addAttribute("profile", profileRepository.getProfile(userId));
        return "user-details";
    }
}
