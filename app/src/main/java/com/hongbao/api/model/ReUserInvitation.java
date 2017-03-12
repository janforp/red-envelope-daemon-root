package com.hongbao.api.model;

/**
 * Created by com.hongbao.api.MybatisCodeGenerate on 2016-09-20
 */
public class ReUserInvitation implements java.io.Serializable {

    // Fields

    // id
    private Long id;
    // 邀请人ID
    private Integer userId;
    // 被邀请人ID
    private Integer invitedUserId;
    // 邀请时间,如:2016-08-18 12:53:30
    private String invitedTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserInvitation() {
    }

    /**
     * full constructor
     */
    public ReUserInvitation(Integer userId, Integer invitedUserId, String invitedTime) {
        this.userId = userId;
        this.invitedUserId = invitedUserId;
        this.invitedTime = invitedTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 邀请人ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 邀请人ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 被邀请人ID
     */
    public Integer getInvitedUserId() {
        return this.invitedUserId;
    }

    /**
     * 被邀请人ID
     */
    public void setInvitedUserId(Integer invitedUserId) {
        this.invitedUserId = invitedUserId;
    }

    /**
     * 邀请时间,如:2016-08-18 12:53:30
     */
    public String getInvitedTime() {
        return this.invitedTime;
    }

    /**
     * 邀请时间,如:2016-08-18 12:53:30
     */
    public void setInvitedTime(String invitedTime) {
        this.invitedTime = invitedTime;
    }

}