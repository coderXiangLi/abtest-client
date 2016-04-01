package com.opensource.leo.model;

import java.util.ArrayList;
import java.util.List;

public class AbResponse {

    private List<AbEntity> entrys = new ArrayList<AbEntity>();

    public void addEntity(AbEntity entry) {
        if (entry != null) {
            this.entrys.add(entry);
        }
    }

    public List<AbEntity> getEntrys() {
        return entrys;
    }


    public static class AbEntity {
        private String exprName;
        private long bucketId;

        public String getExprName() {
            return exprName;
        }

        public void setExprName(String exprName) {
            this.exprName = exprName;
        }

        public long getBucketId() {
            return bucketId;
        }

        public void setBucketId(long bucketId) {
            this.bucketId = bucketId;
        }
    }
}
