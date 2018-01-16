package charles.sandbox;

/**
 * Created by cha on 12/21/2017.
 */

public class ImageUploadInfo {

    public String productName,price,email,sellerName,contact;

    public String imageURL;

    public ImageUploadInfo() {
    }

    public ImageUploadInfo(String productName, String price, String email, String sellerName, String contact, String imageURL) {
        this.productName = productName;
        this.price = price;
        this.email = email;
        this.sellerName = sellerName;
        this.contact = contact;
        this.imageURL = imageURL;
    }


    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public String getEmail() {
        return email;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getContact() {
        return contact;
    }

    public String getImageURL() {
        return imageURL;
    }


}
