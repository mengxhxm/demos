package com.zc.publics;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public class SplitPage {
	
	private int start = 0;
	private int length = 10;
	private Integer pageSize=10; //页面大小
    private Integer pageNo=1; //当前页码
	private List<OrderBy> orders = new ArrayList<OrderBy>();
	
	public SplitPage(){
		
	}
	
	public SplitPage(int start,int length){
		this.start = start;
		this.length = length;
	}
	
	public int getLimit() {
		return length;
	}
	public int getOffset() {
		return start;
	}
	
	 public PageBounds toPageBounds(){
		 PageBounds pageBounds = new PageBounds(start/length+1, length);
		 for(OrderBy order:orders){
			 if(StringUtils.isNotBlank(order.getColumn())){
				 pageBounds.getOrders().add(Order.create(order.getColumn(), order.getBy()));
			 }
		 }
	     return pageBounds;
	 }

	public List<OrderBy> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderBy> orders) {
		this.orders = orders;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		this.length = pageSize;
		//根据页号设置开始记录数
		this.start = (pageNo-1)*pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		//根据页号设置开始记录数
		this.start = (pageNo-1)*pageSize;
	}

}
