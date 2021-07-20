package work.gg3083.template.base.model.vo;


import java.util.List;

/**
 * @author Gimi
 * @date 2019/8/16 10:25
 */
public class PageInfo<T>{

    private long pageNo;
    private long pageSize;
    private long totals;
    private List<T> data;



    public PageInfo(){

    }

    public PageInfo(List<T> page){
        this.pageNo = 1;
        this.pageSize = page.size();
        this.totals = page.size();
        this.data = page;
    }



    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotals() {
        return totals;
    }

    public void setTotals(long totals) {
        this.totals = totals;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
