package com.starry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@TableName("t_role_menu")
@Data
public class RoleMenuEntity implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 角色ID
     */
        @TableId(value = "role_id")
      private Long roleId;

      /**
     * 菜单ID
     */
      private String menuId;

}
