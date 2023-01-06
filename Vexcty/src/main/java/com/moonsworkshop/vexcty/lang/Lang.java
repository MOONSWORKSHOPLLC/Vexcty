package com.moonsworkshop.vexcty.lang;

public enum Lang {

    EN("English"), // english
    AR("عربي"), // arabic
    FR("Français"), // french
    IT("Italiano"), // itialaion
    JA("日本"), // japanese
    LA("Latinus"), // latin
    PA("ਪੰਜਾਬੀ"), // punjabi
    RU("Русский"), // russian
    ES("Español"), // spanish
    TR("Türk"), // turkish
    UR("اردو"), // urdu
    AM("አማርኛ"), // Amharic
    ZH("简体中文"), // chiniese (simplified)
    ZHHK("中國傳統的"), // chiniese tradintadel
    CS("čeština"), // czech
    DE("Deutsch"), // german
    FI("Suomalainen"), // finish
    PT("Português"), // porguese
    PL("Polski"); // polish


    private String name;

    Lang(String name) {
        this.name = name;
    }
}