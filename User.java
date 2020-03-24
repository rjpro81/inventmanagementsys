
/**
 * This class provides user objects for the application.
 * @author Ralph Julsaint
 */
class User {
  private int userNo;
  private String userFirstName;
  private String userLastName;
  private String userEmail;
  private int userPhone;
  private String userName;
  private String userPassword;
  
  User(final int userNo, final String userFirstName, final String userLastName, String userEmail, String userName, String userPassword){
	this(userNo, userFirstName, userLastName, userEmail, 0, userName, userPassword);  
  }
  
  User(final int userNo, final String userFirstName, final String userLastName, String userEmail, int userPhone, String userName, String userPassword){
    this.userNo = userNo;
    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.userEmail = userEmail;
    this.userPhone = userPhone;
    this.userName = userName;
    this.userPassword = userPassword;
  }
  /**
   * Mutator methods
   */  
  void setUserEmail(String userEmail){
    this.userEmail = userEmail;
  }
  
  void setUserPhone(int userPhone){
    this.userPhone = userPhone;
  }
  
  void setUserName(String userName){
	this.userName = userName;  
  }
  
  void setUserPassword(String userPassword){
    this.userPassword = userPassword;
  }
  /**
   * Accessor Methods
   */
  int getUserNo(){
    return userNo;
  }
  
  String getUserFirstName(){
    return userFirstName;
  }
  
  String getUserLastName(){
    return userLastName;
  }
  
  String getUserEmail(){
    return userEmail;
  }
  
  int getUserPhone(){
	return userPhone;  
  }
  
  String getUserName(){
	return userName;  
  }
  
  String getUserPassword(){
    return userPassword;
  }
}
