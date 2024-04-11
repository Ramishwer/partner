package com.goev.partner.dao.booking;

import com.goev.lib.dao.BaseDao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookingDao extends BaseDao {
    private Integer bookingTypeId;
    private String bookingTypeDetails;
    private String status;
    private String startLocationDetails;
    private String endLocationDetails;
    private Integer bookingDetailsId;
    private String partnerDetails;
    private String vehicleDetails;
    private String customerDetails;

}
