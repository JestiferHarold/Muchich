use std::io;

fn main(){

    let mut length = String::new();
    let mut breath = String::new();
    
    print!("Enter the length : ");
    io::stdin().read_line(&mut length).unwrap();
    
    print!("\nEnter the breath : ");
    io::stdin().read_line(&mut breath).unwrap();

    let length : i32 = length.trim().parse().unwrap();
    let breath : i32 = breath.trim().parse().unwrap();
    let area = length * breath;

    print!("The area of the rectangle is {:.0}", area);
}
