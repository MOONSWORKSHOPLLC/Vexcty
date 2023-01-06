package com.moonsworkshop.vexcty.util;

public class User
{
    private String captcha;
    private Boolean passed;

    public User(String captchaString)
    {
        this(captchaString, false);
    }

    public User(String captchaString, boolean passedBool)
    {
        this.setCaptcha(captchaString);
        this.setPassed(passedBool);
    }

    public void setPassed(Boolean passedBool)
    {
        passed = passedBool;
    }

    public Boolean hasPassed()
    {
        return passed;
    }

    public void setCaptcha(String newCaptcha)
    {
        captcha = newCaptcha;
    }

    public String getCaptcha()
    {
        return captcha;
    }
}
