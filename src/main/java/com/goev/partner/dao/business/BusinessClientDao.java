package com.goev.partner.dao.business;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BusinessClientDao extends BaseDao {
    private String name;
    private String description;
}
