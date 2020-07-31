package com.starry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@TableName("t_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键ID
     */
        @TableId(value = "user_id", type = IdType.AUTO)
      private Long userId;

      /**
     * 用户名
     */
      private String username;

      /**
     * 密码
     */
      private String password;

      /**
     * 管理员类别
     */
      private Integer userType;

      /**
     * 单位ID
     */
      private Integer agentId;

      /**
     * 真实姓名
     */
      private String realName;

      /**
     * 联系人
     */
      private String contact;

      /**
     * 手机号
     */
      private String mobile;

      /**
     * 状态  0正常   1禁用
     */
      private Integer status;

      /**
     * 人脸
     */
      private String face;

    /**
     * 最后登录时间
     */
    private Long lastLoginTime;

    /**
     * 创建使劲啊
     */
    private Long createTime;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", agentId=" + agentId +
                ", realName='" + realName + '\'' +
                ", contact='" + contact + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", face='" + face + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", createTime=" + createTime +
                '}';
    }
}
