import java.io.*;

@interface MarkerAnnotation
{
}

@interface SingleValueAnnotation
{
	String stripper() default "Sanjay";
}

@interface MultiValueAnnotation
{
	int age() default 19;
	String name() default "fuckering";
	String loveOfTheLife() default "me ;)";
	System.out.println("asdasd");
}

public class anno {
	public static void main(String[] args) {
		@MultiValueAnnotation
		System.out.println();
	}
}
