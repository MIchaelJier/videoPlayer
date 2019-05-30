package com.team5101.dao;

import com.team5101.entity.User;
import com.team5101.entity.VQe;
import org.apache.ibatis.annotations.*;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;
import com.team5101.entity.VMe;
import com.team5101.entity.VHi;
@Mapper
@Component
public interface userDao {
    @Select("select * from user where name=#{name} and passwd=#{passwd}")
    User Login(@Param("name") String name,@Param("passwd")String passwd);

    @Select("select EXISTS(select name from user where name=#{name})")
    Integer HasName(@Param("name") String name);

    @Insert("insert into user(name,passwd,Email) values(#{name},#{passwd},#{email})")
    Integer Register(@Param("name") String name,@Param("passwd")String passwd,@Param("email")String email);

    @Select("select * from videomessage where videoname like #{vname}")
    List<VMe> findV(@Param("vname") String vname);

    @Select("select EXISTS(select username,videoname from videohistroy where username=#{username} and videoname=#{videoname} )")
    Integer HasVandU(@Param("username") String username,@Param("videoname") String videoname);

    @Update("UPDATE videohistroy SET videocurrenttime=#{videoct},date=#{date} WHERE username=#{username} and videoname=#{videoname} ")
    Integer Updatehistroy(@Param("username") String username,@Param("videoname") String videoname,@Param("videoct") String videoct,@Param("date") Date date);

    @Insert("insert into videohistroy(username,videoname,videocurrenttime,date) value(#{username},#{videoname},#{videoct},#{date}) ")
    Integer Inserthistroy(@Param("username") String username,@Param("videoname") String videoname,@Param("videoct") String videoct,@Param("date") Date date);

    @Select("select * from videohistroy where username=#{username}")
    List<VHi> Lookhistorybyname(@Param("username") String username);

    @Select("select * from videoquestion where videoname=#{videoname}")
    List<VQe> Returnquestionbyname(@Param("videoname") String videoname);

    @Update("UPDATE user SET name=#{username} WHERE name=#{oldname}")
    Integer Changeusername1(@Param("username")String username,@Param("oldname") String oldname);

    @Update("UPDATE videohistroy SET username=#{username} WHERE username=#{oldname}")
    Integer Changeusername2(@Param("username")String username,@Param("oldname") String oldname);
}
