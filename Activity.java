package com.ralph.inventmanagementsys;

/**
 * This abstract class provides a template for inventory activity to be displayed in inventory activity table.
 */

import java.time.LocalDate;

class Activity {
  private String activity;
  private LocalDate date;

  Activity(){

  }	  
  Activity(String a, LocalDate d){
    setActivity(a);
    setDate(d);
  }
	  
  void setActivity(String a){
    activity = a;
  }
	  
  void setDate(LocalDate d){
    date = d;
  }
	  
  String getActivity(){
    return activity;
  }
	  
  LocalDate getDate(){
    return date;
  }
	  
  @Override
  public String toString(){
    return String.format("%s -- on %s%n", getActivity(), getDate().toString());
  }
}
