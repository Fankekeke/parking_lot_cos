package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ParkOrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface ParkOrderInfoMapper extends BaseMapper<ParkOrderInfo> {

    /**
     * 分页获取订单信息
     *
     * @param page          分页对象
     * @param parkOrderInfo 订单信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectOrderPage(Page<ParkOrderInfo> page, @Param("parkOrderInfo") ParkOrderInfo parkOrderInfo);
}
