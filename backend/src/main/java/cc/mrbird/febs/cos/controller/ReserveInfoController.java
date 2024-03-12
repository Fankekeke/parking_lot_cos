package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ReserveInfo;
import cc.mrbird.febs.cos.service.IReserveInfoService;
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
@RequestMapping("/cos/reserve-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReserveInfoController {

    private final IReserveInfoService reserveInfoService;

    /**
     * 分页获取车位预约信息
     *
     * @param page        分页对象
     * @param reserveInfo 车位预约信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ReserveInfo> page, ReserveInfo reserveInfo) {
        return R.ok(reserveInfoService.selectReservePage(page, reserveInfo));
    }

    /**
     * 车位预约信息详情
     *
     * @param id 车位预约ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(reserveInfoService.getById(id));
    }

    /**
     * 车位预约信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(reserveInfoService.list());
    }

    /**
     * 新增车位预约信息
     *
     * @param reserveInfo 车位预约信息
     * @return 结果
     */
    @PostMapping
    public R save(ReserveInfo reserveInfo) {
        reserveInfo.setStartDate(DateUtil.formatDateTime(new Date()));
        return R.ok(reserveInfoService.save(reserveInfo));
    }

    /**
     * 修改车位预约信息
     *
     * @param reserveInfo 车位预约信息
     * @return 结果
     */
    @PutMapping
    public R edit(ReserveInfo reserveInfo) {
        return R.ok(reserveInfoService.updateById(reserveInfo));
    }

    /**
     * 删除车位预约信息
     *
     * @param ids ids
     * @return 车位预约信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(reserveInfoService.removeByIds(ids));
    }
}
