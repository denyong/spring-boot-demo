package com.duqi.entity;

import lombok.Data;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @TableName email
 */
@Data
public class Email {
    /**
     *
     */
    private String brand;

    /**
     *
     */
    private String seriescode;

    /**
     *
     */
    private String seriesnameen;

    /**
     *
     */
    private String seriesnamezh;

    /**
     *
     */
    private String displayorder;

    /**
     *
     */
    private String segmentag;

    /**
     *
     */
    private String bodyag;

    /**
     *
     */
    private String wheelbase;

    /**
     *
     */
    private String releaseid;

    /**
     *
     */
    private String type;

    /**
     *
     */
    private String targetdate;

    /**
     *
     */
    private String nextsopdate;

    /**
     *
     */
    private String targetdatedetail;

    /**
     *
     */
    private String nextsopdatedetail;

    /**
     *
     */
    private String nameprefix;

    /**
     *
     */
    private String namezh;

    /**
     *
     */
    private String mpaeseriesname;

    /**
     *
     */
    private String propublishtime;

    /**
     *
     */
    private String marketpublishtime;

    /**
     *
     */
    private String prosharetime;

    /**
     *
     */
    private String updatetime;

    /**
     *
     */
    private String variantid;

    /**
     *
     */
    private String enginename;

    /**
     *
     */
    private String variant;

    /**
     *
     */
    private String hybridversion;

    /**
     *
     */
    private String displayorder1;

    /**
     *
     */
    private String envariantname;

    /**
     *
     */
    private String cnvariantname;

    /**
     *
     */
    private String modelcode;

    /**
     *
     */
    private String packagecode;

    /**
     *
     */
    private String linepackagecode;

    /**
     *
     */
    private String msrp;

    /**
     *
     */
    private String aditionaloptions;

    /**
     *
     */
    private String salesmsrp;

    /**
     *
     */
    private String sosdate;

    /**
     *
     */
    private String wheelbase1;

    /**
     *
     */
    private String transmission;

    /**
     *
     */
    private String drivetype;

    /**
     *
     */
    private String thaiproduct;

    /**
     *
     */
    private String specialedition;

    /**
     *
     */
    private String energytype;

    /**
     *
     */
    private String nev;

    /**
     *
     */
    private String cylinder;

    /**
     *
     */
    private String drivemode;

    /**
     *
     */
    private String assemblyland;

    /**
     *
     */
    private String assemblytype;

    /**
     *
     */
    private String paklevel;

    /**
     *
     */
    private String displacement;

    /**
     *
     */
    private String performancetype;

    private static final long serialVersionUID = 1L;
    private Long id;

    @Id
    public Long getId() {
        return id;
    }
}