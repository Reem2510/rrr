package com.example.finalproject;

public class Stock {
    private String StockName,value,Symbol;

    @Override
    public String toString() {
        return "Stock{" +
                ", StockName='" + StockName + '\'' +
                ", value='" + value + '\'' +
                ", Symbol='" + Symbol + '\'' +
                '}';
    }



    public Stock(String symbol, String stockName, String value) {
        this.StockName = stockName;
        this.value = value;
        this.Symbol=symbol;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
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
