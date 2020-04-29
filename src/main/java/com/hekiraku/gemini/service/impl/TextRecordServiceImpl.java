package com.hekiraku.gemini.service.impl;

import com.github.pagehelper.PageInfo;
import com.hekiraku.gemini.aop.threadLocal.SessionLocal;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.dto.PageParamsDto;
import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.dto.TextWriteDto;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.TextUserVo;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import com.hekiraku.gemini.manager.TextRecordManager;
import com.hekiraku.gemini.service.TextRecordService;
import com.hekiraku.gemini.service.UserService;
import com.hekiraku.gemini.utils.BeanUtils;
import com.hekiraku.gemini.utils.SnowFlakeUtils;
import net.bytebuddy.asm.Advice;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.hekiraku.gemini.utils.EntityUtil.setCommonField;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
@Service
public class TextRecordServiceImpl implements TextRecordService {
    private TextRecordManager textRecordManager;
    @Autowired
    private void setTextRecordManager(TextRecordManager textRecordManager){
        this.textRecordManager = textRecordManager;
    }
    private UserService userService;
    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    @Override
    @Transactional
    public ApiResult<TextUserVo> writeRecord(TextWriteDto textWriteDto) throws Exception {
        UserInfoVo userInfoVo = SessionLocal.getUserInfo();
        Long userId = userInfoVo.getUserId();
        textWriteDto.setUserId(userId);
        String createDay = Optional.ofNullable(textWriteDto.getCreateDay()).orElse(LocalDate.now().toString());
        textWriteDto.setCreateDay(createDay);
        Validate.notEmpty(textWriteDto.getSoulChar(),"人格信息不能为空");
        TextReadDto textReadDto = TextReadDto.builder().build();
        BeanUtils.copyNotNullProperties(textReadDto,textWriteDto);
        TextUserEntity textUserEntity = textRecordManager.selectTextByDayUsrChar(textReadDto);
        if(textUserEntity==null){
            Long textId = SnowFlakeUtils.nextId();
            textWriteDto.setTextId(textId);
        }else {
            textWriteDto.setTextId(textUserEntity.getTextId());
        }
        //如果没有就新增
        setCommonField(textWriteDto);
        textRecordManager.createOrUpdateText(textWriteDto);
        return ApiResult.successMsg("成功创建/更新日记");
    }

    /**
     * 根据日期，人格，和用户num获取日记
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult<TextUserVo> readRecord(TextReadDto textReadDto) throws Exception {
        UserInfoVo userInfoVo = SessionLocal.getUserInfo();
        textReadDto.setUserId(userInfoVo.getUserId());
        TextUserVo textUserVo = textRecordManager.selectTextByTextReadDto(textReadDto);
        return ApiResult.buildSuccessNormal("成功获取日记",textUserVo);

    }

    @Override
    public ApiResult<PageInfo<TextSummaryEntity>> selectOpenTextByCreateDayAndSoulChar(TextReadDto textReadDto, PageParamsDto pageParamsDto) throws Exception {
        textReadDto.setCreateDay(LocalDate.now().toString());
        PageInfo<TextSummaryEntity> textSummaryEntityPageInfo = textRecordManager.selectOpenTextPageByCreateDayAndSoulChar(textReadDto,pageParamsDto);
        return ApiResult.buildSuccessNormal("成功获取公开日记",textSummaryEntityPageInfo);
    }
}
