package com.kitri.myservletboard.data;

public class Pagination {
    private int page;
    private int rowPerPage = 15;
    private int linePerPage = 10;
    private int totalRow = 0;
    private int viewStartPage = 1;
    private int viewEndPage = this.linePerPage;
    private boolean hasPrev = false;
    private boolean hasNext = false;
    public Pagination(){};

    public Pagination(int page) {
        this.page = page;
    }

    public void calcPagination() {
//        총 페이지 개수 = Math.ceil(전체 컨텐츠 개수 / 한 페이지에 보여줄 컨텐츠의 개수)
        int totalPages = (int)Math.ceil((double)this.totalRow / this.rowPerPage);
//        화면에 보여질 페이지의 첫번째 페이지 번호 = ((페이지 그룹 번호 - 1) * 한 화면에 보여줄 페이지의 개수) + 1
        this.viewStartPage =
                (int)((Math.ceil((double) this.page/this.linePerPage) -1 ) * this.linePerPage + 1);
//        화면에 보여질 페이지의 마지막 페이지 번호 = 페이지 그룹 번호 * 한 화면에 보여줄 페이지의 개수
        this.viewEndPage = this.viewStartPage + this.linePerPage - 1;

        if(this.viewEndPage > totalPages){
            if (totalPages != 0) {
                this.viewEndPage = totalPages;
            } else {
                this.viewEndPage = 1;
            }
        }
        //마지막 페이지가 전체 페이지보다 작다면 더 가야할 페이지가 있으므로 다음 버튼 활성화
        if(this.viewEndPage < totalPages) {
            this.hasNext = true;
        }
        //시작 페이지가 화면에 표시되는 페이지보다 크다면, 이전 버튼 활성화
        if(this.viewStartPage > this.linePerPage) {
            this.hasPrev = true;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(int rowPerPage) {
        this.rowPerPage = rowPerPage;
    }

    public int getLinePerPage() {
        return linePerPage;
    }

    public void setLinePerPage(int linePerPage) {
        this.linePerPage = linePerPage;
    }

    public int getTotlaRow() {
        return totalRow;
    }

    public void setTotlaRow(int totlaRow) {
        this.totalRow = totlaRow;
    }

    public int getViewStartPage() {
        return viewStartPage;
    }

    public void setViewStartPage(int viewStartPage) {
        this.viewStartPage = viewStartPage;
    }

    public int getViewEndPage() {
        return viewEndPage;
    }

    public void setViewEndPage(int viewEndPage) {
        this.viewEndPage = viewEndPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
