package kz.bitlab.course.db;

public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String avatar;

    public User(){}

    public User(Long id, String email, String password, String fullName, String avatar) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
