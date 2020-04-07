package com.example.notes.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

@TableName("AMOS_NS_AIRCRAFT_CATEGORY")
public class AmosNsAircraftCategory extends Model<AmosNsAircraftCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 航空器型别ID
     */
    @TableId("TYPE_ID")
    @JSONField(name = "typeId")
    private String typeId;

    /**
     * 航空器型别ID
     */
    /**
     * 航空器分类
     */
    @TableField("AIRCRAFT_CLASSIFY")
    @JSONField(name = "aircraftClassify")
    private String aircraftClassify;

    /**
     * 航空器型别
     */
    @TableField("AIRCRAFT_TYPE")
    @JSONField(name = "aircraftType")
    private String aircraftType;

    /**
     * 航空器制造者
     */
    @TableField("AIRCRAFT_MANUFACTURER")
    @JSONField(name = "aircraftManufacturer")
    private String aircraftManufacturer;

    /**
     * 航空器制造国
     */
    @TableField("AIRCRAFT_MANU_COUNTRY")
    @JSONField(name = "aircraftManuCountry")
    private String aircraftManuCountry;

    /**
     * TCVTC持有人
     */
    @TableField("TCVTC_HOLDER")
    @JSONField(name = "tcvtcHolder")
    private String tcvtcHolder;

    /**
     * 型号批准情况
     */
    @TableField("MODEL_APPROVAL")
    @JSONField(name = "modelApproval")
    private String modelApproval;

    /**
     * 生产批准情况
     */
    @TableField("PRODUC_APPROVAL")
    @JSONField(name = "producApproval")
    private String producApproval;

    /**
     * 航空器类型
     */
    @TableField("AIRCRAFT_SORT")
    @JSONField(name = "aircraftSort")
    private String aircraftSort;

    /**
     * 发动机数量
     */
    @TableField("ENGINE_NUM")
    @JSONField(name = "engineNum")
    private String engineNum;

    /**
     * 发动机是否随飞机取TC/VTC
     */
    @TableField("IS_ENGINE_TV")
    @JSONField(name = "isEngineTv")
    private String isEngineTv;

    /**
     * 螺旋桨是否随飞机取TC/VTC
     */
    @TableField("IS_PROPELL_TV")
    @JSONField(name = "isPropellTv")
    private String isPropellTv;

    /**
     * 起飞着陆类型
     */
    @TableField("LAND_TYPE")
    @JSONField(name = "landType")
    private String landType;

    /**
     * 最大合格审定座位数量
     */
    @TableField("SEAT_COUNT")
    @JSONField(name = "seatCount")
    private String seatCount;

    /**
     * 适航缴费金额
     */
    @TableField("SH_PAY_AMOUNT")
    @JSONField(name = "shPayAmount")
    private String shPayAmount;

    /**
     * 特许飞行缴费金额
     */
    @TableField("TX_PAY_AMOUNT")
    @JSONField(name = "txPayAmount")
    private String txPayAmount;

    /**
     * 航空器型号（年报用）
     */
    @TableField("AIRCRAFT_TYPE_Y")
    @JSONField(name = "aircraftTypeY")
    private String aircraftTypeY;

    /**
     * 航空器制造人名称（年报用）
     */
    @TableField("AIRCRAFT_MANUFACTURER_Y")
    @JSONField(name = "aircraftManufacturerY")
    private String aircraftManufacturerY;

    /**
     * 航空器类型（年报用）
     */
    @TableField("AIRCRAFT_SORT_Y")
    @JSONField(name = "aircraftSortY")
    private String aircraftSortY;

    /**
     * 是否大于9吨（年报用）
     */
    @TableField("GT9_Y")
    @JSONField(name = "gt9Y")
    private String gt9Y;

    /**
     * 是否停飞（年报用）
     */
    @TableField("IS_GROUND_Y")
    @JSONField(name = "isGroundY")
    private String isGroundY;

    /**
     * 动力来源能源类型（备用）
     */
    @TableField("ENERGY_TYPE")
    @JSONField(name = "energyType")
    private String energyType;

    /**
     * 动力装备数量是否适用
     */
    @TableField("IS_EQUIP_USE")
    @JSONField(name = "isEquipUse")
    private String isEquipUse;

    /**
     * 动力装备数量（备用）
     */
    @TableField("EQUIP_COUNT")
    @JSONField(name = "equipCount")
    private String equipCount;

    /**
     * 螺旋桨数量是否适用
     */
    @TableField("IS_PROPELL_USE")
    @JSONField(name = "isPropellUse")
    private String isPropellUse;

    /**
     * 螺旋桨数量（备用）
     */
    @TableField("PROPELL_COUNT")
    @JSONField(name = "propellCount")
    private String propellCount;

    /**
     * 发动机类型（年报用）
     */
    @TableField("ENGINE_SORT_Y")
    @JSONField(name = "engineSortY")
    private String engineSortY;

    /**
     * 备注
     */
    @TableField("REMARKS")
    @JSONField(name = "remarks")
    private String remarks;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getAircraftClassify() {
        return aircraftClassify;
    }

    public void setAircraftClassify(String aircraftClassify) {
        this.aircraftClassify = aircraftClassify;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getAircraftManufacturer() {
        return aircraftManufacturer;
    }

    public void setAircraftManufacturer(String aircraftManufacturer) {
        this.aircraftManufacturer = aircraftManufacturer;
    }

    public String getAircraftManuCountry() {
        return aircraftManuCountry;
    }

    public void setAircraftManuCountry(String aircraftManuCountry) {
        this.aircraftManuCountry = aircraftManuCountry;
    }

    public String getTcvtcHolder() {
        return tcvtcHolder;
    }

    public void setTcvtcHolder(String tcvtcHolder) {
        this.tcvtcHolder = tcvtcHolder;
    }

    public String getModelApproval() {
        return modelApproval;
    }

    public void setModelApproval(String modelApproval) {
        this.modelApproval = modelApproval;
    }

    public String getProducApproval() {
        return producApproval;
    }

    public void setProducApproval(String producApproval) {
        this.producApproval = producApproval;
    }

    public String getAircraftSort() {
        return aircraftSort;
    }

    public void setAircraftSort(String aircraftSort) {
        this.aircraftSort = aircraftSort;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public String getIsEngineTv() {
        return isEngineTv;
    }

    public void setIsEngineTv(String isEngineTv) {
        this.isEngineTv = isEngineTv;
    }

    public String getIsPropellTv() {
        return isPropellTv;
    }

    public void setIsPropellTv(String isPropellTv) {
        this.isPropellTv = isPropellTv;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getShPayAmount() {
        return shPayAmount;
    }

    public void setShPayAmount(String shPayAmount) {
        this.shPayAmount = shPayAmount;
    }

    public String getTxPayAmount() {
        return txPayAmount;
    }

    public void setTxPayAmount(String txPayAmount) {
        this.txPayAmount = txPayAmount;
    }

    public String getAircraftTypeY() {
        return aircraftTypeY;
    }

    public void setAircraftTypeY(String aircraftTypeY) {
        this.aircraftTypeY = aircraftTypeY;
    }

    public String getAircraftManufacturerY() {
        return aircraftManufacturerY;
    }

    public void setAircraftManufacturerY(String aircraftManufacturerY) {
        this.aircraftManufacturerY = aircraftManufacturerY;
    }

    public String getAircraftSortY() {
        return aircraftSortY;
    }

    public void setAircraftSortY(String aircraftSortY) {
        this.aircraftSortY = aircraftSortY;
    }

    public String getGt9Y() {
        return gt9Y;
    }

    public void setGt9Y(String gt9Y) {
        this.gt9Y = gt9Y;
    }

    public String getIsGroundY() {
        return isGroundY;
    }

    public void setIsGroundY(String isGroundY) {
        this.isGroundY = isGroundY;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public String getIsEquipUse() {
        return isEquipUse;
    }

    public void setIsEquipUse(String isEquipUse) {
        this.isEquipUse = isEquipUse;
    }

    public String getEquipCount() {
        return equipCount;
    }

    public void setEquipCount(String equipCount) {
        this.equipCount = equipCount;
    }

    public String getIsPropellUse() {
        return isPropellUse;
    }

    public void setIsPropellUse(String isPropellUse) {
        this.isPropellUse = isPropellUse;
    }

    public String getPropellCount() {
        return propellCount;
    }

    public void setPropellCount(String propellCount) {
        this.propellCount = propellCount;
    }

    public String getEngineSortY() {
        return engineSortY;
    }

    public void setEngineSortY(String engineSortY) {
        this.engineSortY = engineSortY;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    protected Serializable pkVal() {
        return this.typeId;
    }

    @Override
    public String toString() {
        return "AmosNsAircraftCategory{" + "typeId=" + typeId + ", aircraftClassify=" + aircraftClassify + ", aircraftType=" + aircraftType + ", aircraftManufacturer=" + aircraftManufacturer + ", aircraftManuCountry=" + aircraftManuCountry + ", tcvtcHolder=" + tcvtcHolder + ", modelApproval=" + modelApproval + ", producApproval=" + producApproval + ", aircraftSort=" + aircraftSort + ", engineNum=" + engineNum + ", isEngineTv=" + isEngineTv + ", isPropellTv=" + isPropellTv + ", landType=" + landType + ", seatCount=" + seatCount + ", shPayAmount=" + shPayAmount + ", txPayAmount=" + txPayAmount + ", aircraftTypeY=" + aircraftTypeY + ", aircraftManufacturerY=" + aircraftManufacturerY + ", aircraftSortY=" + aircraftSortY + ", gt9Y=" + gt9Y + ", isGroundY=" + isGroundY + ", energyType=" + energyType + ", isEquipUse=" + isEquipUse + ", equipCount=" + equipCount + ", isPropellUse=" + isPropellUse + ", propellCount=" + propellCount + ", engineSortY=" + engineSortY + ", remarks=" + remarks + "}";
    }
}
