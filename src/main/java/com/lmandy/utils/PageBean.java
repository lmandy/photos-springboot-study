package com.lmandy.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by lmandy on 2017/10/18.
 */
public class PageBean <T>{

    /**
     * 当前页码
     */
    private Integer pageNo;
    /**
     * 每页显示条数
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 查询结果记录
     */
    private List<T> TotalResults;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 开始索引
     */
    private Integer firstIndex;
    /**
     * 查询条件
     */
    private Map<String,Object> conMap;
    /**
     * 路径
     */
    private String url;

    private List<Integer> nums;


    public PageBean() {
    }

    public PageBean(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo == null ? 1:pageNo;
        this.pageSize = pageSize == null ? 10:pageSize;

        firstIndex = (this.pageNo - 1) * this.pageSize;
    }
    public PageBean(Integer pageNo, Integer pageSize,Map conMap) {
        this.pageNo = pageNo == null ? 1:pageNo;
        this.pageSize = pageSize == null ? 10:pageSize;
        this.conMap = conMap;

        firstIndex = (this.pageNo - 1) * this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {

        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getTotalResults() {
        return TotalResults;
    }

    public void setTotalResults(List<T> totalResults) {
        TotalResults = totalResults;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount == null ? 0 : totalCount;

        this.totalPage = (totalCount % pageSize == 0) ?  (totalCount / pageSize) :  (totalCount / pageSize + 1);


        setNums(new LinkedList<>());

    }

    public Integer getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(Integer firstIndex) {
        this.firstIndex = firstIndex;
    }

    public Map<String, Object> getConMap() {
        return conMap;
    }

    public void setConMap(Map<String, Object> conMap) {
        this.conMap = conMap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", TotalResults=" + TotalResults +
                ", totalCount=" + totalCount +
                ", firstIndex=" + firstIndex +
                ", conMap=" + conMap +
                '}';
    }

    public List<Integer> getNums() {
        return nums;
    }

    /**
     * 页面导航显示的页码 每页显示5个页码
     * @param nums
     */
    public void setNums(List<Integer> nums) {

        if( totalPage <=5){
            for (int i=1;i <= totalPage; i++)
                nums.add(i);
        }else {
            if(pageNo <3){
                for (int i=1;i <= 5; i++)
                    nums.add(i);
            }else {
                if((pageNo + 2)>totalPage){
                    for (int i=0;i <= 4; i++)
                        nums.add(totalPage - i);
                    Collections.reverse(nums);
                }else {
                    nums.add(pageNo - 2);
                    nums.add(pageNo - 1);
                    nums.add(pageNo);
                    nums.add(pageNo + 1);
                    nums.add(pageNo + 2);
                }
            }

        }

        this.nums = nums;
    }

}
