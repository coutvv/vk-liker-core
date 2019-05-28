package ru.coutvv.vkliker.core.api.entity.user.parts;

import ru.coutvv.vkliker.core.api.support.raw.Json;

/**
 * @author coutvv    02.07.2018
 */
public class Identity {

	long id;

	String firstName;
	String lastName;
	int sex; // 1 - female, 2 - male
	String bdate;
	String photoUrl;

	public Identity(Json json) {
		this.firstName = json.stringField("first_name");
		this.lastName = json.stringField("last_name");
		this.bdate = json.stringField("bdate");
		this.photoUrl = json.stringField("photo_200_orig");
		this.sex = json.longField("sex").intValue();

		this.id = json.longField("id");
	}

	@Override
	public String toString() {
		return "Identity{" +
				"id = " + id + ";\t" +
				"  firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", sex=" + sex +
				", bdate='" + bdate + '\'' +
				", photoUrl='" + photoUrl + '\'' +
				'}';
	}

	public long getId() {
		return id;
	}
}
