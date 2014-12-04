package com.huanwuji.lps.domain;

import com.huanwuji.lps.utils.search.Search;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Description
 * Date 2014/10/8
 *
 * @author huanwuji
 */
@Search(all = true)
@SolrDocument(solrCoreName = "customer")
@Entity(name = "customer")
public class Customer {
    //车牌号码
    @Id
    @Column(length = 50)
    @Indexed
    private String num;
    //车辆品牌
    @Column(length = 100)
    @Indexed
    private String brand;
    //车辆型号
    @Column(length = 100)
    @Indexed
    private String type;
    //车架号
    @Column(name = "carriage_num", length = 100)
    @Indexed
    private String carriageNum;
    //发动机号
    @Column(name = "engine_num", length = 100)
    @Indexed
    private String engineNum;
    //车身颜色
    @Column(length = 50)
    @Indexed
    private String color;
    //使用性质
    @Column(name = "use_type", length = 100)
    @Indexed
    private String useType;
    //证件号码
    @Column(name = "credentials_num", length = 100)
    @Indexed
    private String credentialsNum;
    //证件名称
    @Column(name = "credentials_name", length = 50)
    @Indexed
    private String credentialsName;
    //车主姓名
    @Column(name = "customer_name", length = 20)
    @Indexed
    private String customerName;
    //登记住所
    @Column(length = 255)
    @Indexed
    private String address;
    //联系电话
    @Column(length = 20)
    @Indexed
    private String phone;
    //手机号码
    @Column(length = 20)
    @Indexed
    private String mobile;
    //登记日期
    @Column(name = "register_time")
    @Indexed
    private Date registerTime;
    //检验有效期止
    @Column(name = "valid_time")
    @Indexed
    private Date validTime;
    //发证日期
    @Column(name = "effect_time")
    @Indexed
    private Date effectTime;
    //保险到期日期
    @Column(name = "expire_time")
    @Indexed
    private Date expireTime;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCarriageNum() {
        return carriageNum;
    }

    public void setCarriageNum(String carriageNum) {
        this.carriageNum = carriageNum;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public String getCredentialsName() {
        return credentialsName;
    }

    public void setCredentialsName(String credentialsName) {
        this.credentialsName = credentialsName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
