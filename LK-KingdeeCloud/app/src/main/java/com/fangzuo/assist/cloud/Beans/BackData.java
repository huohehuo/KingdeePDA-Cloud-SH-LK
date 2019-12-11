package com.fangzuo.assist.cloud.Beans;

import java.util.List;

public class BackData {


    @Override
    public String toString() {
        return "BackData{" +
                "Result=" + Result +
                '}';
    }

    /**
     * Result : {"ResponseStatus":{"ErrorCode":"","IsSuccess":"false","Errors":[{"FieldName":"","Message":"","DIndex":0}],"SuccessEntitys":[{"Id":"","Number":"","DIndex":0}],"SuccessMessages":[{"FieldName":"","Message":"","DIndex":0}]},"Id":"","NeedReturnData":[{}]}
     */

    private ResultBean Result;

    public ResultBean getResult() {
        return Result;
    }

    public void setResult(ResultBean Result) {
        this.Result = Result;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "ResponseStatus=" + ResponseStatus +
                    ", Id='" + Id + '\'' +
                    ", NeedReturnData=" + NeedReturnData +
                    '}';
        }

        /**
         * ResponseStatus : {"ErrorCode":"","IsSuccess":"false","Errors":[{"FieldName":"","Message":"","DIndex":0}],"SuccessEntitys":[{"Id":"","Number":"","DIndex":0}],"SuccessMessages":[{"FieldName":"","Message":"","DIndex":0}]}
         * Id :
         * NeedReturnData : [{}]
         */

        private ResponseStatusBean ResponseStatus;
        private String Id;
        private List<NeedReturnDataBean> NeedReturnData;

        public ResponseStatusBean getResponseStatus() {
            return ResponseStatus;
        }

        public void setResponseStatus(ResponseStatusBean ResponseStatus) {
            this.ResponseStatus = ResponseStatus;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public List<NeedReturnDataBean> getNeedReturnData() {
            return NeedReturnData;
        }

        public void setNeedReturnData(List<NeedReturnDataBean> NeedReturnData) {
            this.NeedReturnData = NeedReturnData;
        }

        public static class ResponseStatusBean {
            /**
             * ErrorCode :
             * IsSuccess : false
             * Errors : [{"FieldName":"","Message":"","DIndex":0}]
             * SuccessEntitys : [{"Id":"","Number":"","DIndex":0}]
             * SuccessMessages : [{"FieldName":"","Message":"","DIndex":0}]
             */

            private String ErrorCode;
            private boolean IsSuccess;
            private List<ErrorsBean> Errors;
            private List<SuccessEntitysBean> SuccessEntitys;
            private List<SuccessMessagesBean> SuccessMessages;

            public String getErrorCode() {
                return ErrorCode;
            }

            public void setErrorCode(String ErrorCode) {
                this.ErrorCode = ErrorCode;
            }

            public boolean getIsSuccess() {
                return IsSuccess;
            }

            public void setIsSuccess(boolean IsSuccess) {
                this.IsSuccess = IsSuccess;
            }

            public List<ErrorsBean> getErrors() {
                return Errors;
            }

            public void setErrors(List<ErrorsBean> Errors) {
                this.Errors = Errors;
            }

            public List<SuccessEntitysBean> getSuccessEntitys() {
                return SuccessEntitys;
            }

            public void setSuccessEntitys(List<SuccessEntitysBean> SuccessEntitys) {
                this.SuccessEntitys = SuccessEntitys;
            }

            public List<SuccessMessagesBean> getSuccessMessages() {
                return SuccessMessages;
            }

            public void setSuccessMessages(List<SuccessMessagesBean> SuccessMessages) {
                this.SuccessMessages = SuccessMessages;
            }

            public static class ErrorsBean {
                /**
                 * FieldName :
                 * Message :
                 * DIndex : 0
                 */

                private String FieldName;
                private String Message;
                private int DIndex;

                public String getFieldName() {
                    return FieldName;
                }

                public void setFieldName(String FieldName) {
                    this.FieldName = FieldName;
                }

                public String getMessage() {
                    return Message;
                }

                public void setMessage(String Message) {
                    this.Message = Message;
                }

                public int getDIndex() {
                    return DIndex;
                }

                public void setDIndex(int DIndex) {
                    this.DIndex = DIndex;
                }
            }

            public static class SuccessEntitysBean {
                /**
                 * Id :
                 * Number :
                 * DIndex : 0
                 */

                private String Id;
                private String Number;
                private int DIndex;

                public String getId() {
                    return Id;
                }

                public void setId(String Id) {
                    this.Id = Id;
                }

                public String getNumber() {
                    return Number;
                }

                public void setNumber(String Number) {
                    this.Number = Number;
                }

                public int getDIndex() {
                    return DIndex;
                }

                public void setDIndex(int DIndex) {
                    this.DIndex = DIndex;
                }
            }

            public static class SuccessMessagesBean {
                /**
                 * FieldName :
                 * Message :
                 * DIndex : 0
                 */

                private String FieldName;
                private String Message;
                private int DIndex;

                public String getFieldName() {
                    return FieldName;
                }

                public void setFieldName(String FieldName) {
                    this.FieldName = FieldName;
                }

                public String getMessage() {
                    return Message;
                }

                public void setMessage(String Message) {
                    this.Message = Message;
                }

                public int getDIndex() {
                    return DIndex;
                }

                public void setDIndex(int DIndex) {
                    this.DIndex = DIndex;
                }
            }
        }

        public static class NeedReturnDataBean {
        }
    }
}
