package com.mmi.mmi.service.converter;

public class GenderConverter {
	public enum gender {
        Pria,Wanita
    }
	
	public static gender convert(int genderCode) {
		if(genderCode == 1) {
			return gender.Pria;
		}else if(genderCode == 2) {
			return gender.Wanita;
		}
		return null;
	}
}
