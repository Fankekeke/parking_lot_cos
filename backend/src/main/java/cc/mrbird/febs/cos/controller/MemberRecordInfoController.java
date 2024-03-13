package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.*;
import cc.mrbird.febs.cos.service.*;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/member-record-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberRecordInfoController {

    private final IMemberRecordInfoService memberRecordInfoService;

    private final IUserInfoService userInfoService;

    private final IMemberInfoService memberInfoService;

    private final IParkOrderInfoService parkOrderInfoService;

    private final IRuleInfoService ruleInfoService;

    private final ISpaceInfoService spaceInfoService;

    /**
     * 分页获取会员信息
     *
     * @param page             分页对象
     * @param memberRecordInfo 会员信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<MemberRecordInfo> page, MemberRecordInfo memberRecordInfo) {
        return R.ok(memberRecordInfoService.selectMemberRecordPage(page, memberRecordInfo));
    }

    /**
     * 订单支付回调
     *
     * @param orderCode 订单编号
     * @param userId    用户ID
     * @return 结果
     */
    @GetMapping("/editOrder")
    public R editOrder(String orderCode, Integer userId) {
        // 获取用户信息
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, userId));

        if (orderCode.contains("MEM-")) {
            // 会员订单
            MemberRecordInfo memberRecordInfo = memberRecordInfoService.getOne(Wrappers.<MemberRecordInfo>lambdaQuery().eq(MemberRecordInfo::getCode, orderCode));
            memberRecordInfo.setStatus("1");
            memberRecordInfo.setPayDate(DateUtil.formatDateTime(new Date()));
            memberRecordInfoService.updateById(memberRecordInfo);

            // 会员信息
            RuleInfo ruleInfo = ruleInfoService.getById(memberRecordInfo.getMemberId());

            // 添加会员信息
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setUserId(userInfo.getId());
            memberInfo.setMemberLevel(ruleInfo.getId().toString());
            memberInfo.setPayDate(DateUtil.formatDateTime(new Date()));
            memberInfo.setStartDate(DateUtil.formatDateTime(new Date()));
            memberInfo.setEndDate(DateUtil.formatDateTime(DateUtil.offsetDay(new Date(), ruleInfo.getDays())));
            memberInfo.setPrice(memberRecordInfo.getPrice());
            memberInfoService.save(memberInfo);

        } else {
            // 停车订单
            ParkOrderInfo parkOrderInfo = parkOrderInfoService.getOne(Wrappers.<ParkOrderInfo>lambdaQuery().eq(ParkOrderInfo::getCode, orderCode));

            // 车位信息
            SpaceInfo spaceInfo = spaceInfoService.getById(parkOrderInfo.getSpaceId());
            parkOrderInfo.setEndDate(DateUtil.formatDateTime(new Date()));
            parkOrderInfo.setPayDate(DateUtil.formatDateTime(new Date()));
            // 停车总时常
            long totalTime = DateUtil.between(DateUtil.parseDate(parkOrderInfo.getStartDate()), DateUtil.parseDate(parkOrderInfo.getEndDate()), DateUnit.MINUTE);
            parkOrderInfo.setTotalTime(BigDecimal.valueOf(totalTime));
            // 总价格
        }
        return R.ok(true);
    }

    /**
     * 会员信息详情
     *
     * @param id 会员ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(memberRecordInfoService.getById(id));
    }

    /**
     * 会员信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(memberRecordInfoService.list());
    }

    /**
     * 新增会员信息
     *
     * @param memberRecordInfo 会员信息
     * @return 结果
     */
    @PostMapping
    public R save(MemberRecordInfo memberRecordInfo) {
        memberRecordInfo.setStatus("0");
        memberRecordInfo.setCode("OR-" + System.currentTimeMillis());
        return R.ok(memberRecordInfoService.save(memberRecordInfo));
    }

    /**
     * 修改会员信息
     *
     * @param memberRecordInfo 会员信息
     * @return 结果
     */
    @PutMapping
    public R edit(MemberRecordInfo memberRecordInfo) {
        return R.ok(memberRecordInfoService.updateById(memberRecordInfo));
    }

    /**
     * 删除会员信息
     *
     * @param ids ids
     * @return 会员信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(memberRecordInfoService.removeByIds(ids));
    }
}