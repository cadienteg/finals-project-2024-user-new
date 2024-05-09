package DTO;

public class UserInfo {

    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;
    private String street;
    private String barangay;
    private String municipality;
    private String city;
    private String  ZIPcode;
    private String Email;
    private Long PhoneNumber;
    private String nationality;

    private String gender;
    private String roleAtSchool;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getBirthdate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = Long.valueOf(PhoneNumber);
    }


    public String getStreet() {
        return street;
    }
    public void setStreet(String Street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }
    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getMunicipality() {
        return municipality;}
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZIPcode() {
        return ZIPcode;
    }
    public void setZIPcode(String ziPcode) {
        this.ZIPcode = ziPcode;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRoleAtSchool() {
        return roleAtSchool;
    }
    public void setroleAtSchool(String roleAtSchool) { this.roleAtSchool = roleAtSchool;}

    public void setisValidId(String adminId) {
    }

    public void setAdminID(String adminId) {
    }

    public void displayUserInfo() {
    }

    public void setUsername(String username) {
    }

    public void setPassword(String password) {
    }
}






