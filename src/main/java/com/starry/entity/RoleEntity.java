package com.starry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@TableName("t_role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "role_id", type = IdType.AUTO)
      private Long roleId;

      /**
     * 角色名称
     */
      private String roleName;

    private String type;

      /**
     * 备注
     */
      private String remark;

      /**
     * 创建时间
     */
      private Date createTime;

      /**
     * 逻辑删除 0未删除   1已删除
     */
      private Boolean deleted;

    
    public Long getRoleId() {
        return roleId;
    }

      public void setRoleId(Long roleId) {
          this.roleId = roleId;
      }
    
    public String getRoleName() {
        return roleName;
    }

      public void setRoleName(String roleName) {
          this.roleName = roleName;
      }
    
    public String getType() {
        return type;
    }

      public void setType(String type) {
          this.type = type;
      }
    
    public String getRemark() {
        return remark;
    }

      public void setRemark(String remark) {
          this.remark = remark;
      }
    
    public Date getCreateTime() {
        return createTime;
    }

      public void setCreateTime(Date createTime) {
          this.createTime = createTime;
      }
    
    public Boolean getDeleted() {
        return deleted;
    }

      public void setDeleted(Boolean deleted) {
          this.deleted = deleted;
      }

    @Override
    public String toString() {
        return "RoleEntity{" +
              "roleId=" + roleId +
                  ", roleName=" + roleName +
                  ", type=" + type +
                  ", remark=" + remark +
                  ", createTime=" + createTime +
                  ", deleted=" + deleted +
              "}";
    }
}
