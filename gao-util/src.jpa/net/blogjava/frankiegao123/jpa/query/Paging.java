package net.blogjava.frankiegao123.jpa.query;

public class Paging {

    public final static String PAGE_SIZE_KEY = "jpa.query.page.max";
    public final static int DEFAULT_PAGE_SIZE = 20;
    public final static String DIR_ASC = "ASC";
    public final static String DIR_DESC = "DESC";

    private static int PAGE_SIZE = DEFAULT_PAGE_SIZE;

    static {
        try {
            String str = System.getProperty(PAGE_SIZE_KEY);
            PAGE_SIZE = Integer.parseInt(str);
            if (PAGE_SIZE < 1) {
                PAGE_SIZE = DEFAULT_PAGE_SIZE;
            }
        } catch (Exception e) {
            PAGE_SIZE = DEFAULT_PAGE_SIZE;
        }
    }

    private int pageSize = PAGE_SIZE;
    private int current;
    private int count;
    private int recordNumber;
    private String orderColumn;
    private String orderDirection = DIR_ASC;    

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            return;
        }
        this.pageSize = pageSize;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current < 1) {
            current = 1;
        }
        this.current = current;
        this.recordNumber = (this.current - 1) * pageSize + 1;
    }

    public int getCount() {
        return count;
    }
    
    public int getRecordNumber() {
        return recordNumber;
    }

    public void setCount(int count) {
        if(count < 0) {
            count = 0;
        }
        this.count = count;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        if (DIR_DESC.equalsIgnoreCase(orderDirection)) {
            this.orderDirection = DIR_DESC;
        } else {
            this.orderDirection = DIR_ASC;
        }
    }

    public int getTotalPage() {
        return (count + pageSize - 1) / pageSize;
    }

    int getFirstResult() {
        return (current - 1) * pageSize;
    }
}
