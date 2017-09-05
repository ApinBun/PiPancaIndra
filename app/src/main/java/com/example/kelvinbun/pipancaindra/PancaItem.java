package com.example.kelvinbun.pipancaindra;



public class PancaItem {
    private int gambarpanca;
    private String judulpanca;

    public int getGambarpanca() {
        return gambarpanca;
    }

    public void setGambarpanca(int gambarpanca) {
        this.gambarpanca = gambarpanca;
    }

    public String getJudulpanca() {
        return judulpanca;
    }

    public void setJudulpanca(String judulpanca) {
        this.judulpanca = judulpanca;
    }

    public PancaItem(int gambarpanca, String judulpanca) {
        this.gambarpanca = gambarpanca;
        this.judulpanca = judulpanca;
    }
}
