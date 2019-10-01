package com.meida.common.util;

import com.meida.front.pay.dto.CurrentMemberDto;

public class FrontUtils {
    public static CurrentMemberDto getCurrentMemberDto(){
        CurrentMemberDto item= new CurrentMemberDto();
        item.setMemberId(1l);
        item.setAccount("test1");
        item.setNickname("测试1");
        return item;
    }
}
