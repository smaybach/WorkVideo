package my1508d.myyuekao.bean;

/**
 * Created by wangguojian on 2017/12/14.
 */

public class MessageBean {
    public String titlte;
    public String url;

    public MessageBean(String titlte, String url) {
        this.titlte = titlte;
        this.url = url;
    }

    public String getTitlte() {
        return titlte;
    }

    public void setTitlte(String titlte) {
        this.titlte = titlte;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
