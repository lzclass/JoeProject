package com.wiwj.maigcer.model.bean.user;

import java.io.Serializable;

public class Additional implements Serializable {
    private int points;
    private int mobileVisable;
    private boolean isChangeShopApplication;
    private int maxRecommendCount;
    private int msgCount;
    private boolean confirmShowTrack;
    private Sign sign;
    private String shareRange;
    private String editEstate;//1可编辑楼盘0不可编辑楼盘

    public String getEditEstate() {
        return editEstate;
    }

    public void setEditEstate(String editEstate) {
        this.editEstate = editEstate;
    }

    public String getShareRange() {
        if (shareRange == null) {
            return "";
        }
        return shareRange;
    }

    public void setShareRange(String shareRange) {
        this.shareRange = shareRange;
    }

    public class Sign implements Serializable {
        private boolean isSign;
        private String lastSign;
        private int signCount;

        public boolean isSign() {
            return isSign;
        }

        public void setSign(boolean isSign) {
            this.isSign = isSign;
        }

        public String getLastSign() {
            return lastSign;
        }

        public void setLastSign(String lastSign) {
            this.lastSign = lastSign;
        }

        public int getSignCount() {
            return signCount;
        }

        public void setSignCount(int signCount) {
            this.signCount = signCount;
        }

        @Override
        public String toString() {
            return "Sign [isSign=" + isSign + ", lastSign=" + lastSign
                    + ", signCount=" + signCount + "]";
        }

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMobileVisable() {
        return mobileVisable;
    }

    public void setMobileVisable(int mobileVisable) {
        this.mobileVisable = mobileVisable;
    }

    public boolean isChangeShopApplication() {
        return isChangeShopApplication;
    }

    public void setChangeShopApplication(boolean isChangeShopApplication) {
        this.isChangeShopApplication = isChangeShopApplication;
    }

    public int getMaxRecommendCount() {
        return maxRecommendCount;
    }

    public void setMaxRecommendCount(int maxRecommendCount) {
        this.maxRecommendCount = maxRecommendCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public boolean isConfirmShowTrack() {
        return confirmShowTrack;
    }

    public void setConfirmShowTrack(boolean confirmShowTrack) {
        this.confirmShowTrack = confirmShowTrack;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Additional [points=" + points + ", mobileVisable="
                + mobileVisable + ", isChangeShopApplication="
                + isChangeShopApplication + ", maxRecommendCount="
                + maxRecommendCount + ", msgCount=" + msgCount
                + ", confirmShowTrack=" + confirmShowTrack + ", sign=" + sign
                + "]";
    }

}
