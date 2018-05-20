package com.zs.CRUD_3.util;

import java.io.Serializable;

import com.github.pagehelper.PageHelper;
/**
 * 分页器 抽象类
 * @author Administrator
 *
 * @param <T>
 */
public abstract class AbstractPageForm<T extends AbstractPageForm<T>> implements Serializable{

	private static final long serialVersionUID = 1L;
	  
    /**
     * @Description 页码为首页
     */
    protected int pageNum = 1;
    
    /**
     * @Description 每页显示数量，默认为10
     */
    protected int pageSize = 2;
  
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
  
    /**
     * @Title enablePaging
     * @Description 启用分页
     * @return
     */
    @SuppressWarnings("unchecked")
    public final T enablePaging() {
        PageHelper.startPage(pageNum, pageSize);
        return (T) this;
    }
  
  
}

