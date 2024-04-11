package phongtaph31865.poly.healthyapp.Model;

public class Category {
    private String _id, CateName, img_cate;

    public Category() {
    }

    public Category(String _id, String cateName, String img_cate) {
        this._id = _id;
        CateName = cateName;
        this.img_cate = img_cate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }

    public String getImg_cate() {
        return img_cate;
    }

    public void setImg_cate(String img_cate) {
        this.img_cate = img_cate;
    }
}
