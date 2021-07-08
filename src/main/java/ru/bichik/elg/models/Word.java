package ru.bichik.elg.models;

public class Word {
    private String en;
    private String ru;
    private int id;
    private String tryEn;



    public Word(String en, String ru, int id) {
        this.en = en;
        this.ru = ru;
        this.id = id;
    }

    public Word() {

    }

    public String getTryEn() { return tryEn; }

    public void setTryEn(String tryEn) { this.tryEn = tryEn; }

    public String getEn() { return en; }

    public void setEn(String en) { this.en = en; }

    public String getRu() { return ru; }

    public void setRu(String ru) { this.ru = ru; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

}
