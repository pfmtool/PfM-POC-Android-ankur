package com.PfM_Demo_Backend.data;

/**
 * Created by CODEMUD on 2/26/2016.
 */
public class Portfolio {
    public static final String PORTFOLIO_PARENT_ENTITY_NAME = "PortfolioParent";
    public static final String PORTFOLIO_PARENT_KEY_NAME = "PortfolioParent";

    public static final String PORTFOLIO_ENTITY_NAME = "Portfolio";
    public static final String FIELD_NAME_NAME = "portfolioName";
    public static final String FIELD_NAME_CUSIP = "cusip";
    public static final String FIELD_NAME_CODE = "code";
    public static final String FIELD_NAME_DESC = "desc";
    public static final String FIELD_NAME_OPENDATE = "opendate";
    public static final String FIELD_NAME_EXPDATE = "expdate";
    public static final String KEY_NAME = FIELD_NAME_NAME;
    public String portfolioName;
    public String cusip;
    public String code;
    public String opendate;
    public String expdate;
    public String desc;

    public Portfolio(String pfName, String cusip,String code, String openDate, String expDate, String desc ){
        this.portfolioName = pfName;
        this.cusip = cusip;
        this.code = code;
        this.opendate = openDate;
        this.expdate = expDate;
        this.desc = desc;
    }
}
