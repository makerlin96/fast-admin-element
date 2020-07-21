package com.starry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@TableName("t_menu")
public class MenuEntity implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 父菜单ID，一级菜单为0
     */
      private Long parentId;

      /**
     * 菜单名称
     */
      private String name;

      /**
     * 菜单URL
     */
      private String path;

      /**
     * 组件路径
     */
      private String component;

    private Boolean menuType;

    private Boolean status;

      /**
     * 授权 多个用逗号分隔，如：user:list,user:create)
     */
      private String perms;

      /**
     * 是否为外链（0不是  1是）
     */
      private Boolean isFrame;

      /**
     * 菜单图标
     */
      private String icon;

      /**
     * 排序
     */
      private Integer sort;

      /**
     * 逻辑删除 0未删除   1已删除
     */
      private Boolean deleted;

    
    public Long getId() {
        return id;
    }

      public void setId(Long id) {
          this.id = id;
      }
    
    public Long getParentId() {
        return parentId;
    }

      public void setParentId(Long parentId) {
          this.parentId = parentId;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getPath() {
        return path;
    }

      public void setPath(String path) {
          this.path = path;
      }
    
    public String getComponent() {
        return component;
    }

      public void setComponent(String component) {
          this.component = component;
      }
    
    public Boolean getMenuType() {
        return menuType;
    }

      public void setMenuType(Boolean menuType) {
          this.menuType = menuType;
      }
    
    public Boolean getStatus() {
        return status;
    }

      public void setStatus(Boolean status) {
          this.status = status;
      }
    
    public String getPerms() {
        return perms;
    }

      public void setPerms(String perms) {
          this.perms = perms;
      }
    
    public Boolean getFrame() {
        return isFrame;
    }

      public void setFrame(Boolean isFrame) {
          this.isFrame = isFrame;
      }
    
    public String getIcon() {
        return icon;
    }

      public void setIcon(String icon) {
          this.icon = icon;
      }
    
    public Integer getSort() {
        return sort;
    }

      public void setSort(Integer sort) {
          this.sort = sort;
      }
    
    public Boolean getDeleted() {
        return deleted;
    }

      public void setDeleted(Boolean deleted) {
          this.deleted = deleted;
      }

    @Override
    public String toString() {
        return "MenuEntity{" +
              "id=" + id +
                  ", parentId=" + parentId +
                  ", name=" + name +
                  ", path=" + path +
                  ", component=" + component +
                  ", menuType=" + menuType +
                  ", status=" + status +
                  ", perms=" + perms +
                  ", isFrame=" + isFrame +
                  ", icon=" + icon +
                  ", sort=" + sort +
                  ", deleted=" + deleted +
              "}";
    }
}
