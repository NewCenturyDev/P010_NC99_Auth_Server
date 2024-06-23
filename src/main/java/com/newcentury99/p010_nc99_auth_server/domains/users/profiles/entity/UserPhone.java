package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity;

import com.newcentury99.p010_nc99_auth_server.library_temp.messages.SDKError;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhone {
    // 사용자 대표번호 (휴대폰번호만 가능, 별도 세팅 없으면 복붙)
    private String phone;
    // 사용자 휴대폰번호
    private String mobile;
    // 사용자 유선전화번호 (집/직장 등)
    private String landline;

    public void setPhone(String phoneNumber) {
        UserPhone.checkMobilePhone(phoneNumber);
        this.phone = phoneNumber;
        if (this.mobile == null || this.mobile.isBlank()) {
            this.mobile = phoneNumber;
        }
    }

    public void setMobile(String phoneNumber) {
        UserPhone.checkMobilePhone(phoneNumber);
        this.mobile = phoneNumber;
    }

    public void setLandLine(String phoneNumber) {
        UserPhone.checkLandlinePhone(phoneNumber);
        this.landline = phoneNumber;
    }

    public static void checkMobilePhone(String phoneNumber) throws IllegalArgumentException {
        String mobilePhoneNumPattern = "^010-\\d{4}-\\d{4}$";
        if (!Pattern.compile(mobilePhoneNumPattern).matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException(SDKError.ERROR_AUTH_MS_ILLEGAL_MOBILE_PHONE_FORMAT.getMessage());
        }
    }

    public static void checkLandlinePhone(String phoneNumber) throws IllegalArgumentException {
        String landlineNumPattern = "^((02|031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064|070|080)-(\\d{3}|\\d{4})|1566|1600|1670|1533|1577|1588|1551|1522|1544|1644|1661|1660|1599|1800|1688|1666|1668|1855|1811|1877|1555)-\\d{4}$";
        if (!Pattern.compile(landlineNumPattern).matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException(SDKError.ERROR_AUTH_MS_ILLEGAL_LANDLINE_PHONE_FORMAT.getMessage());
        }
    }
}
