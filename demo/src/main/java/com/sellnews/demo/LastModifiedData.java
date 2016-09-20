package com.sellnews.demo;

import java.sql.Timestamp;

public class LastModifiedData {
	private Timestamp startdate;
	private Timestamp lastdate;

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getLastdate() {
		return lastdate;
	}

	public void setLastdate(Timestamp lastdate) {
		this.lastdate = lastdate;
	}

}
