package com.hldj.hmyg.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22 0022.
 */

public class QuickPriceGsonBean implements Serializable {


    /**
     * code : 1
     * msg : 操作成功
     */

    public String code;
    public String msg;
    public DatabeanX data;

    public static class DatabeanX implements Serializable {


        public Pagebean page;

        public static class Pagebean implements Serializable {
            /**
             * pageNo : 0
             * pageSize : 10
             * total : 2
             * data : [{"id":"76d9e45b904c4a84b2f782a63884edf6","createBy":"e6ae79aad8254ffcb0cfe8fd11844c89","createDate":"2017-04-10 15:28:07","cityCode":"3501","cityName":"福州","prCode":"35","ciCode":"3501","coCode":"","twCode":"","projectName":"海峡国际会展中心绿化提升","projectType":"direct","consumerId":"833346692f6348aeb2149d5e23606a01","consumerUserId":"","consumerName":"融越园林","name":"海峡国际会展中心绿化提升","num":"P0000470","projectId":"3e01cb03fc3147229630e4261165ec28","receiptDate":"2017-04-12","publishDate":"2017-04-10 15:28:41","closeDate":"2017-04-30 15:29","needInvoice":false,"customerId":"e6ae79aad8254ffcb0cfe8fd11844c89","status":"published","source":"admin","quoteDesc":"<p>\n\t<br />\n<\/p>\n<p>\n\t用苗地：福州\n<\/p>\n<p>\n\t报价要求：（包含以下费用：1、上车费用。2、税金。<span style=\"color:#E53333;\"><\/span>）&nbsp;\n<\/p>\n<p>\n\t发票要求：增值税普通发票或增值税专用发票<br />\n<span style=\"color:#E53333;line-height:1.5;\"><\/span> \n<\/p>\n<p>\n\t<span style=\"color:#E53333;line-height:1.5;\">种植要求：地苗<\/span> \n<\/p>\n<p>\n\t<span style=\"color:#E53333;line-height:1.5;\">质量要求：8分苗<br />\n<\/span> \n<\/p>\n<p>\n\t<span style=\"color:#E53333;line-height:1.5;\">测量要求：胸径离地1.2米处量<\/span> \n<\/p>\n<p>\n\t<span style=\"color:#E53333;line-height:1.5;\"><span style=\"color:#E53333;\">说明：调苗时间依项目进度定。<\/span><br />\n<\/span> \n<\/p>\n<p>\n\t<span style=\"color:#E53333;\"><\/span>报价限制：请供应商报价时对应品种必须上传真实有效的图片，（Ps:有照片者优先考虑）<br />\n到场规格，质量不合格做退货处理。\n<\/p>\n<p>\n\t请供应商报价时对应品种必须上传真实有效的图片，（Ps:有照片者优先考虑）\n<\/p>\n<p>\n\t<br />\n<\/p>\n<p>\n\t<br />\n<\/p>","isCooper":false,"type":"quoting","dispatchPhone":"18350218809","dispatchName":"黄志雄","isPrivate":false,"authcPhone":"","buyer":{"id":"-1","prCode":"","ciCode":"","coCode":"","twCode":"","phone":"4006-579-888","companyName":"厦门花木易购电子商务有限公司","isInvoices":false,"agentTypeName":"","isDirectAgent":false,"displayName":"厦门花木易购电子商务有限公司","adminDisplayName":"厦门花木易购电子商务有限公司","displayPhone":"4006-579-888"},"statusName":"已发布","quoteCountJson":1,"itemCountJson":1,"lastDays":7,"itemNameList":["香樟"],"purchaseFormId":"76d9e45b904c4a84b2f782a63884edf6","typeName":"采购中","blurProjectName":"海峡****","blurName":"海峡****"},{"id":"21fdc65b66d7408ca0f546518efb9081","createBy":"1","createDate":"2017-03-09 15:58:54","cityCode":"3502","cityName":"厦门","prCode":"35","ciCode":"3502","coCode":"","twCode":"","projectName":"代购模式测试项目","projectType":"protocol","consumerId":"a6e6c779fefb4a41b0188dfc5ec76ad5","consumerUserId":"5315ff5c-e118-4a5d-abb7-338c90d8ce73","consumerName":"新模式测试客户","name":"代购模式采购","num":"P0000318","projectId":"2c08b5b58e5d45a0b299bdeb44b72f37","receiptDate":"2017-03-31","publishDate":"2017-03-09 16:26:12","closeDate":"2017-05-27 15:57","needInvoice":false,"customerId":"1","status":"published","source":"admin","quoteDesc":"<p>\n\t报价截止时间：XXXX年XX月XX日\n<\/p>\n<p>\n\t用苗地：XXXX&nbsp;<br />\n报价要求：<span style=\"color:#E53333;\">XXX<\/span>（包含以下费用：1、上车费用。2、税金。）&nbsp;<br />\n发票要求：XXXX&nbsp;<br />\n<span style=\"color:#E53333;\">质量要求：XXXX（接近房地产货）<\/span><br />\n<span style=\"color:#E53333;\">测量要求：XXXXX。<\/span> \n<\/p>\n<p>\n\t<span style=\"color:#E53333;line-height:1.5;\">种植要求：XXXX<\/span> \n<\/p>\n<p>\n\t报价限制：请供应商报价时对应品种必须上传真实有效的图片，（Ps:有照片者优先考虑）\n<\/p>","isCooper":false,"type":"quoting","dispatchPhone":"18250876026","dispatchName":"叶珊珊","isPrivate":false,"authcPhone":"","buyer":{"id":"-1","prCode":"","ciCode":"","coCode":"","twCode":"","phone":"4006-579-888","companyName":"厦门花木易购电子商务有限公司","isInvoices":false,"agentTypeName":"","isDirectAgent":false,"displayName":"厦门花木易购电子商务有限公司","adminDisplayName":"厦门花木易购电子商务有限公司","displayPhone":"4006-579-888"},"statusName":"已发布","quoteCountJson":11,"itemCountJson":4,"lastDays":35,"itemNameList":["白蜡","红花锦鸡儿","红钻","白花鸡蛋花"],"purchaseFormId":"21fdc65b66d7408ca0f546518efb9081","typeName":"采购中","blurProjectName":"代购****","blurName":"代购****"}]
             * firstResult : 0
             * maxResults : 10
             */

            public int pageNo;
            public int pageSize;
            public int total;
            public int firstResult;
            public int maxResults;
            public List<Databean> data;

            public static class Databean implements Serializable {
                /**
                 * id : 76d9e45b904c4a84b2f782a63884edf6
                 * createBy : e6ae79aad8254ffcb0cfe8fd11844c89
                 * createDate : 2017-04-10 15:28:07
                 * cityCode : 3501
                 * cityName : 福州
                 * prCode : 35
                 * ciCode : 3501
                 * coCode :
                 * twCode :
                 * projectName : 海峡国际会展中心绿化提升
                 * projectType : direct
                 * consumerId : 833346692f6348aeb2149d5e23606a01
                 * consumerUserId :
                 * consumerName : 融越园林
                 * name : 海峡国际会展中心绿化提升
                 * num : P0000470
                 * projectId : 3e01cb03fc3147229630e4261165ec28
                 * receiptDate : 2017-04-12
                 * publishDate : 2017-04-10 15:28:41
                 * closeDate : 2017-04-30 15:29
                 * needInvoice : false
                 * customerId : e6ae79aad8254ffcb0cfe8fd11844c89
                 * status : published
                 * source : admin
                 * quoteDesc : <p>
                 * <br />
                 * </p>
                 * <p>
                 * 用苗地：福州
                 * </p>
                 * <p>
                 * 报价要求：（包含以下费用：1、上车费用。2、税金。<span style="color:#E53333;"></span>）&nbsp;
                 * </p>
                 * <p>
                 * 发票要求：增值税普通发票或增值税专用发票<br />
                 * <span style="color:#E53333;line-height:1.5;"></span>
                 * </p>
                 * <p>
                 * <span style="color:#E53333;line-height:1.5;">种植要求：地苗</span>
                 * </p>
                 * <p>
                 * <span style="color:#E53333;line-height:1.5;">质量要求：8分苗<br />
                 * </span>
                 * </p>
                 * <p>
                 * <span style="color:#E53333;line-height:1.5;">测量要求：胸径离地1.2米处量</span>
                 * </p>
                 * <p>
                 * <span style="color:#E53333;line-height:1.5;"><span style="color:#E53333;">说明：调苗时间依项目进度定。</span><br />
                 * </span>
                 * </p>
                 * <p>
                 * <span style="color:#E53333;"></span>报价限制：请供应商报价时对应品种必须上传真实有效的图片，（Ps:有照片者优先考虑）<br />
                 * 到场规格，质量不合格做退货处理。
                 * </p>
                 * <p>
                 * 请供应商报价时对应品种必须上传真实有效的图片，（Ps:有照片者优先考虑）
                 * </p>
                 * <p>
                 * <br />
                 * </p>
                 * <p>
                 * <br />
                 * </p>
                 * isCooper : false
                 * type : quoting
                 * dispatchPhone : 18350218809
                 * dispatchName : 黄志雄
                 * isPrivate : false
                 * authcPhone :
                 * buyer : {"id":"-1","prCode":"","ciCode":"","coCode":"","twCode":"","phone":"4006-579-888","companyName":"厦门花木易购电子商务有限公司","isInvoices":false,"agentTypeName":"","isDirectAgent":false,"displayName":"厦门花木易购电子商务有限公司","adminDisplayName":"厦门花木易购电子商务有限公司","displayPhone":"4006-579-888"}
                 * statusName : 已发布
                 * quoteCountJson : 1
                 * itemCountJson : 1
                 * lastDays : 7
                 * itemNameList : ["香樟"]
                 * purchaseFormId : 76d9e45b904c4a84b2f782a63884edf6
                 * typeName : 采购中
                 * blurProjectName : 海峡****
                 * blurName : 海峡****
                 */

                public String id;
                public String createBy;
                public String createDate;
                public String cityCode;
                public String cityName;
                public String prCode;
                public String ciCode;
                public String coCode;
                public String twCode;
                public String projectName;
                public String projectType;
                public String consumerId;
                public String consumerUserId;
                public String consumerName;
                public String name;
                public String num;
                public String projectId;
                public String receiptDate;
                public String publishDate;
                public String closeDate;
                public boolean needInvoice;
                public String customerId;
                public String status;
                public String source;
                public String quoteDesc;
                public boolean isCooper;
                public String type;
                public String dispatchPhone;
                public String dispatchName;
                public boolean isPrivate;
                public String authcPhone;
                public Buyerbean buyer;
                public String statusName;
                public int quoteCountJson;
                public int itemCountJson;
                public int lastDays;
                public String purchaseFormId;
                public String typeName;
                public String blurProjectName;
                public String blurName;
                public List<String> itemNameList;

                public static class Buyerbean implements Serializable {
                    /**
                     * id : -1
                     * prCode :
                     * ciCode :
                     * coCode :
                     * twCode :
                     * phone : 4006-579-888
                     * companyName : 厦门花木易购电子商务有限公司
                     * isInvoices : false
                     * agentTypeName :
                     * isDirectAgent : false
                     * displayName : 厦门花木易购电子商务有限公司
                     * adminDisplayName : 厦门花木易购电子商务有限公司
                     * displayPhone : 4006-579-888
                     */

                    public String id;
                    public String prCode;
                    public String ciCode;
                    public String coCode;
                    public String twCode;
                    public String phone;
                    public String companyName;
                    public boolean isInvoices;
                    public String agentTypeName;
                    public boolean isDirectAgent;
                    public String displayName;
                    public String adminDisplayName;
                    public String displayPhone;
                }
            }
        }
    }
}
