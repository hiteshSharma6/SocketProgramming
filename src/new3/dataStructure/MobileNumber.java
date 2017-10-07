package new3.dataStructure;

import new3.exception.NotAMobileNumberException;

public final class MobileNumber implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7512110703575285742L;
	
	private String mobileNumber; 
    
	public MobileNumber(String mobileNumber) throws NotAMobileNumberException {
		this.mobileNumber = this.checked(mobileNumber);
	}
	
	private String checked(String mobileNumber) throws NotAMobileNumberException {
		String number;
		try {
			if(mobileNumber.startsWith("0")) {
				number = mobileNumber.substring(3);
				if(mobileNumber.substring(1).length() == 10) {
					Long.parseLong(number);
					return mobileNumber;
				}
			} else if(mobileNumber.startsWith("+91")) {
				number = mobileNumber.substring(3);
				if(number.length() == 10) {
					Long.parseLong(number);
					return mobileNumber;
				}
			} else if(mobileNumber.length() == 10) { 
				Long.parseLong(mobileNumber);
				return mobileNumber;
			}
			throw new NotAMobileNumberException("'"+mobileNumber +"'"+ ": is not a Mobile Number", new NumberFormatException());
		} catch(NumberFormatException e) {
			throw new NotAMobileNumberException("'"+mobileNumber +"'"+ ": is not a Mobile Number", e);
		}
	}

	public String getNumber() {
		return mobileNumber;
	}
	
	public int length() {
		return mobileNumber.length();
	}

}
