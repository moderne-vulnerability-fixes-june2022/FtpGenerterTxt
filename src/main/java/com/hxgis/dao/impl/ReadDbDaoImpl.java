package com.hxgis.dao.impl;

import com.hxgis.dao.ReadDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Li Hong on 2018/5/9 0009.
 */
@Component
public class ReadDbDaoImpl implements ReadDb {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<Map<String, Object>> readDBYB(String start, String end) {
        String sql = "select * from HB_MET.dbo.MET_CN_CityForecast WHERE StationNo IN(select StationNo from HB_MET.dbo.AutoStationInfo where province = '湖北') " +
                " and PublishTime >='"+start+"' AND PublishTime <'"+end+"'";
        Map map = new HashMap();
        return namedParameterJdbcTemplate.queryForList(sql,map);
    }

    @Override
    public List<Map<String, Object>> readDBYJ(String start, String end) {
        String sql = "select * from HB_MET.dbo.MET_FOREWARN_ALARMSIGNALHB WHERE InsertTime>='"+start+"' and InsertTime<'"+end+"'";
        Map map = new HashMap();
        return namedParameterJdbcTemplate.queryForList(sql,map);
    }

}
