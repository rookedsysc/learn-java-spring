package org.example;

import org.example.excel.Excel;
import org.example.model.MemberDTO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MemberDTO member = inputMember();
        Excel excel = new Excel();
        MemberDTO[] customMembers = {member};
        try {
            excel.write(customMembers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static MemberDTO inputMember()  {
        Scanner scanner = new Scanner(System.in);
        // 사용자로부터 인풋 받음
        System.out.print("이름을 입력해주세요 : ");
        String name = scanner.nextLine();

        System.out.print("생년월일을 입력해주세요 : ");
        String birthDateString = scanner.nextLine();
        Date birthDate = convertStringToDate(birthDateString);

        System.out.print("휴대폰 번호를 입력해주세요 : ");
        String phone = scanner.nextLine();

        System.out.print("결혼 여부를 입력해주세요(y or n) : ");
        String isMarriedString = scanner.nextLine();
        boolean isMarried = isMarriedBoolean(isMarriedString);
        return new MemberDTO(name, birthDate, phone, isMarried);
    }

    static private boolean isMarriedBoolean(String isMarried) {
        if(isMarried.equals("y")) {
            return true;
        } else if (isMarried.equals("n")) {
            return false;
        } else {
            System.out.println("잘못된 입력입니다.");
            return false;
        }
    }
    static private Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(dateString);
        } catch (ParseException e) {
            LocalDate today = LocalDate.now();
            // 오늘 날짜 return
            return Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }
}