package com.kitri.myservletboard.data;

import com.kitri.myservletboard.dao.BoardJdbcDao;

public class Pagination {
    private int page;
    private int maxRecordsPerPage;
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
        //시작 페이지는 지금 페이지 / 한 페이지의 맥스 수 - 1 * 5 +1
        //ex) 내 페이지가 1이라면,
        // 첫째로 (1/5) => 0.몇 올림해서 1.
        // 둘째로 1 - 1 = 0
        // 셋째로 0 * 5 = 0
        // 넷째로 0에 +1 해서 1
        this.startPageOnScreen =
                (int)((Math.ceil((double) this.page/this.maxPageOnScreen) -1 ) * this.maxPageOnScreen + 1);
        //한 화면의 끝 페이지는 (1+5) -1 해서 5
        this.endPageOnScreen = this.startPageOnScreen + this.maxPageOnScreen - 1;
        //만약 화면 끝 페이지가 전체 페이지보다 크다면, 화면에 표시되는 끝 페이지를 5개씩 표시하지 않고, 전체 페이지의 마지막 수로 표시
        if(this.endPageOnScreen > totalPages){
            if(totalPages ==0){
                this.endPageOnScreen = 1;
            } else {
                this.endPageOnScreen = totalPages;
            }
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

    public Pagination(int page){
        this.page = page;
    }

    public Pagination(int page,int maxRecordsPerPage) {
        this.page = page;
        this.maxRecordsPerPage = maxRecordsPerPage;
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

