package com.honji.exhibition.admin.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EasyUIDataGridResult implements Serializable {

    private long total;
    private List<?> rows;

    public EasyUIDataGridResult(IPage page) {
        this.setTotal(page.getTotal());
        this.setRows(page.getRecords());
    }

    public EasyUIDataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }
}
