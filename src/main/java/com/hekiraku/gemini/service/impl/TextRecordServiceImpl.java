package com.hekiraku.gemini.service.impl;

import com.hekiraku.gemini.aop.threadLocal.SessionLocal;
import com.hekiraku.gemini.common.ApiResult;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.TextRecordVo;
import com.hekiraku.gemini.domain.vo.UserInfoVo;
import com.hekiraku.gemini.manager.TextRecordManager;
import com.hekiraku.gemini.service.TextRecordService;
import com.hekiraku.gemini.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ApiResult create(TextUserEntity textRecordEntity) {
        int result = textRecordManager.create(textRecordEntity);
        if(result==1){
            return ApiResult.successMsg("创建日记成功");
        }else{
            return ApiResult.buildFail("100","创建日记失败");
        }
    }

    @Override
    public ApiResult update(TextUserEntity textRecordEntity) {
        int result = textRecordManager.update(textRecordEntity);
        if(result==1){
            return ApiResult.successMsg("更新日记成功");
        }else{
            return ApiResult.buildFail("100","更新日记失败");
        }
    }

    @Override
    public ApiResult deleteSoft(TextUserEntity textRecordEntity) {
        int result = textRecordManager.deleteSoft(textRecordEntity);
        if(result==1){
            return ApiResult.successMsg("删除日记成功");
        }else{
            return ApiResult.buildFail("100","删除日记失败");
        }
    }

    @Override
    public ApiResult selectTextByDayUsrChar(TextDto textRecordDto) {
        TextUserEntity recordEntity = textRecordManager.selectTextByDayUsrChar(textRecordDto);
        return ApiResult.buildSuccessNormal("查询成功",recordEntity);
    }

    @Override
    public ApiResult<TextRecordVo> writeRecord(TextDto textRecordDto) throws Exception {
        UserInfoVo userInfoVo = SessionLocal.getUserInfo();
        String createDay = DateTime.now().toString("yyyy-MM-dd");
        textRecordDto.setUserNum(userInfoVo.getUserNum());
        textRecordDto.setCreateDay(createDay);
        List<TextUserEntity> recordEntities = textRecordManager.selectTextByDayUsrCharList(textRecordDto);
        if(recordEntities==null){
            throw new Exception("查询数据库失败");
        }
        if(recordEntities.isEmpty()){
            //数据库没有记录
        TextUserEntity textRecordEntity = TextUserEntity.builder()
                .userNum(textRecordDto.getUserNum())
                .text(textRecordDto.getText())
                .soulChar(textRecordDto.getSoulChar())
                .createDay(createDay)
                .build();
            textRecordManager.create(textRecordEntity);
        }else{
            recordEntities.get(0).setText(textRecordDto.getText());
            textRecordManager.update(recordEntities.get(0));
        }
        return ApiResult.successMsg("成功创建/更新日记");
    }

    /**
     * 根据日期，人格，和用户num获取日记
     * @param textRecordDto
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult<List<TextUserEntity>> readRecord(TextDto textRecordDto) throws Exception {
        UserInfoVo userInfoVo = SessionLocal.getUserInfo();
        textRecordDto.setUserNum(userInfoVo.getUserNum());
        List<TextUserEntity> recordEntities = textRecordManager.selectTextByDayUsrCharList(textRecordDto);
        if(recordEntities==null){
            throw new Exception("查询数据库失败");
        }else if(recordEntities.isEmpty()){
            return ApiResult.successMsg("当天没有日记");
        }
        return ApiResult.buildSuccessNormal("成功获取日记",recordEntities);

    }
}
