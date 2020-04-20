package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.domain.dto.TextReadDto;
import com.hekiraku.gemini.domain.dto.TextWriteDto;
import com.hekiraku.gemini.domain.entity.TextDetailEntity;
import com.hekiraku.gemini.domain.entity.TextSummaryEntity;
import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.SoulCharDateVo;
import com.hekiraku.gemini.domain.vo.SoulCharVo;
import com.hekiraku.gemini.domain.vo.TextUserVo;
import com.hekiraku.gemini.manager.TextRecordManager;
import com.hekiraku.gemini.mapper.TextDetailMapper;
import com.hekiraku.gemini.mapper.TextSummaryMapper;
import com.hekiraku.gemini.mapper.TextUserMapper;
import com.hekiraku.gemini.utils.BeanUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
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
    private TextUserMapper textUserMapper;
    @Autowired
    private TextSummaryMapper textSummaryMapper;
    @Autowired
    private TextDetailMapper textDetailMapper;

    @Override
    @Transactional
    public void createOrUpdateText (TextWriteDto textWriteDto) {
        //文章-用户表
        TextUserEntity textUserEntity = BeanUtils.copyProperties(TextUserEntity.class,textWriteDto);
        //文章-简述表
        TextSummaryEntity textSummaryEntity = BeanUtils.copyProperties(TextSummaryEntity.class,textWriteDto);
        //文章-内容表
        TextDetailEntity textDetailEntity = BeanUtils.copyProperties(TextDetailEntity.class,textWriteDto);
        textUserMapper.createOrUpdateTextUser(textUserEntity);
        textSummaryMapper.createOrUpdateTextSummary(textSummaryEntity);
        textDetailMapper.createOrUpdateTextDetail(textDetailEntity);
    }

    @Override
    public TextUserVo selectTextByTextReadDto(TextReadDto textReadDto) {
        return textUserMapper.selectTextByTextReadDto(textReadDto);
    }

    @Override
    public TextUserEntity selectTextByDayUsrChar(TextReadDto textReadDto) {
        return textUserMapper.selectTextByDayUsrChar(textReadDto);
    }

    @Override
    public List<List<SoulCharVo>> selectSoulDiaryByUserAndYear(String years, Long userId) {
        List<List<SoulCharVo>> soulCharList = new ArrayList<>();
        List<SoulCharDateVo> soulCharDateVos = textUserMapper.selectSoulDiaryByUserAndYear(years,userId);
        //12个月
        for(int i = 1;i<=12;i++) {
            List<SoulCharVo> soulCharVos = new ArrayList<>();
            int month = i;
            //检查所有月份相等的数据
            List<SoulCharDateVo> soulCharDateMonthFilter = soulCharDateVos
                    .stream()
                    .filter(soulCharDateVo -> (month == soulCharDateVo.getMonth()))
                    .collect(Collectors.toList());
            //填充数据
            YearMonth yearMonth = YearMonth.of(Integer.parseInt(years),month);
            //获取当月天数
            int monthday = yearMonth.lengthOfMonth();
            //初始化这个月
            for(int j = 0;j<monthday;j++){
                SoulCharVo soulCharVo = new SoulCharVo();
                soulCharVos.add(soulCharVo);
            }
            //对于当月
            for(SoulCharDateVo soulCharDateVo:soulCharDateMonthFilter){
                int day = soulCharDateVo.getDay();
                if("ura".equalsIgnoreCase(soulCharDateVo.getSoulChar())){
                    soulCharVos.get(day-1).setUra(true);
                }
                if("omote".equalsIgnoreCase(soulCharDateVo.getSoulChar())){
                    soulCharVos.get(day-1).setOmote(true);
                }
            }
            soulCharList.add(soulCharVos);
        }
        return soulCharList;
    }
}
