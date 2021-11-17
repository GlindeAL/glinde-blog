package me.glinde.blog.pojo;

public class QueryInfo {
    private String query;
    private int pageNum=1;
    private int pageSize=1;

    public QueryInfo() {}

    public QueryInfo(String query, int pageNum, int pageSize) {
        this.query = query;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "QueryInfo{" +
                "query='" + query + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
