package nyc.c4q.leighdouglas.memehunt;

import nl.qbusict.cupboard.annotation.Column;

/**
 * Created by leighdouglas on 12/6/16.
 */

public class Meme {

    public Long _id;
    public String name;
    public int usedAlready;

    @Column("image_url")
    public String url;

    public Meme(){
        this.name = "no name";
        this.url = "no url";
        this.usedAlready = 0;
    }

    public Meme(String name, String url){
        this.name = name;
        this.url = url;
        //by default, used already is set to false
        this.usedAlready = 0;
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
