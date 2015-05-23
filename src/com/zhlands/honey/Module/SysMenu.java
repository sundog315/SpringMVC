package com.zhlands.honey.Module;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SysMenu implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.fd_id
     *
     * @mbggenerated
     */
    private Integer fdId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.text
     *
     * @mbggenerated
     */
    private String text;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.parent_field
     *
     * @mbggenerated
     */
    private Integer parentField;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.dispay_order
     *
     * @mbggenerated
     */
    private Integer dispayOrder;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.fd_id
     *
     * @return the value of sys_menu.fd_id
     *
     * @mbggenerated
     */
    public Integer getFdId() {
        return fdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.fd_id
     *
     * @param fdId the value for sys_menu.fd_id
     *
     * @mbggenerated
     */
    public void setFdId(Integer fdId) {
        this.fdId = fdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.text
     *
     * @return the value of sys_menu.text
     *
     * @mbggenerated
     */
    public String getText() {
        return text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.text
     *
     * @param text the value for sys_menu.text
     *
     * @mbggenerated
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.parent_field
     *
     * @return the value of sys_menu.parent_field
     *
     * @mbggenerated
     */
    public Integer getParentField() {
        return parentField;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.parent_field
     *
     * @param parentField the value for sys_menu.parent_field
     *
     * @mbggenerated
     */
    public void setParentField(Integer parentField) {
        this.parentField = parentField;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.dispay_order
     *
     * @return the value of sys_menu.dispay_order
     *
     * @mbggenerated
     */
    public Integer getDispayOrder() {
        return dispayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.dispay_order
     *
     * @param dispayOrder the value for sys_menu.dispay_order
     *
     * @mbggenerated
     */
    public void setDispayOrder(Integer dispayOrder) {
        this.dispayOrder = dispayOrder;
    }
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}