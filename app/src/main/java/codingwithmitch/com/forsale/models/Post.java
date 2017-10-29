package codingwithmitch.com.forsale.models;

/**
 * Created by User on 10/29/2017.
 */

public class Post {

    private String post_id;
    private String user_id;
    private String image;
    private String title;
    private String description;
    private String price;
    private String country;
    private String state_province;
    private String city;
    private String contact_email;

    public Post(String post_id, String user_id, String image, String title, String description,
                String price, String country, String state_province, String city, String contact_email) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
        this.country = country;
        this.state_province = state_province;
        this.city = city;
        this.contact_email = contact_email;
    }

    public Post() {

    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id='" + post_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", country='" + country + '\'' +
                ", state_province='" + state_province + '\'' +
                ", city='" + city + '\'' +
                ", contact_email='" + contact_email + '\'' +
                '}';
    }
}
