package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.MemberInfo;
import cc.mrbird.febs.cos.service.IMemberInfoService;
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
@RequestMapping("/cos/member-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberInfoController {

    private final IMemberInfoService memberInfoService;

    /**
     * 分页获取会员信息
     *
     * @param page         分页对象
     * @param memberInfo 会员信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<MemberInfo> page, MemberInfo memberInfo) {
        return R.ok();
    }

    /**
     * 会员信息详情
     *
     * @param id 会员ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(memberInfoService.getById(id));
    }

    /**
     * 会员信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(memberInfoService.list());
    }

    /**
     * 新增会员信息
     *
     * @param memberInfo 会员信息
     * @return 结果
     */
    @PostMapping
    public R save(MemberInfo memberInfo) {
        return R.ok(memberInfoService.save(memberInfo));
    }

    /**
     * 修改会员信息
     *
     * @param memberInfo 会员信息
     * @return 结果
     */
    @PutMapping
    public R edit(MemberInfo memberInfo) {
        return R.ok(memberInfoService.updateById(memberInfo));
    }

    /**
     * 删除会员信息
     *
     * @param ids ids
     * @return 会员信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(memberInfoService.removeByIds(ids));
    }
}