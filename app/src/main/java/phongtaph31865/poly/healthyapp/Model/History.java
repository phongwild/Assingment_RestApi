package phongtaph31865.poly.healthyapp.Model;

public class History {
    String _id, cartId, time, date;

    public History(String _id, String cartId, String time, String date) {
        this._id = _id;
        this.cartId = cartId;
        this.time = time;
        this.date = date;
    }

    public History() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
