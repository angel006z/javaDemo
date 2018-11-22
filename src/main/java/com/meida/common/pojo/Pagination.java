package com.meida.common.pojo;
/**
 * 分页类
 * @author BING
 */
public class Pagination {
	//Alt+Insert  生成get set
    public Pagination() {        
        this.currentPage=1;
        this.pageSize=20;
    }
    public Pagination(int currentPage,int pageSize) {
    	this.currentPage=currentPage;
    	this.pageSize=pageSize;
    }

    /**
     * 页索引
     */
    private int pageIndex;
    /**
     * 页
     */
    private int currentPage;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 总记录数
     */
    private int totalRecord;
    
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}   

   
}
