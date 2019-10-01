package com.meida.front.pay.dto;

public class CurrentMemberDto {
    //会员id
    private  Long memberId;

    //会员昵称
    private  String nickname;

    //账号
    private  String account;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
