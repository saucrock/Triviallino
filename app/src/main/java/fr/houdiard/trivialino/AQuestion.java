package fr.houdiard.trivialino;

public class AQuestion {
    private String Q;
    private String cate;
    private String goodA;
    private String[] badA;
    private String diff;




    public AQuestion(String a, String b, String c, String d, String e, String f, String g) {
        Q = e;
        goodA = a;
        badA = new String[3];
        badA[0] = b;
        badA[1] = c;
        badA[2] = d;
        cate = f;
        diff = g;

    }

    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q = q;
    }

    public String getGoodA() {
        return goodA;
    }

    public void setGoodA(String goodA) {
        this.goodA = goodA;
    }

    public String[] getBadA() {
        return badA;
    }

    public void setBadA(String[] badA) {
        this.badA = badA;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }
}
