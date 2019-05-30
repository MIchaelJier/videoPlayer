package com.team5101.service;


import com.team5101.dao.userDao;
import com.team5101.entity.User;
import com.team5101.entity.VQe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import com.team5101.entity.VMe;
import com.team5101.entity.VHi;
import sun.misc.BASE64Encoder;

@Service
public class userService {
    @Autowired
    private userDao userDao;
    public User findByName(String name,String passwd){
        return userDao.Login(name,passwd);
    }
    public Integer register(String name,String passwd,String email){
        return  userDao.Register(name,passwd,email);
    }
    public Integer hasName(String name){
        return userDao.HasName(name);
    }
    public List<VMe> findVbyVname(String vname){
        return  userDao.findV(vname);
    }
    public Integer hasVandU(String username,String videoname){
        return  userDao.HasVandU(username,videoname);
    }
    public Integer updatehistory(String username, String videoname, String videoct, Date date){
        return  userDao.Updatehistroy(username,videoname,videoct,date);
    }
    public Integer insertehistory(String username, String videoname, String videoct, Date date){
        return  userDao.Inserthistroy(username,videoname,videoct,date);
    }
    public List<VHi> lookhistorybyname(String username){
        return  userDao.Lookhistorybyname(username);
    }

    public List<VQe> returnquestionbyname(String videoname){
        return  userDao.Returnquestionbyname(videoname);
    }
    public Integer changeusername(String username,String oldname){
        return userDao.Changeusername1(username,oldname)+userDao.Changeusername2(username,oldname);
    }
    public String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;

        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
}
