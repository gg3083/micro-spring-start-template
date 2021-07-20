package work.gg3083.template.user.model.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public class PageInfo<T> extends work.gg3083.template.base.model.vo.PageInfo<T> {



    public PageInfo(List page) {
        super(page);
    }

    public PageInfo(IPage<T> page) {
        setPageNo(page.getCurrent());
        setPageSize(page.getSize());
        setTotals(page.getTotal());
        setData(page.getRecords());

    }
}
