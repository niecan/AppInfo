package cn.app.pojo;

import java.util.Date;

public class Promotion {
    private Integer id;
    private Integer appid;//info表的主键id
    private String adPicPath;//广告图片储存路径
    private Integer adPV;//广告点击量
    private Date carouselPosition;//轮播位
    private Date endTime;//失效时间
    private Integer createdBy;//创建者
    private Date creationDate;//创建时间
    private Date modifyDate;//更新时间
    private Integer modifyBy;//更新者
}
