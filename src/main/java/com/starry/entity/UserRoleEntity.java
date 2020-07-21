package com.starry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户与角色对应关系
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@TableName("t_user_role")
public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 用户ID
     */
        @TableId(value = "user_id", type = IdType.AUTO)
      private Long userId;

      /**
     * 角色ID
     */
      private Long roleId;

    
    public Long getUserId() {
        return userId;
    }

      public void setUserId(Long userId) {
          this.userId = userId;
      }
    
    public Long getRoleId() {
        return roleId;
    }

      public void setRoleId(Long roleId) {
          this.roleId = roleId;
      }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
              "userId=" + userId +
                  ", roleId=" + roleId +
              "}";
    }
}
