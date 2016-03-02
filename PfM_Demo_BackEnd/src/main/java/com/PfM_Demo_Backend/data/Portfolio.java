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
    public static final String KEY_NAME = FIELD_NAME_NAME;
    public String portfolioName;
    public String cusip;
    public Portfolio(String port, String cusip){
        this.portfolioName = port;
        this.cusip = cusip;
    }
}
