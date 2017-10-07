package com.Mail.DAO;

import com.Mail.DTO.TAction;

import java.util.List;

import com.Mail.DTO.TActionExample;
import org.apache.ibatis.annotations.Param;

public interface TActionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    int countByExample(TActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    int deleteByExample(TActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    int insert(TAction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    int insertSelective(TAction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    List<TAction> selectByExample(TActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    TAction selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TAction record, @Param("example") TActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TAction record, @Param("example") TActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TAction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_action
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TAction record);
}