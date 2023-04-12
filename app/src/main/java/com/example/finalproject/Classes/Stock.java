package com.example.finalproject.Classes;

public class
Stock {
    private String StockName,value,Symbol;


    @Override
    public String toString() {
        return "Stock{" +
                ", StockName='" + StockName + '\'' +
                ", value='" + value + '\'' +
                ", Symbol='" + Symbol + '\'' +
                '}';
    }



    public Stock(String stockName, String symbol, String value) {
        this.StockName = stockName;
        this.Symbol=symbol;
        this.value = value;


    }

 public Stock(){}
    public void setStockName(String stockName) {
        StockName = stockName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getSymbol() {
        return Symbol;
    }
    public String getStockName() {
        return StockName;
    }
    public String getValue() {
        return value;
    }
}
