import java.util.Scanner;

import java.lang.Math;

import java.util.Arrays;

 

class QuestionOne{

   

    public static Boolean emiExceedingMonthlyIncome(double EMI, int annualIncome){

       

        final double monthlyIncome = annualIncome / 12;

        return(EMI > monthlyIncome ? true : false);

    }

   

    public static double calculateEmi(int princi, int interestRate, int tenure){

        final double EMI = (princi * interestRate * Math.pow(1 + interestRate, tenure))/(Math.pow(1 + interestRate, tenure) - 1);

       

        return EMI;

    }

 

    public static void main(String[] args){

           

        Scanner prompt = new Scanner(System.in);

        System.out.println("Enter annual income : "); int annualIncome = prompt.nextInt();

        System.out.println("Enter Credit Score : "); int creditScore = prompt.nextInt();

        if( annualIncome > 10_00_000 && creditScore > 750){

            System.out.println("Loan Approved without additional checks!");

            System.exit(0);

        }

        System.out.println("Enter number of dependents : "); int dependencies = prompt.nextInt();

       

        if (creditScore < 600 || dependencies > 4){

            System.out.println("Loan Rejected due to low credit score or too many dependents.");

            System.exit(0);

        }

       

       

       

        System.out.println("Enter loan principal amount : "); int loanPrincipalAmount = prompt.nextInt();  

        System.out.println("Enter annual interest rate  (in %) : "); int interestRate = prompt.nextInt();

        System.out.println("Enter tenure (in years) : "); int tenure = prompt.nextInt();

       

        final double emi = calculateEmi(loanPrincipalAmount, interestRate, tenure);

        if ( !emiExceedingMonthlyIncome(emi, annualIncome)){

            System.out.println("Loan Approved EMI will be " + emi);

            System.exit(0);

        }

        System.out.println("Loan not approved");

    }

}


 

class MobilePhone{

   

    public static void main(String[] args){

       

        Scanner prompt = new Scanner(System.in);

        System.out.println("Enter the number of months");int month = prompt.nextInt();

       

        double[] ratings = new double[month];

        for(int i =  1 ; i < month + 1; i ++){

            int f = 0;

            System.out.println("Start entering the product ratings for month " + i);

            double averageRating = 0.0d;

            while (true){

                int rate = prompt.nextInt();

                if (rate <= 10 && rate >= 0){  

                    averageRating += rate;

                    f++;

                }if (rate >= 1000){

                    break;

                }

            }

            if (f == 0){

                System.out.println("Month " + month + " : Error. Average cannot be calculated! ");

            }else{

                System.out.println("Month " + month + " : The average rating is " + averageRating);

            }

            ratings[i - 1] = averageRating / f;

        }

       

        double faultyRating = ratings[0];

        int m = 0;

        for (int i = 1; i < ratings.length; i ++){

            if (ratings[i] < faultyRating){

                faultyRating = ratings[i];

                m = i;

            }

        }

        System.out.println("The highest number of faulty rating is for month " + m);

    }

}

 

public class Finish{

	public static boolean checkIfProductExists(int[] arr, int product){
		for(int i : arr){
			if (i == product){
				return true;
			}
		}
		return false;
	}

    public static void main(String[] args){

        Scanner prompt = new Scanner(System.in);

        System.out.print("Enter the Size of the array : "); int size = prompt.nextInt();

      int[] array = new int[size];

        System.out.print("\nEnter array elements : ");

        for(int i = 0; i < size; i ++){

            int data = prompt.nextInt();

            array[i] = data;

        }

        int total = 0;

        for(int i : array){

            for(int j = 0; j < array.length; j++){

                if (checkIfProductExists(array, i * j)){

                    total ++;

                }

            }

        }

        System.out.println("There are three pairs : " + total);

    }

}

