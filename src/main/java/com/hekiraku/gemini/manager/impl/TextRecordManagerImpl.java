package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.entity.TextRecordEntity;
import com.hekiraku.gemini.entity.dto.TextRecordDto;
import com.hekiraku.gemini.entity.vo.SoulCharDateVo;
import com.hekiraku.gemini.entity.vo.SoulCharVo;
import com.hekiraku.gemini.manager.TextRecordManager;
import com.hekiraku.gemini.mapper.TextRecordMapper;
import com.hekiraku.gemini.utils.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<List<SoulCharVo>> selectSoulDiaryByUserAndYear(String years, String userNum) {
        List<List<SoulCharVo>> soulCharList = new ArrayList<>();
        List<SoulCharDateVo> soulCharDateVos = textRecordMapper.selectSoulDiaryByUserAndYear(years,userNum);
        //12个月
        for(int i = 1;i<=12;i++) {
            List<SoulCharVo> soulCharVos = new ArrayList<>();
            int month = i;
            //检查所有月份相等的数据
            List<SoulCharDateVo> soulCharDateMonthFilter = soulCharDateVos
                    .stream()
                    .filter(soulCharDateVo -> (month == soulCharDateVo.getMonth()))
                    .collect(Collectors.toList());
            //检查当月存在日记的数据
            for (int j = 1; j <= 32; j++) {
                int day = j;
//                List<SoulCharVo> soulCharVoList = soulCharDateMonthFilter
//                        .stream()
//                        .filter(soulCharDateVo -> (day == soulCharDateVo.getDay()))
//                        .collect(//todo
//                                Collectors.toList()
//                                );
            }
        }
    }
}
