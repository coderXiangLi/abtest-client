package com.opensource.leo.meta;

public class Bucket {
    private long id;
    private int start;
    private int end;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean hitBucket(int num) {
        if (num >= start && num < end) {
            return true;
        }
        return false;
    }

}
