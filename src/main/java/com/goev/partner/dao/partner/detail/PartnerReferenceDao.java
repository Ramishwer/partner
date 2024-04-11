package com.goev.partner.dao.partner.detail;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PartnerReferenceDao extends BaseDao {
    private Integer partnerId;
    private String name;
    private String phoneNumber;
    private String relation;
    private String email;
    private String address;
}