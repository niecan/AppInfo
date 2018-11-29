package cn.app.controller;

import cn.app.pojo.DevUser;
import cn.app.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/devUser")
public class DevUserController {

    @Autowired
    private DevUserService devUserService;

    @RequestMapping(value="/login.html")
    public String login(){
        return "devlogin";
    }

    @RequestMapping(value="dologin.html")
    public String doLogin(@RequestParam String devCode, @RequestParam String devPassword,
                          HttpSession session, HttpServletRequest request){
        DevUser devUser=devUserService.login(devCode,devPassword);
        if(null!=devUser){
            session.setAttribute("devUserSession",devUser);
            return "developer/main";
        }else{
            request.setAttribute("error","用户名或者密码错误");
            return "devlogin";
        }
    }
    @RequestMapping(value="logout.html")
    public String logout(HttpSession session){
        session.removeAttribute("devUserSession");
        return "redirect:/index.jsp";
    }

}
