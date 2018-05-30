package com.hxgis.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Li Hong on 2018/5/9 0009.
 */
//读取数据库并生成csv
public interface ReadDb {
    List<Map<String,Object>> readDBYB(String start,String end);
    List<Map<String,Object>> readDBYJ(String start,String end);

}
