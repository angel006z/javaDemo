package com.meida.base.domain.vo;

public class TreeVo {
    private String id ;
    private String pid  ;
    private String name  ;
    private String url  ;
    private String title ;
    private String target  ;
    private String icon  ;
    private Boolean iconOpen  ;
    private Boolean open  ;

    private Boolean nocheck  ;

    private Boolean checkbox  ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(Boolean iconOpen) {
        this.iconOpen = iconOpen;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getNocheck() {
        return nocheck;
    }

    public void setNocheck(Boolean nocheck) {
        this.nocheck = nocheck;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getNodeClassName() {
        return nodeClassName;
    }

    public void setNodeClassName(String nodeClassName) {
        this.nodeClassName = nodeClassName;
    }

    public String getNodeStyle() {
        return nodeStyle;
    }

    public void setNodeStyle(String nodeStyle) {
        this.nodeStyle = nodeStyle;
    }

    private Boolean checked  ;
    private String nodeClassName  ;
    private String nodeStyle  ;
}
