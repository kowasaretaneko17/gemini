package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.entity.TextRecordEntity;
import com.hekiraku.gemini.entity.dto.TextRecordDto;
import com.hekiraku.gemini.manager.TextRecordManager;
import com.hekiraku.gemini.mapper.TextRecordMapper;
import com.hekiraku.gemini.utils.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
@Component
public class TextRecordManagerImpl implements TextRecordManager {
    @Autowired
    private TextRecordMapper textRecordMapper;

    @Override
    public int create(TextRecordEntity textRecordEntity) {
        return textRecordMapper.create(textRecordEntity);
    }

    @Override
    public int update(TextRecordEntity textRecordEntity) {
        return textRecordMapper.update(textRecordEntity);
    }

    @Override
    public int deleteSoft(TextRecordEntity textRecordEntity) {
        return textRecordMapper.deleteSoft(textRecordEntity);
    }

    @Override
    public TextRecordEntity selectTextByDayUsrChar(TextRecordDto textRecordDto) {
        return textRecordMapper.selectTextByDayUsrChar(textRecordDto);
    }

    /**
     * 查询数据库
     * 如果存在，则加入list之后返回
     * 如果不存在，则生成一条空记录返回。
     *
     * @param textRecordDto
     * @return
     */
    @Override
    public List<TextRecordEntity> selectTextByDayUsrCharList(TextRecordDto textRecordDto) {
        List<TextRecordEntity> textRecordEntities = new ArrayList<>();
        TextRecordEntity textRecordEntity = textRecordMapper.selectTextByDayUsrChar(textRecordDto);
        if (textRecordEntity != null) {
            textRecordEntities.add(textRecordEntity);
        }
        return textRecordEntities;
    }
}
