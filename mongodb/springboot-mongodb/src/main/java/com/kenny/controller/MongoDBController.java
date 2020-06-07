package com.kenny.controller;

import com.kenny.entity.MemberReadHistory;
import com.kenny.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * ClassName: MongoDBController
 * Function:  TODO
 * Date:      2020/6/6 19:50
 *
 * @author Kenny
 * version    V1.0
 */
@Controller
@RequestMapping("/mongodb")
public class MongoDBController {

    @Autowired
    private MongoDBService mongoDBService;

    @RequestMapping("/create")
    @ResponseBody
    public int create() {
        MemberReadHistory memberReadHistory = new MemberReadHistory();
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistory.setMemberIcon("ic0n");
        memberReadHistory.setMemberId(1L);
        memberReadHistory.setMemberNickname("nickName");
        memberReadHistory.setProductId(2L);
        memberReadHistory.setProductName("productName");
        memberReadHistory.setProductPic("pic");
        memberReadHistory.setProductPrice("price");
        memberReadHistory.setProductSubTitle("title");
        return mongoDBService.create(memberReadHistory);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int delete() {
        return mongoDBService.delete(Arrays.asList("5edb8bf1bef6b441762f04c5"));//ObjectId("5edb8bf1bef6b441762f04c5")
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<MemberReadHistory> list(Long meberId) {
        return mongoDBService.list(meberId);
    }
}
