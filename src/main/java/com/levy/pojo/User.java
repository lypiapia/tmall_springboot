package com.levy.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "salt")
	private String salt;
	
	@Transient
	private String anonymousName;	//anonymousName没有和数据库关联，用于获取匿名，其实就是前后保留

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getAnonymousName() {
		if(anonymousName != null) {
			return anonymousName;
		}
		if(name == null) {
			anonymousName = null;
		}else if(name.length() <= 1) {
			anonymousName = "*";
		}else if(name.length() == 2) {
			anonymousName = name.substring(0, 1) + "*";
		}else {
			char[] c = name.toCharArray();
			for(int i = 1;i < c.length - 1;i++) {
				c[i] = '*';
			}
			anonymousName = new String(c);
		}
		return anonymousName;
	}
	
	public void setAnonymousName(String anonymousName) {
		this.anonymousName = anonymousName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", salt=" + salt + ", anonymousName="
				+ anonymousName + "]";
	}
	
}
