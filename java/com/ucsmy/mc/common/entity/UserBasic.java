package com.ucsmy.mc.common.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserBasic implements UserDetails{
    private String usbaId;

    private String depaId;

    private String usbaAccount;

    private String usbaName;

    private String usbaPassword;

    private Byte usbaFailureCount;

    private Byte usbaCredentialExpired;

    private Byte usbaAccountExpired;

    private Byte usbaAccountEnable;

    private Byte usbaAccountLocked;

    private Date usbaModifyDate;

    private Date usbaCreateDate;

    private String usbaSuperiorId;

    private Date usbaLastActiveTime;

    private String usbaCredentialToken;

    private String usbaCredentialIp;
    
    private String usbaMail;

    private String usbaPhone;
    
    /** 用户的角色列表. */
    private List<UserRole> usbaUserRoles;
    
    /** 权限列表，security使用. */
	private List<GrantedAuthority> usbaGrantedAuthoritys;

    public String getUsbaId() {
        return usbaId;
    }

    public void setUsbaId(String usbaId) {
        this.usbaId = usbaId;
    }

    public String getDepaId() {
        return depaId;
    }

    public void setDepaId(String depaId) {
        this.depaId = depaId;
    }

    public String getUsbaAccount() {
        return usbaAccount;
    }

    public void setUsbaAccount(String usbaAccount) {
        this.usbaAccount = usbaAccount;
    }

    public String getUsbaName() {
        return usbaName;
    }

    public void setUsbaName(String usbaName) {
        this.usbaName = usbaName;
    }

    public String getUsbaPassword() {
        return usbaPassword;
    }

    public void setUsbaPassword(String usbaPassword) {
        this.usbaPassword = usbaPassword;
    }

    public Byte getUsbaFailureCount() {
        return usbaFailureCount;
    }

    public void setUsbaFailureCount(Byte usbaFailureCount) {
        this.usbaFailureCount = usbaFailureCount;
    }

    public Byte getUsbaCredentialExpired() {
        return usbaCredentialExpired;
    }

    public void setUsbaCredentialExpired(Byte usbaCredentialExpired) {
        this.usbaCredentialExpired = usbaCredentialExpired;
    }

    public Byte getUsbaAccountExpired() {
        return usbaAccountExpired;
    }

    public void setUsbaAccountExpired(Byte usbaAccountExpired) {
        this.usbaAccountExpired = usbaAccountExpired;
    }

    public Byte getUsbaAccountEnable() {
        return usbaAccountEnable;
    }

    public void setUsbaAccountEnable(Byte usbaAccountEnable) {
        this.usbaAccountEnable = usbaAccountEnable;
    }

    public Byte getUsbaAccountLocked() {
        return usbaAccountLocked;
    }

    public void setUsbaAccountLocked(Byte usbaAccountLocked) {
        this.usbaAccountLocked = usbaAccountLocked;
    }

    public Date getUsbaModifyDate() {
        return usbaModifyDate;
    }

    public void setUsbaModifyDate(Date usbaModifyDate) {
        this.usbaModifyDate = usbaModifyDate;
    }

    public Date getUsbaCreateDate() {
        return usbaCreateDate;
    }

    public void setUsbaCreateDate(Date usbaCreateDate) {
        this.usbaCreateDate = usbaCreateDate;
    }

    public String getUsbaSuperiorId() {
        return usbaSuperiorId;
    }

    public void setUsbaSuperiorId(String usbaSuperiorId) {
        this.usbaSuperiorId = usbaSuperiorId;
    }

    public Date getUsbaLastActiveTime() {
        return usbaLastActiveTime;
    }

    public void setUsbaLastActiveTime(Date usbaLastActiveTime) {
        this.usbaLastActiveTime = usbaLastActiveTime;
    }

    public String getUsbaCredentialToken() {
        return usbaCredentialToken;
    }

    public void setUsbaCredentialToken(String usbaCredentialToken) {
        this.usbaCredentialToken = usbaCredentialToken;
    }

    public String getUsbaCredentialIp() {
        return usbaCredentialIp;
    }

    public void setUsbaCredentialIp(String usbaCredentialIp) {
        this.usbaCredentialIp = usbaCredentialIp;
    }
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return usbaGrantedAuthoritys;
	}

	@Override
	public String getPassword() {
		return usbaPassword;
	}

	@Override
	public String getUsername() {
		return usbaAccount;
	}

	@Override
	public boolean isAccountNonExpired() {
		if(usbaAccountExpired != null){
			if (usbaAccountExpired == 1){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		if(usbaAccountLocked != null){
			if (usbaAccountLocked == 1){
				return true;
			}
		}
		return false;
	}

	/**
	 * 取消判断该属性
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		if(usbaAccountEnable != null){
			if (usbaAccountEnable == 1){
				return true;
			}
		}
		return false;
	}

	public List<UserRole> getUsbaUserRoles() {
		return usbaUserRoles;
	}

	public void setUsbaUserRoles(List<UserRole> usbaUserRoles) {
		this.usbaUserRoles = usbaUserRoles;
	}

	public List<GrantedAuthority> getUsbaGrantedAuthoritys() {
		return usbaGrantedAuthoritys;
	}

	public void setUsbaGrantedAuthoritys(List<GrantedAuthority> usbaGrantedAuthoritys) {
		this.usbaGrantedAuthoritys = usbaGrantedAuthoritys;
	}

	//rememberMe
	private boolean rememberMe;

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usbaId == null) ? 0 : usbaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBasic other = (UserBasic) obj;
		if (usbaId == null) {
			if (other.usbaId != null)
				return false;
		} else if (!usbaId.equals(other.usbaId))
			return false;
		return true;
	}

	/**
	 * @return the usbaMail
	 */
	public String getUsbaMail() {
		return usbaMail;
	}

	/**
	 * @param usbaMail the usbaMail to set
	 */
	public void setUsbaMail(String usbaMail) {
		this.usbaMail = usbaMail;
	}

	/**
	 * @return the usbaPhone
	 */
	public String getUsbaPhone() {
		return usbaPhone;
	}

	/**
	 * @param usbaPhone the usbaPhone to set
	 */
	public void setUsbaPhone(String usbaPhone) {
		this.usbaPhone = usbaPhone;
	}
	
}