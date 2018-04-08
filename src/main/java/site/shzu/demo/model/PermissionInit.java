package site.shzu.demo.model;

public class PermissionInit {
    private Integer id;

    private String url;

    private String permissioninit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermissioninit() {
        return permissioninit;
    }

    public void setPermissioninit(String permissioninit) {
        this.permissioninit = permissioninit == null ? null : permissioninit.trim();
    }
}