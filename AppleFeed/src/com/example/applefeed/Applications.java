package com.example.applefeed;

public class Applications {
String  developername,category,price,release_date,app_name,imageUrl,id;

public String getDevelopername() {
	return developername;
}

public void setDevelopername(String developername) {
	this.developername = developername;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public String getRelease_date() {
	return release_date;
}

public void setRelease_date(String release_date) {
	this.release_date = release_date;
}

public String getApp_name() {
	return app_name;
}

public void setApp_name(String app_name) {
	this.app_name = app_name;
}

@Override
public String toString() {
	return "Applications [developername=" + developername + ", category="
			+ category + ", price=" + price + ", release_date=" + release_date
			+ ", app_name=" + app_name + "]";
}

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

}
