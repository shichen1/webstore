package entity;

import java.util.List;

/**
 * 分类结果页
 * @param <T>
 */
public class PageResult<T> {

    private Long total;
    private List<T> rows; // 每页记录

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
