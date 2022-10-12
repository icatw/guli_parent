package cn.icatw.educenter.entity;

import lombok.Data;

@Data
public class GiteeUser {
    private String id; //NOT NULL OPEN_ID
    private String name; //NOT NULL
    private String avatarUrl; //可能为空
    //get、set方法
}
