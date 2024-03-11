package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 会员管理
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 会员等级（1.月会员 2.年会员）
     */
    private String memberLevel;

    /**
     * 会员开始时间
     */
    private LocalDateTime startDate;

    /**
     * 会员结束时间
     */
    private LocalDateTime endDate;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 支付时间
     */
    private LocalDateTime payDate;


}
