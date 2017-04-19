package com.hldj.hmyg.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class SaveSeedingGsonBean {

    /**
     * code : 1
     * msg : 操作成功
     * data : {"typeList":[{"id":"74b0e9c44dd0499381a1adcadae6b4c9","createBy":"1","createDate":"2016-02-29 15:06:37","name":"乔木","aliasName":"","parentId":"","level":0,"firstPinyin":"qm","fullPinyin":"qiaomu","seedlingParams":"dbh,height,crown","sort":1,"defaultUnit":"plant","defaultValidity":180,"paramsList":[{"value":"dbh","required":true},{"value":"height","required":false},{"value":"crown","required":false}],"plantTypeList":[{"text":"地栽苗","value":"planted"},{"text":"移植苗","value":"transplant"},{"text":"假植苗","value":"heelin"},{"text":"容器苗","value":"container"}],"qualityTypeList":[{"text":"全冠苗","value":"quanguan"},{"text":"大骨架","value":"dagujia"},{"text":"小骨架","value":"xiaogujia"},{"text":"截杆苗","value":"jiegan"}],"qualityGradeList":[{"text":"A货","value":"A"},{"text":"B货","value":"B"}],"mainSpec":"dbh"},{"id":"4bf45dae8c8e4ad5a0bc4df7cdc3d294","createBy":"1","createDate":"2016-02-29 15:06:37","name":"灌木","aliasName":"","parentId":"","level":0,"firstPinyin":"gm","fullPinyin":"guanmu","seedlingParams":"diameter,height,crown","sort":2,"defaultUnit":"plant","defaultValidity":90,"paramsList":[{"value":"diameter","required":true},{"value":"height","required":false},{"value":"crown","required":false}],"plantTypeList":[{"text":"地栽苗","value":"planted"},{"text":"假植苗","value":"heelin"},{"text":"容器苗","value":"container"}],"qualityGradeList":[{"text":"A货","value":"A"},{"text":"B货","value":"B"}],"mainSpec":"diameter"},{"id":"dcdce5c0eea74f3e8357aa58ae74651b","createBy":"1","createDate":"2016-02-29 15:06:37","name":"桩景","aliasName":"","parentId":"","level":0,"firstPinyin":"zj","fullPinyin":"zhuangjing","seedlingParams":"diameter,height,crown","sort":3,"defaultUnit":"plant","defaultValidity":180,"paramsList":[{"value":"diameter","required":true},{"value":"height","required":false},{"value":"crown","required":false}],"plantTypeList":[{"text":"移植苗","value":"transplant"},{"text":"容器苗","value":"container"},{"text":"假植苗","value":"heelin"}],"qualityGradeList":[{"text":"A货","value":"A"},{"text":"B货","value":"B"}],"mainSpec":"diameter"},{"id":"4d80082e024d4e778e131c2d47c37400","createBy":"1","createDate":"2016-02-29 15:06:37","name":"地被","aliasName":"","level":0,"firstPinyin":"db","fullPinyin":"dibei","seedlingParams":"height,crown","sort":4,"defaultValidity":30,"paramsList":[{"value":"height","required":false},{"value":"crown","required":true}],"plantTypeList":[{"text":"地栽苗","value":"planted"},{"text":"容器苗","value":"container"}],"mainSpec":"crown"},{"id":"ce14746cde764456990f717a82ffb0c0","createBy":"1","createDate":"2016-02-29 15:06:37","name":"棕榈/苏铁","aliasName":"","parentId":"","level":0,"firstPinyin":"zl","fullPinyin":"zonglv","seedlingParams":"diameter,crown,offbarHeight","sort":6,"defaultUnit":"plant","defaultValidity":180,"paramsList":[{"value":"diameter","required":false},{"value":"crown","required":false},{"value":"offbarHeight","required":true}],"plantTypeList":[{"text":"地栽苗","value":"planted"},{"text":"移植苗","value":"transplant"},{"text":"假植苗","value":"heelin"},{"text":"容器苗","value":"container"}],"qualityGradeList":[{"text":"A货","value":"A"},{"text":"B货","value":"B"}],"mainSpec":"offbarHeight"}],"plantTypeList":[{"text":"地栽苗","value":"planted"},{"text":"移植苗","value":"transplant"},{"text":"假植苗","value":"heelin"},{"text":"容器苗","value":"container"}]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TypeListBean> typeList;
        private List<TypeListBean.PlantTypeListBean> plantTypeList;

        public List<TypeListBean> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<TypeListBean> typeList) {
            this.typeList = typeList;
        }

        public List<TypeListBean.PlantTypeListBean> getPlantTypeList() {
            return plantTypeList;
        }

        public void setPlantTypeList(List<TypeListBean.PlantTypeListBean> plantTypeList) {
            this.plantTypeList = plantTypeList;
        }

        public static class TypeListBean {
            /**
             * id : 74b0e9c44dd0499381a1adcadae6b4c9
             * createBy : 1
             * createDate : 2016-02-29 15:06:37
             * name : 乔木
             * aliasName :
             * parentId :
             * level : 0
             * firstPinyin : qm
             * fullPinyin : qiaomu
             * seedlingParams : dbh,height,crown
             * sort : 1
             * defaultUnit : plant
             * defaultValidity : 180
             * paramsList : [{"value":"dbh","required":true},{"value":"height","required":false},{"value":"crown","required":false}]
             * plantTypeList : [{"text":"地栽苗","value":"planted"},{"text":"移植苗","value":"transplant"},{"text":"假植苗","value":"heelin"},{"text":"容器苗","value":"container"}]
             * qualityTypeList : [{"text":"全冠苗","value":"quanguan"},{"text":"大骨架","value":"dagujia"},{"text":"小骨架","value":"xiaogujia"},{"text":"截杆苗","value":"jiegan"}]
             * qualityGradeList : [{"text":"A货","value":"A"},{"text":"B货","value":"B"}]
             * mainSpec : dbh
             */

            private String id;
            private String createBy;
            private String createDate;
            private String name;
            private String aliasName;
            private String parentId;
            private int level;
            private String firstPinyin;
            private String fullPinyin;
            private String seedlingParams;
            private int sort;
            private String defaultUnit;
            private int defaultValidity;
            private String mainSpec;
            private List<ParamsListBean> paramsList;
            private List<PlantTypeListBean> plantTypeList;
            private List<QualityTypeListBean> qualityTypeList;
            private List<QualityGradeListBean> qualityGradeList;

            @Override
            public String toString() {
                return "TypeListBean{" +
                        "id='" + id + '\'' +
                        ", createBy='" + createBy + '\'' +
                        ", createDate='" + createDate + '\'' +
                        ", name='" + name + '\'' +
                        ", aliasName='" + aliasName + '\'' +
                        ", parentId='" + parentId + '\'' +
                        ", level=" + level +
                        ", firstPinyin='" + firstPinyin + '\'' +
                        ", fullPinyin='" + fullPinyin + '\'' +
                        ", seedlingParams='" + seedlingParams + '\'' +
                        ", sort=" + sort +
                        ", defaultUnit='" + defaultUnit + '\'' +
                        ", defaultValidity=" + defaultValidity +
                        ", mainSpec='" + mainSpec + '\'' +
                        ", paramsList=" + paramsList +
                        ", plantTypeList=" + plantTypeList +
                        ", qualityTypeList=" + qualityTypeList +
                        ", qualityGradeList=" + qualityGradeList +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAliasName() {
                return aliasName;
            }

            public void setAliasName(String aliasName) {
                this.aliasName = aliasName;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getFirstPinyin() {
                return firstPinyin;
            }

            public void setFirstPinyin(String firstPinyin) {
                this.firstPinyin = firstPinyin;
            }

            public String getFullPinyin() {
                return fullPinyin;
            }

            public void setFullPinyin(String fullPinyin) {
                this.fullPinyin = fullPinyin;
            }

            public String getSeedlingParams() {
                return seedlingParams;
            }

            public void setSeedlingParams(String seedlingParams) {
                this.seedlingParams = seedlingParams;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getDefaultUnit() {
                return defaultUnit;
            }

            public void setDefaultUnit(String defaultUnit) {
                this.defaultUnit = defaultUnit;
            }

            public int getDefaultValidity() {
                return defaultValidity;
            }

            public void setDefaultValidity(int defaultValidity) {
                this.defaultValidity = defaultValidity;
            }

            public String getMainSpec() {
                return mainSpec;
            }

            public void setMainSpec(String mainSpec) {
                this.mainSpec = mainSpec;
            }

            public List<ParamsListBean> getParamsList() {
                return paramsList;
            }

            public void setParamsList(List<ParamsListBean> paramsList) {
                this.paramsList = paramsList;
            }

            public List<PlantTypeListBean> getPlantTypeList() {
                return plantTypeList;
            }

            public void setPlantTypeList(List<PlantTypeListBean> plantTypeList) {
                this.plantTypeList = plantTypeList;
            }

            public List<QualityTypeListBean> getQualityTypeList() {
                return qualityTypeList;
            }

            public void setQualityTypeList(List<QualityTypeListBean> qualityTypeList) {
                this.qualityTypeList = qualityTypeList;
            }

            public List<QualityGradeListBean> getQualityGradeList() {
                return qualityGradeList;
            }

            public void setQualityGradeList(List<QualityGradeListBean> qualityGradeList) {
                this.qualityGradeList = qualityGradeList;
            }

            public static class ParamsListBean {
                /**
                 * name : "胸径"
                 * value : dbh
                 * required : true
                 * <p>
                 * "name": "高度",
                 * "value": "height",
                 * "required": false
                 * <p>
                 * "name": "冠幅",
                 * "value": "crown",
                 * "required": false
                 */

                private String value;
                private String name;
                private boolean required;


                @Override
                public String toString() {
                    return "ParamsListBean{" +
                            "value='" + value + '\'' +
                            ", name='" + name + '\'' +
                            ", required=" + required +
                            '}';
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public boolean isRequired() {
                    return required;
                }

                public void setRequired(boolean required) {
                    this.required = required;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class PlantTypeListBean {
                /**
                 * text : 地栽苗
                 * value : planted
                 */

                private String text;
                private String value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class QualityTypeListBean {
                /**
                 * text : 全冠苗
                 * value : quanguan
                 */

                private String text;
                private String value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class QualityGradeListBean {
                /**
                 * text : A货
                 * value : A
                 */

                private String text;
                private String value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

//        public static class PlantTypeListBeanX {
//            /**
//             * text : 地栽苗
//             * value : planted
//             */
//
//            private String text;
//            private String value;
//
//            public String getText() {
//                return text;
//            }
//
//            public void setText(String text) {
//                this.text = text;
//            }
//
//            public String getValue() {
//                return value;
//            }
//
//            public void setValue(String value) {
//                this.value = value;
//            }
//        }
    }
}
