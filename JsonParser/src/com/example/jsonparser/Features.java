package com.example.jsonparser;

import org.json.JSONException;
import org.json.JSONObject;

public class Features {
   public Features(String mPG, String accelaration, String cylinders,
			String displacement, String horsepower, String model_year,
			String name, String weight) {
		super();
		MPG = mPG;
		this.accelaration = accelaration;
		this.cylinders = cylinders;
		this.displacement = displacement;
		this.horsepower = horsepower;
		this.model_year = model_year;
		this.name = name;
		this.weight = weight;
	}

public Features(JSONObject featuresJSONObject) throws JSONException {
  this.MPG = featuresJSONObject.getString("MPG");
  this.accelaration = featuresJSONObject.getString("acceleration");
  this.cylinders = featuresJSONObject.getString("cylinders");
  this.displacement = featuresJSONObject.getString("displacement");
  this.horsepower = featuresJSONObject.getString("horsepower");
  this.model_year = featuresJSONObject.getString("model_year");
  this.name = featuresJSONObject.getString("name");
  this.weight = featuresJSONObject.getString("weight");
	 
}

String MPG,accelaration,cylinders,displacement,horsepower,model_year,name,weight;

public String getMPG() {
	return MPG;
}

public void setMPG(String mPG) {
	MPG = mPG;
}

public String getAccelaration() {
	return accelaration;
}

public void setAccelaration(String accelaration) {
	this.accelaration = accelaration;
}

public String getCylinders() {
	return cylinders;
}

public void setCylinders(String cylinders) {
	this.cylinders = cylinders;
}

public String getDisplacement() {
	return displacement;
}

public void setDisplacement(String displacement) {
	this.displacement = displacement;
}

public String getHorsepower() {
	return horsepower;
}

public void setHorsepower(String horsepower) {
	this.horsepower = horsepower;
}

public String getModel_year() {
	return model_year;
}

public void setModel_year(String model_year) {
	this.model_year = model_year;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getWeight() {
	return weight;
}

public void setWeight(String weight) {
	this.weight = weight;
}

@Override
public String toString() {
	return "Features [MPG=" + MPG + ", accelaration=" + accelaration
			+ ", cylinders=" + cylinders + ", displacement=" + displacement
			+ ", horsepower=" + horsepower + ", model_year=" + model_year
			+ ", name=" + name + ", weight=" + weight + "]";
}
}
