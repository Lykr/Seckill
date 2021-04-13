package com.learning.seckill.limit;

public class Bucket {
    private final int maxTokenNum;
    private int curTokenNum;
    private int createTokenRate;

    public Bucket(int maxTokenNum, int createTokenRate) {
        this.maxTokenNum = maxTokenNum;
        this.curTokenNum = maxTokenNum;
        this.createTokenRate = createTokenRate;
    }

    public synchronized void createToken() {
        int target = Math.min(maxTokenNum, curTokenNum + createTokenRate);
        curTokenNum = target;
    }

    public synchronized boolean getToken() {
        if (curTokenNum <= 0) return false;
        curTokenNum--;
        return true;
    }

    public int getMaxTokenNum() {
        return maxTokenNum;
    }

    public int getCurTokenNum() {
        return curTokenNum;
    }

    public void setCurTokenNum(int curTokenNum) {
        this.curTokenNum = curTokenNum;
    }

    public int getCreateTokenRate() {
        return createTokenRate;
    }

    public void setCreateTokenRate(int createTokenRate) {
        this.createTokenRate = createTokenRate;
    }
}
