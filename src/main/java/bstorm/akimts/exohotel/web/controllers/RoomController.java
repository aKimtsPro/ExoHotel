package bstorm.akimts.exohotel.web.controllers;

import bstorm.akimts.exohotel.services.RoomService;
import bstorm.akimts.exohotel.web.annotations.Controller;
import bstorm.akimts.exohotel.web.annotations.GetRequest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless(name = "RoomController")
@Controller(basePath = "/room")
public class RoomController implements BaseController {

//    @EJB
//    private RoomService service;

    @GetRequest(path = "/test")
    public String test( ){
        return "room_test.jsp";
    }

}
