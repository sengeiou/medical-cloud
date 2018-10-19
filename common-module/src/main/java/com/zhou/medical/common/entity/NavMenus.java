package com.zhou.medical.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单导航
 *
 */
public class NavMenus implements Serializable {

    private String id;
    private String name;
    private String path;
    private String icon;
    private String authority;
    private String pid;
    private List<NavMenus> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<NavMenus> getChildren() {
        return children;
    }

    public void setChildren(List<NavMenus> children) {
        this.children = children;
    }
}
