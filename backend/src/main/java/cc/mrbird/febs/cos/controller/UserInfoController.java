package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.service.CacheService;
import cc.mrbird.febs.common.service.RedisService;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.IMailService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import cc.mrbird.febs.system.domain.User;
import cc.mrbird.febs.system.service.UserService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/user-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoController {

    private final IUserInfoService userInfoService;

    private final UserService userService;

    private final CacheService cacheService;

    private final TemplateEngine templateEngine;

    private final IMailService iMailService;

    private final RedisService redisService;

    @GetMapping("/recover/password")
    public R recoverPassword(String email) throws Exception {
        // 判断邮箱是否存在
        int count = userInfoService.count(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getEmail, email));
        if (count > 0) {
            // 绘制随机字符
            String randomString = "";
            for (int i = 1; i <= 50; i++) {
                randomString = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
            }
            //获取用户信息
            UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getEmail, email));
            if (userInfo == null || userInfo.getUserId() == null) {
                throw new FebsException("邮箱用户不存在");
            }
            User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUserId, userInfo.getUserId()));
            userService.resetPasswordFix(user.getUsername(), randomString);

            Context context = new Context();
            context.setVariable("today", DateUtil.formatDate(new Date()));
            context.setVariable("custom", "您好，您找回的邮箱密码已重置，新的密码为 " + randomString + "，请重新进行登录");
            String emailContent = templateEngine.process("registerEmail", context);
            iMailService.sendHtmlMail(email, "橘子-账户验证", emailContent);
        } else {
            throw new FebsException("邮箱不存在");
        }
        return R.ok(true);
    }

    /**
     * 验证码验证
     *
     * @param validateCode 验证码
     * @return 结果
     */
    @SneakyThrows
    @GetMapping("/verification/check")
    public R verificationCheck(String validateCode, String email) {
        //1:获取redis里面的验证码信息
        String code = redisService.get(email);
        //2:判断验证码是否正确
        if (!StringUtils.isEmpty(validateCode)) {
            validateCode = validateCode.toLowerCase();
            code = code.toLowerCase();
            if (validateCode.equals(code)) {
                // 删除key
                redisService.del(email);
                return R.ok(true);
            }
        }
        return R.ok(false);
    }

    /**
     * 发送注册邮件
     *
     * @param email 邮箱地址
     * @return 结果
     */
    @SneakyThrows
    @GetMapping("/register/email")
    public R sendRegisterEmail(String email) {
        // 判断邮箱是否重复
        int count = userInfoService.count(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getEmail, email));
        if (count > 0) {
            return R.ok(false);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= 50; i++) {
            randomString = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        }

        Context context = new Context();
        context.setVariable("today", DateUtil.formatDate(new Date()));
        context.setVariable("custom", "您好，欢迎您注册，您的验证码为 " + randomString + "，请进行验证");
        String emailContent = templateEngine.process("registerEmail", context);
        iMailService.sendHtmlMail(email, "橘子-账户验证", emailContent);
        // 将随机生成的验证码放入Redis中
        redisService.set(email, randomString);
        return R.ok(true);
    }

    /**
     * 分页获取用户信息
     *
     * @param page     分页对象
     * @param userInfo 用户信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<UserInfo> page, UserInfo userInfo) {
        return R.ok(userInfoService.selectUserPage(page, userInfo));
    }

    /**
     * 用户信息详情
     *
     * @param id 用户ID
     * @return 结果
     */
    @GetMapping("/detail/{userId}")
    public R userDetail(@PathVariable("userId") Integer id) {
        return R.ok(userInfoService.userDetail(id));
    }

    /**
     * 用户信息详情
     *
     * @param id 用户ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(userInfoService.getById(id));
    }

    /**
     * 用户信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(userInfoService.list());
    }

    /**
     * 新增用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    @PostMapping
    public R save(UserInfo userInfo) {
        return R.ok(userInfoService.save(userInfo));
    }

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    @PutMapping
    public R edit(UserInfo userInfo) {
        return R.ok(userInfoService.updateById(userInfo));
    }

    /**
     * 删除用户信息
     *
     * @param ids ids
     * @return 用户信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(userInfoService.removeByIds(ids));
    }
}
