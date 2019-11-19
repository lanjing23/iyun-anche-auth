package iyun.anche.auth.response;

import lombok.Data;

import java.util.List;

@Data
public class PageQueryItems {

    private Integer currentPage;
    private Integer pageSize;
    private Long count;
    private List items;

    public PageQueryItems(Integer currentPage, Integer pageSize, Long count, List items) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.count = count;
        this.items = items;
    }

    public PageQueryItems() {
    }
}
