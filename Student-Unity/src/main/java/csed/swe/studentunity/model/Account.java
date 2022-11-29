package csed.swe.studentunity.model;


public class Account {
    private String name;
    private Long Id;
    private String email;
    private String role;

    public Account() {}

    public Account(String name, String email, String password, String role, String token) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Long getStudentId() {
        return Id;
    }

    public void setStudentId(Long studentId) {
        this.Id = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
