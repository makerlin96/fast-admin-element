package com.starry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 角色与菜单对应关系
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@TableName("t_role_menu")
public class RoleMenuEntity implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 角色ID
     */
        @TableId(value = "role_id", type = IdType.AUTO)
      private Long roleId;

      /**
     * 菜单ID
     */
      private Long menuId;

    
    public Long getRoleId() {
        return roleId;
    }

      public void setRoleId(Long roleId) {
          this.roleId = roleId;
      }
    
    public Long getMenuId() {
        return menuId;
    }

      public void setMenuId(Long menuId) {
          this.menuId = menuId;
      }

    @Override
    public String toString() {
        return "RoleMenuEntity{" +
              "roleId=" + roleId +
                  ", menuId=" + menuId +
              "}";
    }
}
