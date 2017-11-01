package codingwithmitch.com.forsale.models;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 10/31/2017.
 */

@IgnoreExtraProperties
public class PostSource {

    @SerializedName("_source")
    @Expose
    private Post post;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
