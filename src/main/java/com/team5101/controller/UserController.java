package com.team5101.controller;

import com.team5101.entity.VQe;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;
import com.team5101.entity.User;
import com.team5101.entity.VHi;
import com.team5101.entity.VMe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team5101.service.userService;
import sun.misc.BASE64Encoder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
public class UserController {
    @Autowired
    userService userService;

    @RequestMapping(value = "/getfile0")
    public String[] getfile0(HttpServletRequest request) {
        String username = request.getParameter("username");
        String filePath="C:\\Users\\ASUS\\IdeaProjects\\videoPlayer\\src\\main\\resources\\static\\images\\user\\";//存放路径
        String savePath=filePath+username+".jpg";
        String imgsrc = userService.ImageToBase64ByLocal(savePath);
        String[] imgs = imgsrc.split("/");
        return imgs;
    }

    @RequestMapping(value = "/getfile")
    public String[] getfile(HttpServletRequest request,
                       @RequestParam("file") MultipartFile file,Model model) {
        BufferedOutputStream stream=null;
        String username = request.getParameter("name");
        Map<String,Object> resultMap=new HashMap<>();
        String filePath="C:\\Users\\ASUS\\IdeaProjects\\videoPlayer\\src\\main\\resources\\static\\images\\user\\";//存放路径
        /*String filePath="C:\\Users\\ASUS\\IdeaProjects\\videoPlayer\\out\\artifacts\\videoPlayer\\static\\images\\user\\";*/
        if(!file.isEmpty()){
            try {
                byte[] bytes=file.getBytes();
                String savePath=filePath+username+".jpg";
                stream=new BufferedOutputStream(new FileOutputStream(new File(savePath)));
                stream.write(bytes);
                stream.close();
                resultMap.put("result","success");
                String imgsrc = userService.ImageToBase64ByLocal(savePath);
                String[] imgs = imgsrc.split("/");
                return imgs;
            } catch (Exception e) {
                resultMap.put("result","failure");
                e.printStackTrace();
                return null;
            }
        }else{
            resultMap.put("result","failure");
            return null;
        }
    }
    @RequestMapping(value = "/login")
    public User login(HttpServletRequest request, HttpServletResponse response,
                      HttpSession session, Model model) {
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        User user = userService.findByName(name, passwd);
        if (user != null) {
            session.setAttribute("user", user);
        }
        return user;
    }

    @RequestMapping(value = "/reg")
    @ResponseBody
    public int reg(User user) {
        String name = user.getName();
        String passwd = user.getPasswd();
        String email = user.getEmail();
        int ok = 0;
        Integer hasN = userService.hasName(name);
        if (hasN == 1) {
            ok = 2;
        } else {
            Integer n = userService.register(name, passwd, email);
            if (n == 1) {
                ok = 1;
            }
        }
        return ok;
    }

    @RequestMapping(value = "/changename")
    @ResponseBody
    public int changename(HttpServletRequest request) {
        String username = request.getParameter("name");
        String oldname = request.getParameter("oldname");
        Integer hasN = userService.hasName(username);
        File   file=new   File("C:\\Users\\ASUS\\IdeaProjects\\videoPlayer\\src\\main\\resources\\static\\images\\user\\"+oldname+".jpg");
        /*System.out.println(file);*/
        if(file!=null){
            file.renameTo(new File("C:\\Users\\ASUS\\IdeaProjects\\videoPlayer\\src\\main\\resources\\static\\images\\user\\"+username+".jpg"));
        }
        if (hasN == 1) return 3;
        else {
            Integer changeok = userService.changeusername(username, oldname);
            /*System.out.println(changeok);*/
            return changeok / 2;
        }
    }

    @RequestMapping(value = "/savetime")
    @ResponseBody
    public int savetime(VHi vHi) {
        String username = vHi.getUsername();
        String videoname = vHi.getVideoname();
        String videoct = vHi.getVideocurrenttime();
        Date date = vHi.getDate();
        /*System.out.println(username+videoname+videoct+date);*/
        Integer hasVU = userService.hasVandU(username, videoname);
        if (hasVU == 1) {
            Integer upok = userService.updatehistory(username, videoname, videoct, date);
        } else {
            Integer lsok = userService.insertehistory(username, videoname, videoct, date);
        }
        return 1;
    }

    @RequestMapping(value = "/lookhistory")
    @ResponseBody
    public List<VHi> lookhistory(HttpServletRequest request) {
        String username = request.getParameter("name");
        List<VHi> vHi = userService.lookhistorybyname(username);
        return vHi;
    }

    @RequestMapping(value = "/returnquestion")
    @ResponseBody
    public List<VQe> returnquestion(HttpServletRequest request) {
        String videoname = request.getParameter("vname");
        List<VQe> vQe = userService.returnquestionbyname(videoname);
        return vQe;
    }

    @RequestMapping(value = "/find")
    @ResponseBody
    public List<VMe> find(HttpServletRequest request) {
        String vname = request.getParameter("vname");
        vname = "%" + vname + "%";
        List<VMe> vMe = userService.findVbyVname(vname);
        return vMe;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
    @RequestMapping("/homepage")
    public ModelAndView homepage(){
        return new ModelAndView("homepage");
    }
    @RequestMapping("/findus")
    public ModelAndView findus(){
        return new ModelAndView("findus");
    }
    @RequestMapping("/findvideo")
    public ModelAndView findvideo(){
        return new ModelAndView("findvideo");
    }
    @RequestMapping("/player")
    public ModelAndView player(){ return new ModelAndView("player"); }
    @RequestMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }
    @RequestMapping("/selfpage")
    public ModelAndView selfpage(){ return new ModelAndView("selfpage"); }
}
