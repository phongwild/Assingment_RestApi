package phongtaph31865.poly.healthyapp.Model;

import java.io.Serializable;

public class Users implements Serializable {
    private String _id, fullName, password, email, token;

    @Override
    public String toString() {
        return "Users{" +
                "_id='" + _id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public Users(String _id, String fullName, String password, String email, String token) {
        this._id = _id;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.token = token;
    }

    public Users() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
