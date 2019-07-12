package com.flyex.pageTopn;

public class PageCount implements Comparable<PageCount> {
    private String page;
    private int count;
    public void set(String page,int count){
        this.page = page;
        this.count = count;
    }
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int compareTo(PageCount o) {
        return o.getCount()-this.getCount()==0?o.getPage().compareTo(this.page):o.getCount()-this.count;
    }

    @Override
    public String toString() {
        return this.page+"\t"+this.count;
    }
}
