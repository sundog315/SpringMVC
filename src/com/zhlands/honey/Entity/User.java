package com.zhlands.honey.Entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class User implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

    private Long fd_id;

    private String fd_name;

    private String fd_loginName;

    private String fd_email;

    private String fd_mobile;

    private String fd_password;

    private String fd_token;

    private String fd_status;

    public Long getId()
    {
        return fd_id;
    }

    public void setId(Long fd_id)
    {
        this.fd_id = fd_id;
    }

    public String getLoginName()
    {
        return fd_loginName;
    }

    public void setLoginName(String fd_loginName)
    {
        this.fd_loginName = fd_loginName;
    }

    public String getName()
    {
        return fd_name;
    }

    public void setName(String fd_name)
    {
        this.fd_name = fd_name;
    }

    public String getPassword()
    {
        return fd_password;
    }

    public void setPassword(String fd_password)
    {
        this.fd_password = fd_password;
    }

    public String getMobile()
    {
        return fd_mobile;
    }

    public void setMobile(String fd_mobile)
    {
        this.fd_mobile = fd_mobile;
    }

    public String getEmail()
    {
        return fd_email;
    }

    public void setEmail(String fd_email)
    {
        this.fd_email = fd_email;
    }

    public String getStatus()
    {
        return fd_status;
    }

    public void setStatus(String fd_status)
    {
        this.fd_status = fd_status;
    }

    public String getToken()
    {
        return fd_token;
    }

    public void setToken(String fd_token)
    {
        this.fd_token = fd_token;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
