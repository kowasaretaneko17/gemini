package com.hekiraku.gemini.service.impl;

import com.github.pagehelper.PageInfo;
import com.hekiraku.gemini.aop.threadLocal.SessionLocal;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.dto.PageParamsDto;
import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.dto.TextWriteDto;
import com.hekiraku.gemini.domain.entity.TextDetailEntity;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.TextDetailVo;
import com.hekiraku.gemini.domain.vo.TextSummaryEntityVo;
import com.hekiraku.gemini.domain.vo.TextUserVo;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import com.hekiraku.gemini.manager.TextRecordManager;
import com.hekiraku.gemini.service.TextRecordService;
import com.hekiraku.gemini.service.UserService;
import com.hekiraku.gemini.utils.BeanUtils;
import com.hekiraku.gemini.utils.SnowFlakeUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static com.hekiraku.gemini.utils.EntityUtil.setCommonField;
import static java.lang.Long.getLong;

/**
 * 构建组：gemini星云总线技术总局
 * 作者:hekiraku
 * 邮箱:hekiraku@foxmail.com
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
        Long userId = getLong(userInfoVo.getUserId());
        textWriteDto.setUserId(userId);
        String createDay = Optional.ofNullable(textWriteDto.getCreateDay()).orElse(LocalDate.now().toString());
        textWriteDto.setCreateDay(createDay);
        Validate.notEmpty(textWriteDto.getSoulChar(),"人格信息不能为空");
        TextReadDto textReadDto = new TextReadDto();
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
        textReadDto.setUserId(getLong(userInfoVo.getUserId()));
        TextUserVo textUserVo = textRecordManager.selectTextByTextReadDto(textReadDto);
        return ApiResult.buildSuccessNormal("成功获取日记",textUserVo);

    }

    @Override
    public ApiResult<PageInfo<TextSummaryEntityVo>> selectOpenTextByCreateDayAndSoulChar(TextReadDto textReadDto, PageParamsDto pageParamsDto) throws Exception {
        textReadDto.setCreateDay(LocalDate.now().toString());
        PageInfo<TextSummaryEntityVo> textSummaryEntityPageInfo = textRecordManager.selectOpenTextPageByCreateDayAndSoulChar(textReadDto,pageParamsDto);
        return ApiResult.buildSuccessNormal("成功获取公开日记",textSummaryEntityPageInfo);
    }

    @Override
    public ApiResult<TextDetailVo> selectTextDetailByTextId(Long textId) {
        TextDetailVo textDetailVo = textRecordManager.selectTextDetailByTextId(textId);
        return ApiResult.buildSuccessNormal("成功获取公开日记",textDetailVo);
    }
}
