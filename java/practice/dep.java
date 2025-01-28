public class dep {

	@Deprecated

	public static void call() {
		System.out.println("Hello kiddo");
	}

	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		call();
	}
}
