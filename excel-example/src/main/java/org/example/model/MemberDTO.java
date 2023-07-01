package org.example.model;

import java.util.Date;

public class MemberDTO {
    private final String name;
    private final Date birthDate;
    private final String phone;

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isMarried() {
        return isMarried;
    }

    private final boolean isMarried;

    public MemberDTO(String name, Date birthDate, String phone, boolean isMarried) {
        this.birthDate = birthDate;
        this.name = name;
        this.phone = phone;
        this.isMarried = isMarried;
    }

    @Override
    public String toString() {
        return "CustomMember{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", isMarried=" + isMarried +
                '}';
    }
}
