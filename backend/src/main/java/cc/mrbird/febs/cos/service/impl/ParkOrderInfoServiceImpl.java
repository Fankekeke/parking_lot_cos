package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ParkOrderInfo;
import cc.mrbird.febs.cos.dao.ParkOrderInfoMapper;
import cc.mrbird.febs.cos.service.IParkOrderInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class ParkOrderInfoServiceImpl extends ServiceImpl<ParkOrderInfoMapper, ParkOrderInfo> implements IParkOrderInfoService {

    /**
     * 分页获取订单信息
     *
     * @param page          分页对象
     * @param parkOrderInfo 订单信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectOrderPage(Page<ParkOrderInfo> page, ParkOrderInfo parkOrderInfo) {
        return baseMapper.selectOrderPage(page, parkOrderInfo);
    }
}
