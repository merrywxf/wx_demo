package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Autowired
    UserRepository userRepository;

    //根据编号查询
    @GetMapping(value = "/findById")
    public User findById(@RequestParam(value = "id") Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping(value = "findName")
    public List<User> findName(@RequestParam(value = "name") String name) {
        return userRepository.findByNameLike(name);
    }

    @GetMapping("/save")
    public String save() {
        User user = new User();
        user.setAddress("akdfjakdjf");
        user.setBirth("--asdf9349459");
        user.setMobile("12356");
        userRepository.save(user);
        return "ok";
    }

    @GetMapping(value = "findAll")
        //currentPage 当前页，默认为0，如果传1的话是查的第二页数据
        //pageSize 每页数据条数
   public Page<User> findAll(@RequestParam(value = "currentPage") int currentPage, @RequestParam(value = "pageSize") int pageSize) {
        Pageable pageable = PageUtil.getPageable(currentPage, pageSize, "datetime");
        return userRepository.findAll(pageable);
    }
}
