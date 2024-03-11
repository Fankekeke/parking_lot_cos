package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ParkOrderInfo;
import cc.mrbird.febs.cos.service.IParkOrderInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/park-order-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkOrderInfoController {

    private final IParkOrderInfoService parkOrderInfoService;

    /**
     * 分页获取订单信息
     *
     * @param page          分页对象
     * @param parkOrderInfo 订单信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ParkOrderInfo> page, ParkOrderInfo parkOrderInfo) {
        return R.ok();
    }

    /**
     * 订单信息详情
     *
     * @param id 订单ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(parkOrderInfoService.getById(id));
    }

    /**
     * 订单信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(parkOrderInfoService.list());
    }

    /**
     * 新增订单信息
     *
     * @param parkOrderInfo 订单信息
     * @return 结果
     */
    @PostMapping
    public R save(ParkOrderInfo parkOrderInfo) {
        parkOrderInfo.setStartDate(DateUtil.formatDateTime(new Date()));
        return R.ok(parkOrderInfoService.save(parkOrderInfo));
    }

    /**
     * 修改订单信息
     *
     * @param parkOrderInfo 订单信息
     * @return 结果
     */
    @PutMapping
    public R edit(ParkOrderInfo parkOrderInfo) {
        return R.ok(parkOrderInfoService.updateById(parkOrderInfo));
    }

    /**
     * 删除订单信息
     *
     * @param ids ids
     * @return 订单信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(parkOrderInfoService.removeByIds(ids));
    }
}