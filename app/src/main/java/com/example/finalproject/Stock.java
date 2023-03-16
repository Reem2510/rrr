package com.example.finalproject;

public class Stock {
    private String description,StockName,value,Symbol;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "description='" + description + '\'' +
                ", StockName='" + StockName + '\'' +
                ", value='" + value + '\'' +
                ", Symbol='" + Symbol + '\'' +
                '}';
    }

    public Stock(String description, String stockName, String value) {
        this.description = description;
        this.StockName = stockName;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStockName() {
        return StockName;
    }

    public void setStockName(String stockName) {
        StockName = stockName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
