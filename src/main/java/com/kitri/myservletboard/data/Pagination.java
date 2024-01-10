package com.kitri.myservletboard.data;

import com.kitri.myservletboard.dao.BoardJdbcDao;

public class Pagination {
    private int page;
    private int maxRecordsPerPage = 10;
    private int maxPageOnScreen = 5;
    private int startIndex = 0;
    private int totalRecords = 0;

    //이전페이지, 다음페이지가 있는가 확인하기 위한것
    private boolean hasNext = false;
    private boolean hasPrev = false;
    //페이지의 시작점ex)1page, 6page등
    private int startPageOnScreen = 1;
    private int endPageOnScreen = this.maxPageOnScreen;

    public void calcPagination() {
        //페이지네이션 정보 계산 메서드
        //전체 페이지 = 전체레코드/한 페이지당 전체 레코드수
        //ex) 전체레코드 100 / 10 이라면, 전체 페이지는 10이다
        int totalPages = (int)(Math.ceil((double) this.totalRecords / maxRecordsPerPage));
        //
        this.startPageOnScreen =
                (int)((Math.ceil((double) this.page/this.maxPageOnScreen) -1 ) * this.maxPageOnScreen + 1);

        this.endPageOnScreen = this.startPageOnScreen + this.maxPageOnScreen - 1;

        if(this.endPageOnScreen > totalPages){
            this.endPageOnScreen = totalPages;
        }
        //마지막 페이지가 전체 페이지보다 작다면 더 가야할 페이지가 있으므로 다음 버튼 활성화
        if(this.endPageOnScreen < totalPages) {
            this.hasNext = true;
        }
        //시작 페이지가 화면에 표시되는 페이지보다 크다면, 이전 버튼 활성화
        if(this.startPageOnScreen > this.maxPageOnScreen) {
            this.hasPrev = true;
        }
    }

    public Pagination(){};

    public Pagination(int page) {
        this.page = page;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getMaxRecordsPerPage() {
        return maxRecordsPerPage;
    }

    public void setMaxRecordsPerPage(int maxRecordsPerPage) {
        this.maxRecordsPerPage = maxRecordsPerPage;
    }

    public int getMaxPageOnScreen() {
        return maxPageOnScreen;
    }

    public void setMaxPageOnScreen(int maxPageOnScreen) {
        this.maxPageOnScreen = maxPageOnScreen;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public int getStartPageOnScreen() {
        return startPageOnScreen;
    }

    public void setStartPageOnScreen(int startPageOnScreen) {
        this.startPageOnScreen = startPageOnScreen;
    }

    public int getEndPageOnScreen() {
        return endPageOnScreen;
    }

    public void setEndPageOnScreen(int endPageOnScreen) {
        this.endPageOnScreen = endPageOnScreen;
    }
}

