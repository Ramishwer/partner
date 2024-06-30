package com.goev.partner.dao.partner.detail;

import com.goev.lib.dao.BaseDao;
import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PartnerDetailDao extends BaseDao {
    private String firstName;
    private String lastName;
    private String email;
    private Boolean isVerified;
    private String profileUrl;
    private String homeLocationName;
    private Integer partnerId;
    private String fathersName;
    private String aadhaarCardNumber;
    private String dlNumber;
    private String localAddress;
    private String permanentAddress;
    private DateTime joiningDate;
    private DateTime dlExpiryDate;
    private Integer homeLocationId;
    private DateTime interviewDate;
    private String sourceOfLeadType;
    private String sourceOfLead;
    private Integer shiftId;
    private String drivingTestStatus;
    private String selectionStatus;
    private String remark;
    private DateTime onboardingDate;
    private DateTime deboardingDate;
}
