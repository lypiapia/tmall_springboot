package com.levy.utils;

import java.util.List;

import org.springframework.data.domain.Page;

public class Page4Navigator<T> {
	Page<T> pageFromJPA;	//JPA封装的Page对象
	int navigatorPages;	//分页导航显示分页个数
	
	int totalPages;	//分页总数
	
	int number; //当前第几页   。总是非负的
	
	long totalElements; //返回元素总数。
	
	int size; //返回当前页面的大小。
	
	int numberOfElements;  //返回当前页上的元素数。
	
	List<T> content;  //将所有数据返回为List
	
	boolean isHasContent;	//返回数据是否有内容。
	
	boolean isFirst;	//返回当前页是否为第一页。
	
	boolean isLast;		//返回当前页是否是最后一页。
	
	boolean isHasNext;	//是否有下一页
	
	boolean isHasPrevious;	//是否有上一页
	
	int[] navigatePageNums;	//存放前端分页导航的具体分页数，方便前端展示
	
	public Page4Navigator() {
		
	}
	
	public Page4Navigator(Page<T> pageFromJPA,int navigatorPages) {
		this.pageFromJPA = pageFromJPA;
		
		this.navigatorPages = navigatorPages;
		
		this.totalPages = pageFromJPA.getTotalPages();
		
		this.number = pageFromJPA.getNumber();
		
		this.totalElements = pageFromJPA.getTotalElements();
		
		this.size = pageFromJPA.getSize();
		
		this.numberOfElements = pageFromJPA.getNumberOfElements();
		
		this.content = pageFromJPA.getContent();
		
		this.isHasContent = pageFromJPA.hasContent();
		
		this.isHasNext = pageFromJPA.hasNext();
		
		this.isHasPrevious = pageFromJPA.hasPrevious();
		
		this.isFirst = pageFromJPA.isFirst();
		
		this.isLast = pageFromJPA.isLast();
		
		calcNavigatepageNums();
	}
	
	private void calcNavigatepageNums() {
		 int navigatepageNums[];
		 int totalPages = getTotalPages();	//总页数
		 int number = getNumber();	//当前页号
		 
		 if(totalPages <= navigatorPages) {	//总页数还没有要求导航的页数大，直接返回总页数即可
			 navigatepageNums = new int[totalPages];
			 for(int i = 0;i < totalPages;i++) {
				 navigatepageNums[i] = i + 1;
			 }
		 }else {	//当总页数大于导航页数时
			 navigatepageNums = new int[navigatorPages];
			 //开始页号
			 int startPage = number - navigatorPages / 2;
			 //结束页号
			 int endPage = number + navigatorPages / 2;
			 if(startPage < 1) {	//最前页小于1
				 startPage = 1;
				 for(int i = 0;i < navigatorPages;i++) {
					 navigatepageNums[i] = startPage++;
				 }
			 }else if(endPage > totalPages){	//最后页大于总页数
				endPage = totalPages;
				for(int i = navigatorPages - 1;i >= 0;i--) {
					navigatepageNums[i] = endPage--;
				}
			}else {		//正常情况，即所有页都在中间位置
				for(int i = 0;i < navigatorPages;i++) {
					navigatepageNums[i] = startPage++;
				}
			}
		}
		 this.navigatePageNums = navigatepageNums;
		 
	}

	public Page<T> getPageFromJPA() {
		return pageFromJPA;
	}

	public void setPageFromJPA(Page<T> pageFromJPA) {
		this.pageFromJPA = pageFromJPA;
	}

	public int getNavigatorPages() {
		return navigatorPages;
	}

	public void setNavigatorPages(int navigatorPages) {
		this.navigatorPages = navigatorPages;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public boolean isHasContent() {
		return isHasContent;
	}

	public void setHasContent(boolean isHasContent) {
		this.isHasContent = isHasContent;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public boolean isHasNext() {
		return isHasNext;
	}

	public void setHasNext(boolean isHasNext) {
		this.isHasNext = isHasNext;
	}

	public boolean isHasPrevious() {
		return isHasPrevious;
	}

	public void setHasPrevious(boolean isHasPrevious) {
		this.isHasPrevious = isHasPrevious;
	}

	public int[] getNavigatePageNums() {
		return navigatePageNums;
	}

	public void setNavigatePageNums(int[] navigatePageNums) {
		this.navigatePageNums = navigatePageNums;
	}
}
