package com.meida.base.domain.vo;

/**
 * 分页类
 * 
 * @author BING
 */
public class Pagination {
	// Alt+Insert 生成get set
	public Pagination() {
		this.currentPage = 1;
		this.pageSize = 20;
	}

	public Pagination(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
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
	private long pageCount;
	/**
	 * 总记录数
	 */
	private long totalRecord;

	public int getPageIndex() {
		return this.currentPage-1;
	}

//	public void setPageIndex(int pageIndex) {
//		this.pageIndex = pageIndex;
//	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if(currentPage<=0)
		{
			currentPage=1;
		}
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getPageCount() {
		return this.totalRecord % this.pageSize == 0 ? this.totalRecord / this.pageSize : this.totalRecord / this.pageSize + 1;		
	}

//	public void setPageCount(long pageCount) {
//		this.pageCount = pageCount;
//	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

}
