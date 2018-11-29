package cn.app.controller;

import cn.app.pojo.BkUser;
import cn.app.service.BkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("BkUser")
public class BkUserController {
    @Autowired
    private BkUserService bkUserService;
    @RequestMapping(value="login.html")
    public String login(){
        return "backendlogin";
    }

    @RequestMapping(value="dologin")
    public String dologin(@RequestParam(value="userCode",required = false)String userCode, @RequestParam(value="userPassword",required = false)String userPassword, HttpSession session,HttpServletRequest request) {
        BkUser bkUser=bkUserService.login(userCode,userPassword);
        if(bkUser!=null){
            session.setAttribute("userSession",bkUser);
            return "backend/main";
        }else{
            request.setAttribute("error","用户名或者密码错误");
            return "backendlogin";
        }
    }
    @RequestMapping("logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("devUserSession");
        return "redirect:/index.jsp";
    }
}
