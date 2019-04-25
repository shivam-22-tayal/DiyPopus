package com.ibm.diypopups.payload;

public class UserSummary {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private String type;
    private int Downcredits;
    private int Upcredits;

    


	public UserSummary(Long id, String username, String firstname, String lastname, String email, String gender, String type)
    {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.type = type;
    }


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public int getDowncredits() {
		return Downcredits;
	}


	public void setDowncredits(int downcredits) {
		Downcredits = downcredits;
	}


	public int getUpcredits() {
		return Upcredits;
	}


	public void setUpcredits(int upcredits) {
		Upcredits = upcredits;
	}
}