package com.meida.common.util;

import com.meida.front.pay.domain.po.CurrentMember;

public class FrontUtils {
    public static CurrentMember getCurrentMember(){
        CurrentMember item= new CurrentMember();
        item.setMemberId(1l);
        item.setAccount("test1");
        item.setNickname("测试1");
        return item;
    }
}
