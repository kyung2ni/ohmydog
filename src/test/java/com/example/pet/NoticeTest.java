package com.example.pet;

import com.example.pet.notice.dto.NoticeDto;
import com.example.pet.notice.mapper.NoticeMapper;
import com.example.pet.notice.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NoticeTest {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    public void insertNoticeTest() {
        NoticeDto notice = new NoticeDto();

        for (int i=1; i<100; i++) {
            notice.setNoticeTitle("공지사항" + i);
            notice.setNoticeContent("공지사항내용" + i);

            int result = noticeMapper.insertNT(notice);
            assertThat(result).isEqualTo(1);
        }
    }
}
