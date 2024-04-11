package phongtaph31865.poly.healthyapp.Model;

public class Product {
    String Product_name, Description, CateId, _id, img_product;
    int  Price;

    public Product(String product_name, String description, String _id, String img_product, int price) {
        Product_name = product_name;
        Description = description;
        this._id = _id;
        this.img_product = img_product;
        Price = price;
    }

    public Product(String _id) {
        this._id = _id;
    }

    public Product() {
    }

    public String getImg_product() {
        return img_product;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCateId() {
        return CateId;
    }

    public void setCateId(String cateId) {
        CateId = cateId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
