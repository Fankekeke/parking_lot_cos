package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SpaceStatusInfo;
import cc.mrbird.febs.cos.service.ISpaceStatusInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/space-status-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpaceStatusInfoController {

    private final ISpaceStatusInfoService spaceStatusInfoService;

    /**
     * 分页获取车位状态信息
     *
     * @param page            分页对象
     * @param spaceStatusInfo 车位状态信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<SpaceStatusInfo> page, SpaceStatusInfo spaceStatusInfo) {
        return R.ok();
    }

    /**
     * 车位状态信息详情
     *
     * @param id 车位状态ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(spaceStatusInfoService.getById(id));
    }

    /**
     * 车位状态信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(spaceStatusInfoService.list());
    }

    /**
     * 新增车位状态信息
     *
     * @param spaceStatusInfo 车位状态信息
     * @return 结果
     */
    @PostMapping
    public R save(SpaceStatusInfo spaceStatusInfo) {
        return R.ok(spaceStatusInfoService.save(spaceStatusInfo));
    }

    /**
     * 修改车位状态信息
     *
     * @param spaceStatusInfo 车位状态信息
     * @return 结果
     */
    @PutMapping
    public R edit(SpaceStatusInfo spaceStatusInfo) {
        return R.ok(spaceStatusInfoService.updateById(spaceStatusInfo));
    }

    /**
     * 删除车位状态信息
     *
     * @param ids ids
     * @return 车位状态信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(spaceStatusInfoService.removeByIds(ids));
    }
}
