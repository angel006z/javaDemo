package com.meida.common.util;

import java.util.UUID;

public class RegexValidate {

	public static boolean isUuid(String uuid) {
		if(StringUtils.isEmpty(uuid)){
			return false;
		}
		String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
		if (uuid.toLowerCase().matches(regex)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String uuid1 = "e65deb4c-a110-49c8-a4ef-6e69447968d6";
		String uuid2 = "ca4a8a92d4ed4fc48a4f345c587fbdcb";
		String uuid3 = "e1f15f1d-6edb-4f70-8a05465se273eaf95a";
		System.out.println("check > " + uuid1 + " > " + isUuid(uuid1));
		System.out.println("check > " + uuid2 + " > " + isUuid(uuid2));
		System.out.println("check > " + uuid3 + " > " + isUuid(uuid3));

		for (int i = 0; i < 100000 ; i++) {
			String uuid4= UUID.randomUUID().toString();
			if(!isUuid(uuid4)){
			System.out.println("check > " + uuid4 + " > " + isUuid(uuid4));
			}
		}

		System.out.println("check OK");
	}
}
