package com.zhlands.honey.Dao;

import com.zhlands.honey.Entity.PmForecast;
import com.zhlands.honey.Dao.MyBatisRepository;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface PmForecastMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_forecast
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long fdId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_forecast
     *
     * @mbggenerated
     */
    int insert(PmForecast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_forecast
     *
     * @mbggenerated
     */
    PmForecast selectByPrimaryKey(Long fdId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_forecast
     *
     * @mbggenerated
     */
    List<PmForecast> selectAll(Map<String, Integer> pm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pm_forecast
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PmForecast record);
    
    int getCount();
}