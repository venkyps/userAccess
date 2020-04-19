package com.user.access.controller;

import com.user.access.service.UserAccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/userAccess")
public class UserAccessRestEndpointController {

    private static final Logger logger = LoggerFactory.getLogger(UserAccessRestEndpointController.class);

    @Autowired
    private UserAccessService userAccessService;

    @PostMapping(path="/persistUserAccess")
    @ResponseBody
    public void persistUserAccess(@RequestParam("empID") String empID) throws Exception{
        userAccessService.persistUserAccess(empID);
    }

    @PostMapping(path="/persistAllUserAccess")
    @ResponseBody
    public void persistAllUserAccess() throws Exception{
        userAccessService.persistAllUserAccess();
    }


}
