package com.a205.brushbuddy.mileage.dto.request;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MileageHistoryRequestDto {
	// 검색 제한 연도 설정, 필요에따라 start, end 제한 추가 가능
	private final int countOfYear = 2;
	private Long userId;
	// all charged spent
	//    private Category category = Category.all;
	private String category = "all";
	private LocalDate date = LocalDate.now();

	private Timestamp defaultStart = new Timestamp(date.getYear(), date.getMonthValue() - 3, 1, 0, 0, 0, 0);
	private Timestamp defaultEnd = new Timestamp(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 23, 59, 59,
		999999);

	private Timestamp start = defaultStart;
	private Timestamp end = defaultEnd;
	boolean isValid = true;

	public void setStartDate(String start) {
		int year = Integer.parseInt(start.substring(0, 2));
		year += 2000;
		int month = Integer.parseInt(start.substring(2, 4));
		int day = Integer.parseInt(start.substring(4));
		this.start = new Timestamp(year, month, day, 0, 0, 0, 0);
		if (!inRange()) {
			System.err.println("startDate is set, isValid is false");
		}
	}

	public void setEndDate(String end) {
		int year = Integer.parseInt(end.substring(0, 2));
		year += 2000;
		int month = Integer.parseInt(end.substring(2, 4));
		int day = Integer.parseInt(end.substring(4));
		this.end = new Timestamp(year, month, day, 23, 59, 59, 999999);
		if (!inRange()) {
			System.err.println("endDate is set, isValid is false");
		}
	}

	public boolean inRange() {
		isValid = start.before(end) && end.getYear() - start.getYear() < countOfYear;
		return isValid;
	}
}
