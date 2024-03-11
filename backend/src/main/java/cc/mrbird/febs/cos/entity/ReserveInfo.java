package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 车位预约信息
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReserveInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车位ID
     */
    private Integer spaceId;

    /**
     * 车辆ID
     */
    private Integer vehicleId;

    /**
     * 开始预约时间
     */
    private LocalDateTime startDate;

    /**
     * 预约结束时间
     */
    private LocalDateTime endDate;

    /**
     * 状态（0.结束 1.预约中）
     */
    private String status;


}
