package com.haiyang.kmeans.entity;

/**
 * @Author: HaiYang
 * @Date: 2020/4/17 11:41
 */
public enum Period {
    //8:30 - 9:00
    MORNING_PEAK(1,30600000L,32400000L),
    //17:00 - 19:00
    EVENING_PEAK(2,61200000L,68400000L);

    private int tag;

    private Long begin;

    private Long end;

    Period(int tag, Long begin, Long end) {
        this.tag = tag;
        this.begin = begin;
        this.end = end;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}
