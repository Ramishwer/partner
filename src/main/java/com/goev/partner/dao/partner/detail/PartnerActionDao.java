package com.goev.partner.dao.partner.detail;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PartnerActionDao extends BaseDao {
    private String actionDetails;
    private String action;
    private Integer partnerId;
    private String status;
}