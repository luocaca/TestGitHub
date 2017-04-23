package com.hldj.hmyg.bean;

/**
 * Created by Administrator on 2017/4/23.
 */

public class CollectsGsonBean {

    /**
     * code : 1
     * msg : 操作成功
     * data : {"page":{"pageNo":0,"pageSize":20,"total":0,"firstResult":0,"maxResults":20}}
     */

    public String code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * page : {"pageNo":0,"pageSize":20,"total":0,"firstResult":0,"maxResults":20}
         */

        public PageBean page;

        public static class PageBean {
            /**
             * pageNo : 0
             * pageSize : 20
             * total : 0
             * firstResult : 0
             * maxResults : 20
             */

            public int pageNo;
            public int pageSize;
            public int total;
            public int firstResult;
            public int maxResults;
        }
    }
}
