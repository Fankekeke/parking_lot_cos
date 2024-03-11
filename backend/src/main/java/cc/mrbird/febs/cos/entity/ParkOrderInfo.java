package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 停车出入库管理
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ParkOrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 停车位ID
     */
    private Integer spaceId;

    /**
     * 车辆ID
     */
    private Integer vehicleId;

    /**
     * 开始停车时间
     */
    private String startDate;

    /**
     * 停车结束时间
     */
    private String endDate;

    /**
     * 停车时常（分钟）
     */
    private BigDecimal totalTime;

    /**
     * 停车位单价
     */
    private BigDecimal price;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    /**
     * 支付时间
     */
    private String payDate;


}
