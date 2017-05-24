package phuong.example.com.androidproject;

public class Device {
  //private variables
  int _id;
  String name;
  String type;
  String setting;

  // Empty constructor
  public Device() {

  }

  public Device(int _id, String name, String type, String setting) {
    this._id = _id;
    this.name=name;
    this.type = type;
    this.setting = setting;
  }

  public int get_id() {
    return _id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void set_id(int _id) {
    this._id = _id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSetting() {
    return setting;
  }

  public void setSetting(String setting) {
    this.setting = setting;
  }
}
