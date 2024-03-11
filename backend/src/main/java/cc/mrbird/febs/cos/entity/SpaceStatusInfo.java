package cc.mrbird.febs.cos.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 停车位状态
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpaceStatusInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车位ID
     */
    private Integer spaceId;

    /**
     * 状态（-1.预约中 0.空闲 1.停车中）
     */
    private String status;


}
