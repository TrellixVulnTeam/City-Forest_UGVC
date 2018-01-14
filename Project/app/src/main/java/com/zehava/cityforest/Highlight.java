package com.zehava.cityforest;

/**
 * Created by avigail on 10/01/18.
 */

public class Highlight
{
    private String attributeName;
    private String highlightedValue;

    public Highlight(String attributeName, String highlightedValue)
    {
        this.attributeName = attributeName;
        this.highlightedValue = highlightedValue;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public String getHighlightedValue()
    {
        return highlightedValue;
    }
}
