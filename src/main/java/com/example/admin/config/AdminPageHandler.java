package com.example.admin.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class AdminPageHandler {
    private int totalCnt; //총 게시물 개수
    private int pageSize; //한 페이지에 표시할 데이터 개수
    private int naviSize = 10; //페이지 네비게이션의 개수
    private int totalPage; //전체 페이지의 개수
    private int page;  //현재 페이지 번호
    private int beginPage; //페이지 네비게이션의 첫번째 페이지
    private int endPage; //페이지 네비게이션의 마지막 페이지
    private boolean firstPage; //첫번째 페이지인지 확인
    private boolean lastPage; //마지막 페이지인지 확인

    public AdminPageHandler(int totalCnt, int pageSize, int page) {
        this.totalCnt = totalCnt;
        this.pageSize = pageSize;
        this.page = page;

        // 전체 페이지의 개수 계산하기
        totalPage = (int) Math.ceil((double) totalCnt / pageSize);

        // 시작 페이지 번호 계산
        beginPage = (page - 1) / naviSize * naviSize + 1;

        // 마지막 페이지 번호 계산
        endPage = Math.min(beginPage + naviSize - 1, totalPage);

        // 현재 보이는 페이지 번호가 시작위치인지
        firstPage = (beginPage == 1);

        // 마지막 페이지인지 확인
        lastPage = (endPage == totalPage);
    }
}
