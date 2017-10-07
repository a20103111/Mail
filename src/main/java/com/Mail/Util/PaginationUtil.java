package com.Mail.Util;

/**
 * Created by Lhy on 2017/5/7 0007.
 */
public class PaginationUtil {

    public int totalRows;// 总记录数
    public int pageSize=10; // 每页显示的记录条数
    public int currentPage; // 当前页码

    public int totalPages; // 总页数
    public int pageStartRow; // 这一页的开始行;
    public int pageEndRow; // 这一页的结束行
    public int prePage; // 当前页码的上一页
    public int nextPage; // 当前页码的下一页
    public boolean isPrePage; // 有无上一页
    public boolean isNextPage; // 有无下一页

    public PaginationUtil() {
        super();
        // TODO Auto-generated constructor stub
    }



    public PaginationUtil(int totalRows, int currentPage) {
        super();
        this.totalRows = totalRows;
        this.currentPage = currentPage;

        this.analyse();
    }



    public PaginationUtil(int totalRows, int pageSize, int currentPage) {

        this.totalRows = totalRows;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        this.analyse();

    }

    private void analyse(){

        // 计算出总共需要的页数
        if (totalRows % pageSize == 0) {
            totalPages = totalRows / pageSize;
        } else {
            totalPages = totalRows / pageSize + 1;
        }

        // 计算出当前页的开始行和结束行
        if(currentPage!=totalPages){
            pageStartRow = (currentPage - 1) * pageSize;
            pageEndRow = currentPage*pageSize;
        }else{
            pageStartRow = (currentPage - 1)*pageSize;
            //记录索引从0开始
            pageEndRow = totalRows - pageStartRow;
        }

        // 计算出当前页是否有前一页以及其页数
        if (currentPage == 1) {
            isPrePage = false;
        } else {
            isPrePage = true;
            prePage = currentPage - 1;
        }

        // 计算出当前页是否有后一页以及其页数
        if (currentPage == totalPages) {
            isNextPage = false;
        } else {
            isNextPage = true;
            nextPage = currentPage + 1;
        }

    }



    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageStartRow() {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setIsNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean getIsPrePage() {
        return isPrePage;
    }

    public void setIsPrePage(boolean isPrePage) {
        this.isPrePage = isPrePage;
    }

    public boolean getIsNextPage() {
        return isNextPage;
    }

    public void setNextPage(boolean isNextPage) {
        this.isNextPage = isNextPage;
    }

    public int getPageEndRow() {
        return pageEndRow;
    }

    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }



    public String toString() {
        return "总条数:" + totalRows + "\n每页显示条数:" + pageSize + "\n当前页数:"
                + currentPage + "\n总页数:" + totalPages + "\n这一页的开始行:"
                + pageStartRow + "\n:有无上一页:"+isPrePage+"\n有无下一页:"+isNextPage+"\n上一页:"+prePage+"\n下一页:"+nextPage;
    }

    public static void main(String[] args) {
        System.out.println(new PaginationUtil(20,3,4));//20条记录，每页3条，第4页
    }




}
