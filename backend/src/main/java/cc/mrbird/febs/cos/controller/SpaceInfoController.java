package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SpaceInfo;
import cc.mrbird.febs.cos.service.ISpaceInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/space-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpaceInfoController {

    private final ISpaceInfoService spaceInfoService;

    /**
     * 分页获取车位信息
     *
     * @param page      分页对象
     * @param spaceInfo 车位信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<SpaceInfo> page, SpaceInfo spaceInfo) {
        return R.ok();
    }

    /**
     * 车位信息详情
     *
     * @param id 车位ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(spaceInfoService.getById(id));
    }

    /**
     * 车位信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(spaceInfoService.list());
    }

    /**
     * 新增车位信息
     *
     * @param spaceInfo 车位信息
     * @return 结果
     */
    @PostMapping
    public R save(SpaceInfo spaceInfo) {
        return R.ok(spaceInfoService.save(spaceInfo));
    }

    /**
     * 修改车位信息
     *
     * @param spaceInfo 车位信息
     * @return 结果
     */
    @PutMapping
    public R edit(SpaceInfo spaceInfo) {
        return R.ok(spaceInfoService.updateById(spaceInfo));
    }

    /**
     * 删除车位信息
     *
     * @param ids ids
     * @return 车位信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(spaceInfoService.removeByIds(ids));
    }
}
