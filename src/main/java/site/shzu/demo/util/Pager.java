package site.shzu.demo.util;

/**
 * @Author: Kinson
 * @Description: 分页实体类
 * @Date: Created in 2018/1/15 18:43
 * @Version: 1.0
 */
public class Pager {
    /**
     * 当前数据对应的页码
     */
    private Integer page;
    /**
     * 总的数据数目
     */
    private Long recTotal;
    /**
     * 每页数据数目
     */
    private Integer recPerPage;

    public Pager(Integer page, Long recTotal, Integer recPerPage) {
        this.page = page;
        this.recTotal = recTotal;
        this.recPerPage = recPerPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getRecTotal() {
        return recTotal;
    }

    public void setRecTotal(Long recTotal) {
        this.recTotal = recTotal;
    }

    public Integer getRecPerPage() {
        return recPerPage;
    }

    public void setRecPerPage(Integer recPerPage) {
        this.recPerPage = recPerPage;
    }
}
