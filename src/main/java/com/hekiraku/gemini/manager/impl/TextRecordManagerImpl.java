package com.hekiraku.gemini.manager.impl;

import com.hekiraku.gemini.domain.entity.TextUserEntity;
import com.hekiraku.gemini.domain.vo.SoulCharDateVo;
import com.hekiraku.gemini.domain.vo.SoulCharVo;
import com.hekiraku.gemini.manager.TextRecordManager;
import com.hekiraku.gemini.mapper.TextRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private TextRecordMapper textRecordMapper;

    @Override
    public int create(TextUserEntity textRecordEntity) {
        return textRecordMapper.create(textRecordEntity);
    }

    @Override
    public int update(TextUserEntity textRecordEntity) {
        return textRecordMapper.update(textRecordEntity);
    }

    @Override
    public int deleteSoft(TextUserEntity textRecordEntity) {
        return textRecordMapper.deleteSoft(textRecordEntity);
    }

    @Override
    public TextUserEntity selectTextByDayUsrChar(TextDto textRecordDto) {
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
    public List<TextUserEntity> selectTextByDayUsrCharList(TextDto textRecordDto) {
        List<TextUserEntity> textRecordEntities = new ArrayList<>();
        TextUserEntity textRecordEntity = textRecordMapper.selectTextByDayUsrChar(textRecordDto);
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
