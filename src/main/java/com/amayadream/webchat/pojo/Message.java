package com.amayadream.webchat.pojo;

import org.springframework.stereotype.Repository;

import javax.json.Json;
import java.util.ArrayList;
import java.util.Arrays;

import com.amayadream.webchat.utils.ApiUtil;

/**
 * 解析后台传来的消息
 * "massage" : {
 * "from" : "xxx",
 * "to" : "xxx",
 * "content" : "xxx",
 * "time" : "xxxx.xx.xx"
 * },
 * "type" : {notice|message|img},
 * "list" : {[xx],[xx],[xx]}
 */
@Repository(value = "message")
public class Message {
    String id;
    String from;
    String to;
    String content;
    String time;
    String type;
    String keyword1;
    String keyword2;
    String keyword3;
    String keyword4;
    String keyword5;
    //    ArrayList<String> keywords = (ArrayList<String>) Arrays.asList(new String[]{"null", "null", "null", "null", "null"});

    public Message() {
    }

//    public Message(String from, String to, String content, String time, String type) throws Exception {
//        setFrom(from);
//        setTo(to);
//        setContent(content);
//        setTime(time);
//        setType(type);
//        initKeywords();
//    }


    //    void initKeywords() throws Exception {
////        keyWords = this.content;
//        this.keyWords = ApiUtil.getKeywords(this.content);
//    }
    public void setId(String id) {
        this.id = id;
    }

    public void setKeyword1(String keyword1) { this.keyword1 = keyword1; }
    public void setKeyword2(String keyword2) { this.keyword2 = keyword2; }
    public void setKeyword3(String keyword3) { this.keyword3 = keyword3; }
    public void setKeyword4(String keyword4) { this.keyword4 = keyword4; }
    public void setKeyword5(String keyword5) { this.keyword5 = keyword5; }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return this.from;
    }

    public String getId() {
        return this.id;
    }

    public String getTo() {
        return this.to;
    }

    public String getContent() {
        return this.content;
    }

    public String getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public String getKeyword1() { return this.keyword1; }
    public String getKeyword2() { return this.keyword2; }
    public String getKeyword3() { return this.keyword3; }
    public String getKeyword4() { return this.keyword4; }
    public String getKeyword5() { return this.keyword5; }
//    public void setKeywords(ArrayList<String> keywords){
//        this.keyword1 = keywords.get(0);
//        this.keyword2 = keywords.get(1);
//        this.keyword3 = keywords.get(2);
//        this.keyword4 = keywords.get(3);
//        this.keyword5 = keywords.get(4);
//    }
}
