package phongtaph31865.poly.healthyapp.Model;

import java.util.List;

public class Cart {
    private String _id, prId, img;
    private int quantity, price;

    public Cart(String _id, String prId, int quantity) {
        this._id = _id;
        this.prId = prId;
        this.quantity = quantity;
    }

    public Cart() {

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
