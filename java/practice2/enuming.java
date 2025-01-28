enum enuming {
	RED,
	BLUE,
	WHITE;

	enuming() {
		System.out.println("We've finally created something");
	}
}

class what {
	public static void main(String[] args) {
		enuming en = enuming.RED;
		enuming ef = new enuming();
		System.out.println(en);
	}
}
