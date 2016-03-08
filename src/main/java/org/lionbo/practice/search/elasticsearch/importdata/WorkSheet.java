package org.lionbo.practice.search.elasticsearch.importdata;

public class WorkSheet {

    private String worksheetId;
    private String worksheetStatus;
    private String firstCategory;
    private String secondCategroy;
    private String thirdCategroy;
    private String callPhone;
    private String registerPhone;
    private String handleDepart;
    private String busType;
    private String province;
    private String priority;
    private String workSheetType;
    private String occupy;
    private String client;
    private String channel;
    private String orderid;
    private String creator;
    private String content;
    private String createTime;
    private String updateTime;
    private String takeTime;
    private String closeTime;
    private String complainId;
    private String complainedName;

    public WorkSheet() {

    }

    public WorkSheet(String worksheetId, String worksheetStatus, String firstCategory, String secondCategroy,
            String thirdCategroy, String callPhone, String registerPhone, String handleDepart, String busType,
            String province, String priority, String workSheetType, String occupy, String client, String channel,
            String orderid, String creator, String content, String createTime, String updateTime, String takeTime,
            String closeTime, String complainId, String complainedName) {
        this.worksheetId = worksheetId;
        this.worksheetStatus = worksheetStatus;
        this.firstCategory = firstCategory;
        this.secondCategroy = secondCategroy;
        this.thirdCategroy = thirdCategroy;
        this.callPhone = callPhone;
        this.registerPhone = registerPhone;
        this.handleDepart = handleDepart;
        this.busType = busType;
        this.province = province;
        this.priority = priority;
        this.workSheetType = workSheetType;
        this.occupy = occupy;
        this.client = client;
        this.channel = channel;
        this.orderid = orderid;
        this.creator = creator;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.takeTime = takeTime;
        this.closeTime = closeTime;
        this.complainId = complainId;
        this.complainedName = complainedName;
    }

    public String getWorksheetId() {
        return worksheetId;
    }

    public void setWorksheetId(String worksheetId) {
        this.worksheetId = worksheetId;
    }

    public String getWorksheetStatus() {
        return worksheetStatus;
    }

    public void setWorksheetStatus(String worksheetStatus) {
        this.worksheetStatus = worksheetStatus;
    }

    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory;
    }

    public String getSecondCategroy() {
        return secondCategroy;
    }

    public void setSecondCategroy(String secondCategroy) {
        this.secondCategroy = secondCategroy;
    }

    public String getThirdCategroy() {
        return thirdCategroy;
    }

    public void setThirdCategroy(String thirdCategroy) {
        this.thirdCategroy = thirdCategroy;
    }

    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getHandleDepart() {
        return handleDepart;
    }

    public void setHandleDepart(String handleDepart) {
        this.handleDepart = handleDepart;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getWorkSheetType() {
        return workSheetType;
    }

    public void setWorkSheetType(String workSheetType) {
        this.workSheetType = workSheetType;
    }

    public String getOccupy() {
        return occupy;
    }

    public void setOccupy(String occupy) {
        this.occupy = occupy;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    public String getComplainedName() {
        return complainedName;
    }

    public void setComplainedName(String complainedName) {
        this.complainedName = complainedName;
    }

    @Override
    public String toString() {
        return "WorkSheet [worksheetId=" + worksheetId + ", worksheetStatus=" + worksheetStatus + ", firstCategory="
                + firstCategory + ", secondCategroy=" + secondCategroy + ", thirdCategroy=" + thirdCategroy
                + ", callPhone=" + callPhone + ", registerPhone=" + registerPhone + ", handleDepart=" + handleDepart
                + ", busType=" + busType + ", province=" + province + ", priority=" + priority + ", workSheetType="
                + workSheetType + ", occupy=" + occupy + ", client=" + client + ", channel=" + channel + ", orderid="
                + orderid + ", creator=" + creator + ", content=" + content + ", createTime=" + createTime
                + ", updateTime=" + updateTime + ", takeTime=" + takeTime + ", closeTime=" + closeTime + ", complainId="
                + complainId + ", complainedName=" + complainedName + "]";
    }

}
