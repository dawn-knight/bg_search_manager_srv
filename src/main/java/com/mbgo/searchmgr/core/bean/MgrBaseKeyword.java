package com.mbgo.searchmgr.core.bean;

import java.util.Date;

public class MgrBaseKeyword {


	private Long id;

    private String keyword;

    private String wordCode;

    private Long searchCount;

    private Long rscount;

    private Long addTime;

    private Date lastUpdate;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getWordCode() {
        return wordCode;
    }

    public void setWordCode(String wordCode) {
        this.wordCode = wordCode == null ? null : wordCode.trim();
    }

    public Long getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Long searchCount) {
        this.searchCount = searchCount;
    }

    public Long getRscount() {
        return rscount;
    }

    public void setRscount(Long rscount) {
        this.rscount = rscount;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    

	@Override
	public String toString() {
		return "MgrBaseKeyword [id=" + id + ", keyword=" + keyword
				+ ", wordCode=" + wordCode + ", searchCount=" + searchCount
				+ ", rscount=" + rscount + ", addTime=" + addTime
				+ ", lastUpdate=" + lastUpdate + "]";
	}
    
    
}