package nyc.c4q.leighdouglas.memehunt;

import nl.qbusict.cupboard.annotation.Column;

/**
 * Created by leighdouglas on 12/6/16.
 */

public class Meme {
    public static String MEME_ID = "meme id";
    public static final int MEME_UNCOLLECTED = 0;
    public static final int MEME_COLLECTED = 1;

    private Long _id;
    private String name;
    private int usedAlready;

    @Column("image_url")
    public String url;

    public Meme(){
        this.name = "no name";
        this.url = "no url";
        this.usedAlready = Meme.MEME_UNCOLLECTED;
    }

    public Meme(String name, String url){
        this.name = name;
        this.url = url;
        //by default, used already is set to false
        this.usedAlready = Meme.MEME_UNCOLLECTED;
    }


    public Long get_id() {
        return _id;
    }

    public String getUrl() {
        return url;
    }

    public int getUsedAlready() {
        return usedAlready;
    }

    public String getName() {
        return name;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsedAlready(int usedAlready) {
        this.usedAlready = usedAlready;
    }

    public void setName(String name) {
        this.name = name;
    }
}
